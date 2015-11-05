package test.UGC;
//import org.junit.Assert.fail;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.tools.UGCPOST;
import com.tools.tools;


public class UGCTest {

	String strName = "";
	String strHead = "";
	String strUrl = "";
	String strBody = "";
	int nOvertime = 0;
	String strExpect = "";

	String strResult = "";
	String strBaseurl = "http://autopia.bang58.com";
	String strLogin = "password=q&username=1@1.zz";
	String strFilename = "./logs/log.txt";

	tools Ctools = new tools();
	UGCPOST Cugc = new UGCPOST(strBaseurl,strLogin);
	
//	@Test
//	public void test_UGC1() 
//	{
//		strName = "（一）接口版本信息";
//		strUrl = strBaseurl+"/ctb_test/index.php?c=user&m=login&d=passport";
//		strBody = "password=q&username=1@1.zz";
//		nOvertime = 5000;
//		strExpect = "\"ret\":200";
//		
//		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
//	}
	
	@Test
	public void test_UGC2() 
	{
//		String strHead = "";
//		String strName = "握手";
//		String strUrl = strBaseurl+"/ctb_test/index.php?c=user&m=hs&d=passport";
//		String strBody = "platform=2&udid=484faeefdddba9f1&os=2.3.6&channel=36O&config_ver=1&ver=3.0.9";
//		int nOvertime = 5000;
//		String strExpect = "\"ret\":200";
//		
//		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
//		
//		
//		
//		strName = "（一）接口版本信息";
//		strUrl = strBaseurl+"/ctb_test/index.php?c=user&m=login&d=passport";
//		strBody = "type=1&password=123456&username=seraph";
//		nOvertime = 5000;
//		strExpect = "\"ret\":200";
//		
//		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
//		
//		
//		
//		
		strName = "（一）接口版本信息";
		strUrl = strBaseurl+"/ctb_test/index.php?c=point&m=report";
		strBody = "dir=153&kilometer=0&lat=4181006&length=0&lng=12342811&mood=2&speed=0&type=11&uid=20000162&uname=%E8%B6%85%E7%BA%A7%E7%89%9B%E7%89%9B&upic=b522ccfa3d50a146";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	
	
	public void sendmsg(String strName,String strHead,String strUrl,String strBody,int nOvertime,String strExpect) {

		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		// 执行一次请求
		strResult = Cugc.sendCode(strHead,strUrl,strBody);
		long endMili=System.currentTimeMillis();
		long totleMili = endMili-startMili;
		Ctools.logPrint(strFilename,"服务：<<<"+strName+">>>总耗时为："+totleMili+"毫秒");
		Ctools.logPrint(strFilename,strResult);

		if(strResult.indexOf(strExpect) == -1){
			fail("失败：<<<"+strName+">>>:"+strResult+"，预期("+strExpect+")");
		}
		if(totleMili > nOvertime){
			fail("超时：<<<"+strName+">>>:"+totleMili+"毫秒，预期("+nOvertime+")");
		}
		org.junit.Assert.assertTrue("成功：<<<"+strName+">>>:", true);
	}
}