package src;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestToolFrame implements Runnable {

	static String strRecode = "0200";

	static String strMSGRecode;

	static String strheaderCode = "0200";

	private static HttpURLConnection httpURLConnection = null;

	private static String[] sheetNameArray = { "版本验证", "终端接口服务", "", "", "",
			"", "", "", "", "", "" };

	private static String TesterName = "测试业务部"; // 测试人

	private static String rsOK = "通过"; // 测试通过

	private static String rsNG = "不通过"; // 测试不通过

//	private static String rscode = "x-resource-code";
//
//	private static String rsMsg = "X-MESSAGES-FLAG";
//
//	private static String rsHeader = "X-MGWS-Header";

	// private static String rsHeader = "X-REPLYCODE";

	private static String savefolder = "c:\\";

	private static String saveprefix = "测试结果_";

	private final int CellCount = 14; // 总单元格数量

	private final int start = 2; // 开始在第3行

	private final int end = 65535; // 测试限制

	private final int urlColIdx = 3; // 第4列为请求URL

	private final int headCellNum = 4; // 第5列为请求Head

	private final int bodyCellNum = 5; // 第6列为请求Body

	private final int preRsNum = 6; // 第7列为预期结果

	private final int responsecode = 7; // 第8列为应答码

	private final int timeNum = 8; // 第9列为应答头

	private final int responseCellNum = 9; // 第10列为应答体

	private final int rsNum = 10; // 第11列为测试结果(程序判断)

	private final int nameNum = 11; // 第12列为测试人

	int testOverCount = 0; // 完成测试工作表数

	int SetTime = 0; // 执行一条结束后等待时间

	int infolenght = 1000; // 应答截取最大长度

	boolean bFlag = true;

	private String preRsString = "";

	private boolean runtype = false;

	private boolean savetype = false;

	File[] fileArr = new File[255];

	InputStream is = null;

	HSSFWorkbook wb = null;

	List needlist = null;

	String characterCode = (String) AutoProperties.getInstance().get(
			"characterCode");
	
	String settingfile = (String)(AutoProperties.getInstance().get("settingfile"));

	// 初始化，读取配置
	public boolean init(boolean first) throws IOException {
		String resultName;
		if(settingfile == null){
			resultName = "";
		}else{
			resultName = new String(settingfile.getBytes("ISO-8859-1"),"gbk");
		}
		if("".equals(resultName)){
			if(!first){
				newfile();
			}
		}else{
			File fileset = new File(resultName);
			fileArr[0] = fileset;
			is = new FileInputStream(fileset);
		}
		wb = new HSSFWorkbook(is);
		return true;
	}	
	
	// 初始化，读取新文件
	public boolean newfile() throws IOException {
		JFileChooser jfc = new JFileChooser();
		if (null != jfc) {
			jfc.setMultiSelectionEnabled(true);
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Microsoft Office Excel", "xls", "xlsx");
		jfc.setFileFilter(filter);
		if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null)) {
			// TODO 多文件选取处理或屏蔽
			fileArr = jfc.getSelectedFiles();
			is = new FileInputStream(fileArr[0]);
			// new TestToolFrame().business(fileArr[0]);
		} else {
			return false;
		}
		wb = new HSSFWorkbook(is);
		return true;
	}

	// 取得全部工作表名称，读取选取工作文档的全部工作表名
	public List getsheetname() {
		List<String> list = new ArrayList<String>();
		int sheetnum = wb.getNumberOfSheets();
		// HSSFSheet sheet = wb.getSheetAt(0);
		for (int i = 0; i < sheetnum; i++) {
			// list.add(wb.getSheetAt(i));
			list.add(wb.getSheetName(i));
			// list.add(i, wb.getSheetName(i))
		}
		System.out.println(list);
		return list;
	}

	public List getsheet(String sheetName) {

		HSSFSheet sheet = wb.getSheet(sheetName);
		int readLineNo = 1;

		List list = new ArrayList();
		if (null == sheet) {
			return list;
		}

		try {
			System.out.println("开始进行" + sheetName + "的测试");

			HSSFRow row = null;
			// 对文件中的每一行进行读写处理
			inner: for (int i = 0; i < end; i++) {

				// Map map = new HashMap();
				List<String> listinfo = new ArrayList();
				// 取得当前行的信息
				row = sheet.getRow(i);

				if (null == row) {
					break inner;
				}

				try {
					// TODO for匹配一定个数后，读取到包含值的数据
					for (int cellnum = 0; cellnum < CellCount; cellnum++) {
						String CellInfo = null;
						if (row.getCell(cellnum) != null) {
							// if(row.getCell(cellnum).getRichStringCellValue()
							// != null){
							// CellInfo =
							// row.getCell(cellnum).getRichStringCellValue().toString();
							// }else{
							// CellInfo = "";
							// }
							try {
								CellInfo = row.getCell(cellnum)
										.getRichStringCellValue().toString();
							} catch (Exception ex) {
								CellInfo = "";
							}
						} else {
							CellInfo = "";
						}
						listinfo.add(CellInfo);
					}
					row = null;
					readLineNo++;
				} catch (Exception ex) {
					ex.printStackTrace();
					String errorInfo = "第" + String.valueOf(readLineNo)
							+ "行不是有效的信息";
					readLineNo++;
					System.out.println(errorInfo);
					writeErrNo(wb, row, sheetName, errorInfo);
					continue;
				}
				list.add(listinfo);
			}

			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
				httpURLConnection = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Boolean setsheet(String sheetName, List<String> listinfo) {

		HSSFSheet sheet = wb.getSheet(sheetName);
		int readLineNo = 1;

		List list = new ArrayList();
		if (null == sheet) {
			return false;
		}

		try {
			System.out.println("开始进行" + sheetName + "的测试");
			HSSFRow row = null;
			// 对文件中的每一行进行读写处理
			inner: for (int i = 0; i < listinfo.size(); i++) {
				// 设置当前行的信息
				row = sheet.getRow(i);
				if (null == row) {
					break inner;
				}
				try {
					for (int cellnum = 0; cellnum < CellCount; cellnum++) {
						String CellInfo = null;
						if (row.getCell(cellnum) != null) {
							if (row.getCell(cellnum).getRichStringCellValue() != null) {
								CellInfo = row.getCell(cellnum)
										.getRichStringCellValue().toString();
							} else {
								CellInfo = "";
							}
						} else {
							CellInfo = "";
						}
						listinfo.add(CellInfo);
					}

					row = null;
					readLineNo++;
				} catch (Exception ex) {
					ex.printStackTrace();
					String errorInfo = "第" + String.valueOf(readLineNo)
							+ "行不是有效的信息";
					readLineNo++;
					System.out.println(errorInfo);
					continue;
				}

				// list.add(map);
				list.add(listinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean getruntype() {
		return runtype;
	}

	public boolean getsavetype() {
		return savetype;
	}

	public static String Getvalue(String dateformat) {
		Date date = new Date();
		String Dateinfo = date.toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(date);

		return hehe;
	}

	public void saveFile() {

		savetype = true;
		// 把所有信息（发送信息，应答信息）写入文件并关闭该文件
		try {
			wb.write(new FileOutputStream(savefolder + saveprefix
//					+ Getvalue("MM-dd HH-MM-SS") + "_" + "TestResult",
					+ Getvalue("MM-dd HH-MM-SS") + "_" + fileArr[0].getName(),
					true));
//			System.out.println("文件【" + "TestResult" + "】测试完成。");
			System.out.println("文件【" + fileArr[0].getName() + "】测试完成。");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// TODO 怎么关闭
		}
		savetype = false;
	}

	private boolean testneed(int readLineNo) {

		if ("1".equals((needlist.get(readLineNo).toString()))) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public void business(final String sheetName, List list) {
		runtype = true;
		needlist = new ArrayList(list);
		final int readLineNo = 1;
		try {
			// 对EXCEL文件中的所有sheet进行循环处理
			Thread t = new Thread() {
				public void run() {
					if (wb != null) {
						test(wb, sheetName, readLineNo);
						testOverCount++;
					}
				}
			};
			t.start();
			t.currentThread().sleep(SetTime);
			if(SetTime!=0){
				System.out.println("系统等待："+SetTime+"秒。");
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private synchronized void test(HSSFWorkbook wb, String sheetName,
			int readLineNo) {

		HSSFSheet sheet = wb.getSheet(sheetName);

		try {
			if (null == sheet) {
				return;
			}
			System.out.println("开始进行" + sheetName + "的测试");

			HSSFRow row = null;
			// 对文件中的每一行进行读写处理
			inner: for (int i = start; i < end; i++) {
				// 取得当前行的信息
				row = sheet.getRow(i);

				try {
					// 对EXCEL文件中的所有sheet进行循环处理
					Thread curt = new Thread();
					curt.currentThread().sleep(SetTime);
					if(SetTime!=0){
						System.out.println("系统等待："+SetTime+"秒。");
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (null == row) {
					break inner;
				}

				// 取得【请求url】单元格信息
				HSSFCell urlCell = row.getCell(urlColIdx);

				if (null == urlCell) {
					break inner;
				}

				if (!testneed(i)) {
					continue;
				}

				HSSFRichTextString requestUrl = null;
				HSSFRichTextString requestHead = null;
				HSSFRichTextString requestBody = null;
				HSSFRichTextString preResult = null;

				if (urlCell != null) {
					try {
						requestUrl = urlCell.getRichStringCellValue();
						// URL信息
						String urlString = requestUrl.getString();
						String[] result = null;
						result = urlString.split("[?]");

						if (result.length > 1) {
							bFlag = false;
							String urlInfo = result[0];
							String bodyInfo = result[1];

							if ((urlInfo.equals("") || urlInfo.equals(null))
									&& (bodyInfo.equals("") || bodyInfo
											.equals(null))) {
								break inner;
							}
							System.out.println(sheetName + ": 正在读取第" + (i - 1)
									+ "条数据！");
							// 发送消息
							sendMsgOld(urlInfo, bodyInfo);
							// 取得应答信息
							GetHttpResponse(wb, row, sheetName, readLineNo);
							System.out.println(sheetName + ":写入第" + (i - 1)
									+ "条数据完毕！");
							row = null;
							readLineNo++;
						} else {

							// 取得【请求header】单元格信息
							HSSFCell headCell = row.getCell(headCellNum);
							// 取得【请求body】单元格信息
							HSSFCell urlBody = row.getCell(bodyCellNum);
							// 取得【预期结果】单元格
							HSSFCell preRs = row.getCell(preRsNum);

							if (null == headCell || null == urlBody
									|| null == preRs) {
								break inner;
							}
							requestHead = headCell.getRichStringCellValue();
							// Head信息
							String headMsg = requestHead.getString();

							requestBody = urlBody.getRichStringCellValue();
							// BODY信息
							String bodyString = requestBody.getString();

							preResult = preRs.getRichStringCellValue();
							preRsString = preResult.getString();

							if ((urlString.equals("") || urlString.equals(null))
									&& (headMsg.equals("") || headMsg
											.equals(null))
									&& (bodyString.equals("") || bodyString
											.equals(null))) {
								break inner;
							}
							System.out.println(sheetName + ": 正在读取第" + (i - 1)
									+ "条数据！");
							// 发送消息
							sendMsg(headMsg, urlString, bodyString);
							// 取得应答信息
							GetHttpResponse(wb, row, sheetName, readLineNo);
							System.out.println(sheetName + ":写入第" + (i - 1)
									+ "条数据完毕！");
							row = null;
							readLineNo++;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						String errorInfo = "第" + String.valueOf(readLineNo)
								+ "行不是有效的信息";
						readLineNo++;
						System.out.println(errorInfo);
						writeErrNo(wb, row, sheetName, errorInfo);
						continue;
					}
				} else {
					break inner;
				}
			}

			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
				httpURLConnection = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		runtype = false;
	}

	private void sendMsg(String headMsg, String urlString, String bodyString) {

		try {
			if (null == urlString) {
				System.out.println("URL信息不存在！");
				return;
			}
			// 创建HttpURLConnection对象
			java.net.URL url = new java.net.URL(urlString);
			java.net.URLConnection urlcn = url.openConnection();

			if (urlcn == null) {
				System.out.println("URL连接失败【" + urlString + "】");
				return;
			}
			String[] result = null;
			String[] propResult = null;
			String key = null;
			String value = null;

			if (urlcn instanceof HttpURLConnection) {

				httpURLConnection = (HttpURLConnection) urlcn;
				httpURLConnection.setRequestMethod("POST");

				// 按照"\n"和":"分隔符对读取的body信息进行拆分处理
				result = headMsg.split("\n");
				for (int i = 0; i < result.length; i++) {
					propResult = result[i].split(":");

					// 如果以":"作为分隔符拆分后的数组的长度为1，则证明此信息不是按照"
					if (1 == propResult.length) {
						continue;
					}
					propResult[0].trim();
					// HOST的信息的格式是【Host:
					// 218.25.39.56:9094】，拆分后数组的长度为3，即propResult[0]=HOST,
					// propResult[1]=218.25.39.56,218.25.39.56[2]=9094,所以要进行组合处理，
					if (propResult[0].equals("Host")) {
						propResult[1] = propResult[1] + ":" + propResult[2];
					}
					key = propResult[0].trim();
					value = propResult[1].trim();
					// 设置HttpURLConnection对象的属性
					if (null != httpURLConnection) {
						httpURLConnection.setRequestProperty(key, value);
					}
				}

				if (httpURLConnection != null) {
					httpURLConnection.setDoOutput(true);
					httpURLConnection.setDoInput(true);
					// 发送请求
					java.io.OutputStream out = null;

					out = httpURLConnection.getOutputStream();
					out.write(bodyString.getBytes(characterCode));
					out.flush();
					out.close();
					// 添加

//					String code = httpURLConnection.getHeaderField(rscode);
//					String msgCode = httpURLConnection.getHeaderField(rsMsg);
//					String headerCode = httpURLConnection
//							.getHeaderField(rsHeader);
					strRecode = null;
					// if(code!=null && !"".equals(code)){
					// strRecode = code;
					// }
					// if(msgCode!=null && !"".equals(msgCode)){
					// strMSGRecode = msgCode;
					// }
					// if(headerCode!=null && !"".equals(headerCode)){
					// strheaderCode = headerCode;
					// }
//					if (code != null && !"".equals(code)) {
//						strRecode = code;
//					}
//					if (msgCode != null && !"".equals(msgCode)) {
//						strMSGRecode = msgCode;
//					}
//					if (headerCode != null && !"".equals(headerCode)) {
//						strheaderCode = headerCode;
//					}
					// 添加完毕
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public List sendMsgPOST(String headMsg, String urlString, String bodyString, String Charset) {
		String strres = "";
		String strOutFileHead = "";
		HttpURLConnection httpURL = null;
		List<String> list = new ArrayList<String>();
		try {
			if (null == urlString) {
				System.out.println("URL信息不存在！");
				return list;
			}
			// 创建HttpURLConnection对象
			java.net.URL url = new java.net.URL(urlString);
			java.net.URLConnection urlcn = url.openConnection();

			if (urlcn == null) {
				System.out.println("URL连接失败【" + urlString + "】");
				return list;
			}
			String[] result = null;
			String[] propResult = null;
			String key = null;
			String value = null;

			if (urlcn instanceof HttpURLConnection) {

				httpURL = (HttpURLConnection) urlcn;
				httpURL.setRequestMethod("POST");
				InputStream in = null;// http连接的输入流

				// 按照"\n"和":"分隔符对读取的body信息进行拆分处理
				result = headMsg.split("\n");
				for (int i = 0; i < result.length; i++) {
					propResult = result[i].split(":");

					// 如果以":"作为分隔符拆分后的数组的长度为1，则证明此信息不是按照"
					if (1 == propResult.length) {
						continue;
					}
					propResult[0].trim();
					// HOST的信息的格式是【Host:
					// 218.25.39.56:9094】，拆分后数组的长度为3，即propResult[0]=HOST,
					// propResult[1]=218.25.39.56,218.25.39.56[2]=9094,所以要进行组合处理，
					if (propResult[0].equals("Host")) {
						propResult[1] = propResult[1] + ":" + propResult[2];
					}
					key = propResult[0].trim();
					value = propResult[1].trim();
					// 设置HttpURLConnection对象的属性
					if (null != httpURL) {
						httpURL.setRequestProperty(key, value);
					}
				}

				if (httpURL != null) {
					httpURL.setDoOutput(true);
					httpURL.setDoInput(true);
					// 发送请求
					java.io.OutputStream out = null;

					out = httpURL.getOutputStream();
					out.write(bodyString.getBytes(Charset));
					out.flush();
					out.close();
					// 添加

					// String code = httpURLConnection.getHeaderField(rscode);
					// String msgCode = httpURLConnection.getHeaderField(rsMsg);
					// String code = "";
					// String msgCode = "";
//					String headerCode = httpURL.getHeaderField(rsHeader);
					// strRecode = null;
					// if(code!=null && !"".equals(code)){
					// strRecode = code;
					// }
					// if(msgCode!=null && !"".equals(msgCode)){
					// strMSGRecode = msgCode;
					// }
//					if (headerCode != null && !"".equals(headerCode)) {
//						strheaderCode = headerCode;
//					}
					Map<String, List<String>> headMap = httpURL
							.getHeaderFields();

					// 取得http返回的状态码
					in = httpURL.getInputStream();
					// FileOutputStream fo = new
					// FileOutputStream("d:\\mali.txt");

					ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					int len = -1;
					while ((len = in.read(buf)) != -1) {
						byteOut.write(buf, 0, len);
					}
					byteOut.flush();
					String temp = byteOut.toString();
					System.out.println(temp);
					strres = temp;

					String strTemp = null;
					// 读取应答HEAD信息
					for (Map.Entry<String, List<String>> m : headMap.entrySet()) {
						strTemp = (m.getKey());
						strOutFileHead += strTemp + "    ";
						strTemp = "";

						strTemp = (m.getValue()).toString();
						strOutFileHead += strTemp + "	";
						strOutFileHead += "\n";
						strTemp = "";
					}
					// if(code!=null && !"".equals(code)){
					// strRecode = code;
					// strres = code;
					// } else{
					// strres = "wujieguo";
					// }
					// if(msgCode!=null && !"".equals(msgCode)){
					// strMSGRecode = msgCode;
					// }
					// if(headerCode!=null && !"".equals(headerCode)){
					// strheaderCode = headerCode;
					// }
					// 添加完毕
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			list.add("ERROR:NO RESPINSE");
			list.add(e.toString());
			return list;

		}
		list.add(strres);
		list.add(strOutFileHead);
		return list;

	}

	private static void sendMsgOld(String urlString, String bodyString) {

		try {
			if (null == urlString) {
				System.out.println("URL信息不存在！");
				return;
			}
			// 创建HttpURLConnection对象
			java.net.URL url = new java.net.URL(urlString);
			java.net.URLConnection urlcn = url.openConnection();

			if (urlcn == null) {
				System.out.println("URL连接失败【" + urlString + "】");
				return;
			}

			if (urlcn instanceof HttpURLConnection) {

				httpURLConnection = (HttpURLConnection) urlcn;
				httpURLConnection.setRequestMethod("POST");

				if (httpURLConnection != null) {
					httpURLConnection.setDoOutput(true);
					httpURLConnection.setDoInput(true);
					// 发送请求
					java.io.OutputStream out = null;

					out = httpURLConnection.getOutputStream();
					out.write(bodyString.getBytes());
					out.flush();
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public void GetHttpResponse(HSSFWorkbook wb, HSSFRow row, String sheetName,
			int cmdNo) {

		if (httpURLConnection != null && null != row) {
			try {
				Map<String, List<String>> headMap = httpURLConnection
						.getHeaderFields();
				String strTemp = null;

				String strOutFileHead = "";
				// String strOutFileHead = "head信息:\n";
				// 读取应答HEAD信息
				for (Map.Entry<String, List<String>> m : headMap.entrySet()) {
					strTemp = (m.getKey());
					strOutFileHead += strTemp + "    ";
					strTemp = "";

					strTemp = (m.getValue()).toString();
					strOutFileHead += strTemp + "	";
					strOutFileHead += "\n";
					strTemp = "";
				}

				if (null == httpURLConnection) {
					return;
				}
				InputStream inputS = httpURLConnection.getInputStream();
				BufferedReader reader = new BufferedReader(
						new java.io.InputStreamReader(inputS));

				String strOutFile = "";
				// String strOutFile = "body信息:\n";
				// strOutFile = "";//打印头信息
				// char[] charBuff = new char[infolenght];
				char[] charBuff = new char[1000];
				int result = 0;

				try {
					result = reader.read(charBuff);
					if (-1 != result) {
						for (int loop = 0; loop < result; loop++) {
							strOutFile += charBuff[loop];
						}
					}
					System.out.println("第 " + cmdNo + " 行的应答数据为 " + result
							+ " 个字符！");
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (strRecode != null && !"".equals(strRecode)) {
					System.out.println(sheetName + "--->应答结果：" + strRecode);
				}

				if (strMSGRecode != null && !"".equals(strMSGRecode)) {
					System.out.println(sheetName + "--->应答结果：" + strMSGRecode);
				}

				if (strOutFile == null || "".equals(strOutFile)) {
					strOutFile = strheaderCode;
					// strOutFile = strheaderCode;
					System.out.println(sheetName + "--->应答结果：");
				}

				System.out.println(sheetName + "--->应答结果：" + strOutFile);
				String rs = rsNG;
				// String time = Calendar.getInstance().getTime().toString();

				// 测试结果比较
				// if(strOutFile.equals(preRsString)||strheaderCode.equals(preRsString))
				// {
				if (strheaderCode.equals(preRsString)) {
					rs = rsOK;
				}

				// 应答结果截取
//				if (strOutFile.length() > 5000) {
//					strOutFile = strOutFile.substring(0, 5000);
//				}

				if (bFlag) {
					HSSFCell resultCellcode = row.getCell(responsecode);
					HSSFCell resultCell = row.getCell(responseCellNum);
					HSSFCell resultCell2 = row.getCell(timeNum);
					HSSFCell resultCell3 = row.getCell(rsNum);
					HSSFCell resultCell4 = row.getCell(nameNum);

					if (null == resultCell) {
						resultCell = row.createCell(responseCellNum);
					}

					if (null == resultCell2) {
						resultCell2 = row.createCell(timeNum);
					}

					if (null == resultCell3) {
						resultCell3 = row.createCell(rsNum);
					}

					if (null == resultCell4) {
						resultCell4 = row.createCell(nameNum);
					}

					// 把应答信息写入到EXCEL文件的相应的位置
					if (strheaderCode != null) {
						resultCell.setCellValue(new HSSFRichTextString(
								strheaderCode));
					}
					if (strMSGRecode != null) {
						resultCell.setCellValue(new HSSFRichTextString(
								strMSGRecode));
					}
					if (strRecode != null) {
						resultCell.setCellValue(new HSSFRichTextString(
								strRecode));
					}

					resultCell.setCellValue(new HSSFRichTextString(strOutFile));
					resultCellcode.setCellValue(new HSSFRichTextString(
							strheaderCode));
					resultCell2.setCellValue(new HSSFRichTextString(
							strOutFileHead));
					// resultCell2.setCellValue(new HSSFRichTextString(time));
					resultCell3.setCellValue(new HSSFRichTextString(rs));
					resultCell4
							.setCellValue(new HSSFRichTextString(TesterName));
				} else {
					HSSFCell resultCell = row.getCell(bodyCellNum);
					if (null == resultCell) {
						resultCell = row.createCell(bodyCellNum);
					}
					// 把应答信息写入到EXCEL文件的相应的位置
					resultCell.setCellValue(new HSSFRichTextString(strOutFile));
				}
				reader.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String errorInfo = "第" + String.valueOf(cmdNo) + "行内容为空！";
			writeErrNo(wb, row, sheetName, errorInfo);
		}
	}

	public void writeErrNo(HSSFWorkbook wb, HSSFRow row, String sheetName,
			String errMsg) {

		try {
			HSSFCell resultCell = row.getCell(responseCellNum);
			if (null == resultCell) {
				resultCell = row.createCell(responseCellNum);
			}
			resultCell.setCellValue(new HSSFRichTextString(errMsg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.business(nowSheet, list);

	}

	private String nowSheet = "";

	public void setNowsheet(String nowSheet) {
		nowSheet = nowSheet;

	}

	private List<String> list;

	public void setList(List<String> list) {
		list = list;
	}

	public int getSetTime() {
		return SetTime;
	}

	public void setSetTime(int setTime) {
		SetTime = setTime;
	}
}
