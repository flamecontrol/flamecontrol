package src;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class ListCase {
    private static TestToolFrame tool = new TestToolFrame();
	private final static int KEYNAME = 0;				//参数名称
	private final static int KEYCONDITION = 1;			//参数条件
	private final static int KEYNEED = 2;				//参数必填
	private final static int KEYRIGHT = 3;				//参数正确信息
	
	private final static int VALUENAME = 0;				//请求名称
	private final static int VALUECONDITION = 1;		//请求条件
	private final static int VALUECASE = 2;				//请求条件
	private final static int VALUEBODY = 3;				//请求信息
	private final static int VALUERES = 4;				//请求预期应答
	

	private final static int KEYVALUECASE = 0;			//信息中值的位置(123::123::123)
	private final static int KEYVALUERES = 1;			//信息中值的位置(123::123::123)
	private final static int KEYVALUECOUNT = 2;			//信息中值的位置(123::123::123)
	private final static int KEYVALUEALL = 3;			//信息数量
	

	private static int TIMEORTHER = 1;			//防止信息重复自增
	
	public static List TestCase(List list){
		//cmd=zhengque&POI=ID001
		//cmd=zhengque&link=123&POI=ID001
		//取出单行数据
		List listinfo = new ArrayList();
		//返回测试设计用例
		List listres = new ArrayList();
//		单条测试
		List listvalue = new ArrayList();
//		单条测试体信息
		List listbody = new ArrayList();
		list.remove(0);
		String valueinfo = "";
//		全部参数
//		for(int k = 0;k < list.size();k++){
//			List kList = (List)list.get(k);
//			if(kList==null){						
//				continue;
//			}
//			String key = (String) (kList).get(KEYNEED);
//			if(!key.contains("&")){
//				String[] arrayvalue = ((String) ((List)list.get(k)).get(KEYRIGHT)).split("::");
//				valueinfo = arrayvalue[KEYVALUECOUNT];
//				if("time".equals(valueinfo)){
//					valueinfo = Getvalue("ddHHMM");
//				}
//				listbody.add(valueinfo);
//			}
//		}
//		listvalue.add("共通");
//		listvalue.add("");
//		listvalue.add("全部参数");
//		listvalue.add(listbody);
//		listvalue.add("0200");
//		if(listbody.size() != 0){
//			listres.add(listvalue);
//		}
//		小于APP头长度的请求
		listbody.add("0000");
		listvalue.add("共通");
		listvalue.add("");
		listvalue.add("小于APP头长度");
		listvalue.add(listbody);
		listvalue.add("00000000000400");
		if(listbody.size() != 0){
			listres.add(listvalue);
		}
		
//		按个参数设计用例
		for(int i = 0;i < list.size();i++){
			listinfo = (List) list.get(i);
			String name = (String) listinfo.get(KEYNAME);
			if("".equals((String) listinfo.get(KEYRIGHT+1))){
				continue;
			}
			for(int j = KEYRIGHT;j < listinfo.size()&&!"".equals((String) listinfo.get(j));j++){
//				单条测试
				listvalue = new ArrayList();
//				单条测试体信息
				listbody = new ArrayList();
//				测试条件
				for(int k = 0;k < i;k++){
					List kList = (List)list.get(k);
					if(kList==null){						
						continue;
					}
					String key = (String) (kList).get(KEYNEED);
					if("Y".equals(key)||name.equals(key)){
						String[] arrayvalue = ((String) ((List)list.get(k)).get(KEYRIGHT)).split("::");
						valueinfo = arrayvalue[KEYVALUECOUNT];
						if("time".equals(valueinfo)){
							valueinfo = Getvalue("ddHHMM");
						}
						listbody.add(valueinfo);
					}
				}
				String[] value = ((String) listinfo.get(j)).split("::");
				listvalue.add(listinfo.get(KEYNAME));
				//TODO 参数层次
				listvalue.add("");
				
				listvalue.add(value[KEYVALUECASE]);
				if(value.length < KEYVALUEALL){
					valueinfo = "";
				}else{
					valueinfo = value[KEYVALUECOUNT];
				}
				if("time".equals(valueinfo)){
					valueinfo = Getvalue("ddHHMM");
				}
				listbody.add(valueinfo);
				for(int k = i+1;k < list.size();k++){
					List kList = (List)list.get(k);
					if(kList==null){						
						continue;
					}
					String key = (String) (kList).get(KEYNEED);
					if("Y".equals(key)||name.equals(key)){
						String[] arrayvalue = ((String) ((List)list.get(k)).get(KEYRIGHT)).split("::");
						valueinfo = arrayvalue[KEYVALUECOUNT];
						if("time".equals(valueinfo)){
							valueinfo = Getvalue("ddHHMM");
						}
						listbody.add(valueinfo);
					}
				}
				listvalue.add(listbody);
				listvalue.add(value[KEYVALUERES]);
				listres.add(listvalue);
			}
//			if(!"N".equals((String) listinfo.get(KEYNEED))){
//				listvalue = new ArrayList();
//				listbody = new ArrayList();
//				for(int k = 0;k < i;k++){
//					List kList = (List)list.get(k);
//					if(kList==null){						
//						continue;
//					}
//					String key = (String) (kList).get(KEYNEED);
//					if("Y".equals(key)||name.equals(key)){
//						String[] arrayvalue = ((String) ((List)list.get(k)).get(KEYRIGHT)).split("::");
//						valueinfo = arrayvalue[KEYVALUECOUNT];
//						if("time".equals(valueinfo)){
//							valueinfo = Getvalue("ddHHMM");
//						}
//						listbody.add(valueinfo);
//					}
//				}
//				listvalue = new ArrayList();
//				listvalue.add(listinfo.get(KEYNAME));
//				listvalue.add("");
//				listvalue.add("无必填");
//
//				for(int k = i+1;k < list.size();k++){
//					List kList = (List)list.get(k);
//					if(kList==null){						
//						continue;
//					}
//					String key = (String) (kList).get(KEYNEED);
//					if("Y".equals(key)||name.equals(key)){
//						String[] arrayvalue = ((String) ((List)list.get(k)).get(KEYRIGHT)).split("::");
//						valueinfo = arrayvalue[KEYVALUECOUNT];
//						if("time".equals(valueinfo)){
//							valueinfo = Getvalue("ddHHMM");
//						}
//						listbody.add(valueinfo);
//					}
//				}
//				listvalue.add(listbody);
//				listvalue.add("0400");
//				if(listbody.size() != 0){
//					listres.add(listvalue);
//				}
//			}
		}
		body(listres);
		return listres;
	}
	
	public static List body(List listbegin,List list,List listend) {
		
		return list;
	}	
	
	public static String Getvalue(String dateformat) {
		Date date = new Date();
		String Dateinfo = date.toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(date);

		return (hehe + "" + TIMEORTHER++);
	}


	public static String body(List listall) {
		for(int i = 0;i < listall.size();i++){
			List list = (List) listall.get(i);
			String result = "";
			
			
			result += "=MID(CELL(\"filename\"),FIND(\"]\",CELL(\"filename\"))+1,255) ";
			result += "\t";
			result += list.get(VALUENAME);
			result += "\t";
			result += list.get(VALUECASE);
			result += "\t";
			result += "http://10.10.92.236:8080/inkaNetCANBUS/AVNServlet";
			result += "\t";
			result += "POST";
			result += "\t";

			List listbody = (List) list.get(VALUEBODY);
			for(int j = 0;j < listbody.size();j++){
				result += listbody.get(j);
			}
			result += "\t";
			result += list.get(VALUERES);
			
			System.out.println(result);
		}
		return "";
	}
	
	public static void main(String args[]) {
		List list = new ArrayList();
		List listdemo = new ArrayList();
		List listcase = new ArrayList();
		
    	try {
			tool.newfile();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		List<String> titlelist = tool.getsheetname();

//		for(int i = 0;i < 3;i++){
//		for(int i = 0;i < titlelist.size();i++){
//			String titile = titlelist.get(i);
//			list = tool.getsheet(titile);
//			listcase = TestCase(list);
//		}
		list = tool.getsheet("5.3.1 驾驶信息上报");
		listcase = TestCase(list);
//		list = tool.getsheet("4.3.2 POI详情检索接口");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.3.3 查询车次-航班列表");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.1 普通POI新切");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.2 新增航班车次");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.3 新增公交地铁线路");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.4 普通POI签到");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.5 公交地铁切");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.6 飞机火车切");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.7 新增道路出租车切");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.8 交通切评论");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.9 查询交通切详细信息");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.10 查询我的新切");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.11 查询交通切");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.4.12 查询我的切点(历史切迹)");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.5.1 基于位置的提问");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.5.2 回答别人的提问");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.5.3 查询某个问题的答案列表");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.5.4 把某个答案置为最佳答案");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.5.5 查询我的提问列表");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.5.6 搜索所有提问");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.5.7 查询我的回答");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.6.1 挂号到某个区域");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.6.2 查询挂号区域列表");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.7.1 提出纠错");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.7.2 查询我的纠错");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.8.1 查询我的积分");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.9.1 下载优惠券");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.9.2 查询我的优惠券");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.9.3 查询周边优惠券");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.10.1 增加我的导航点");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.10.2 删除我的导航点");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.10.3 查询我收藏夹中的导航点");
//		listcase = TestCase(list);
//		list = tool.getsheet("4.10.4 共享我的导航点");
//		listcase = TestCase(list);
	}
}
