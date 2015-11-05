package test.account;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

import com.tools.tools;


public class ClusterTest {

	String strName = "";
	String strHead = "";
	String strUrl = "";
	String strBody = "";
	int nOvertime = 0;
	String strExpect = "";
	
	String strResult = "";
	String strBaseurl = "http://cluster.api.com";
	String strFilename = "./logs/log.txt";
	
	String s;
    java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyyMMddhhmmss");

	tools Ctools = new tools();

	@Test
	public void test_createcluster() {
		strName = "3.1. 创建群接口 - 3.2. 加入群接口 - 3.15. 成员退群";
		strUrl = strBaseurl+"/cluster/createcluster?format=json";

        s = format1.format(new Date());
//        System.out.println(s);
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&user_id=20000162&name=Q" + s + "&Type=1&dec=a02131321&Lat=4024179&Lng=11644134";
		nOvertime = 5000;
		strExpect = "\"ret\":200";

//		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
		String GUID;
//		GUID = strResult.split(",")[0];
//		GUID = GUID.split(":")[1];
		GUID = "254";

		strUrl = strBaseurl+"/cluster/JoinCluster?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&user_id=20001224&group_id=" + GUID;
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
		
		strUrl = strBaseurl+"/cluster/exitcluster?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&group_id=" + GUID + "&member_id=20001224";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
//	@Test
//	public void test_() {
//		strName = "";
//		strUrl = strBaseurl+"/cluster/?format=json";
//		strBody = "";
//		nOvertime = 5000;
//		strExpect = "\"ret\":200";
//		
//		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
//	}
	
	
	
	public void sendmsg(String strName,String strHead,String strUrl,String strBody,int nOvertime,String strExpect) {
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		// 执行一次请求
		strResult = Ctools.sendMsg(strHead,strUrl,strBody);
		long endMili=System.currentTimeMillis();
		long totleMili = endMili-startMili;
		Ctools.logPrint(strFilename,"服务：<<<"+strName+">>>总耗时为："+totleMili+"毫秒");
		Ctools.logPrint(strFilename,strResult);
		System.out.println(strBody);
		System.out.println(strResult);
		if(strResult.indexOf(strExpect) == -1){
			fail("失败：<<<"+strName+">>>:"+strResult);
		}
		if(totleMili > nOvertime){
			fail("超时：<<<"+strName+">>>:"+totleMili+"毫秒("+nOvertime+")");
		}
	}
}