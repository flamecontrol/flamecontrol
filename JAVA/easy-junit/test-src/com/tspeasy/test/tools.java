package com.tspeasy.test;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Date;


public class tools {
	static String sendMsg(String strHead, String strUrl, String strBody) {
		
        StringBuffer strBuf = new StringBuffer();
		HttpURLConnection httpURLConnection = null;
		StringBuffer str = new StringBuffer();
		try {
			if (null == strUrl) {
				System.out.println("URL信息不存在！");
				return "";
			}
			// 创建HttpURLConnection对象
			java.net.URL url = new java.net.URL(strUrl);
			java.net.URLConnection urlcn = url.openConnection();

//			java.net.URLConnection urlcn = setHttpProxy(url.openConnection());
			   // 设置获取Html的Url
//			   URL url = new URL(strUrl + strCityName);
			 
			   // 获取链接，带代理
//			   URLConnection con = setHttpProxy(url.openConnection());
			if (urlcn == null) {
				System.out.println("URL连接失败【" + strUrl + "】");
				return "";
			}
			String[] result = null;
			String[] propResult = null;
			String key = null;
			String value = null;

			if (urlcn instanceof HttpURLConnection) {

				httpURLConnection = (HttpURLConnection) urlcn;
				httpURLConnection.setRequestMethod("POST");

				// 按照"\n"和":"分隔符对读取的body信息进行拆分处理
				result = strHead.split("\n");
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
					out.write(strBody.getBytes());
					out.flush();
					out.close();
					String line = null;
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(httpURLConnection
									.getInputStream()));
					line = reader.readLine();
					if (null != line) {
						str.append(line);
					}
					while ((line = reader.readLine()) != null) {
						str.append(System.getProperty("line.separator"))
								.append(line);
					}

					reader.close();
					httpURLConnection.disconnect();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        strBuf.append("应答结果：" + str.toString());
		return strBuf.toString();
	}
	static boolean logPrint(String fileName,String strmsg)
	{
		Date d = new Date();
        String s;
        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        s = format1.format(new Date());
        
        try {
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(s+" : "+strmsg+"\n");
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		return true;
	}
}
