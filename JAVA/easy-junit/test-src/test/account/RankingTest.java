package test.account;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tools.tools;


public class RankingTest {

	String strName = "";
	String strHead = "";
	String strUrl = "";
	String strBody = "";
	int nOvertime = 0;
	String strExpect = "";
	
	String strResult = "";
//	String strBaseurl = "http://192.168.80.7:84";
	String strBaseurl = "http://ranking.api.com";	
	static String strFilename = "./logs/log.txt";

	tools Ctools = new tools();
	
	@Test
	public void test_getranking13() {
		strName = "3.1. 获取排行榜信息（等级）";
		strUrl = strBaseurl+"/ranking/getranking?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&ranking_type=13&user_id=10000162&page_index=0&page_size=50";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	@Test
	public void test_getranking23() {
		strName = "3.1. 获取排行榜信息（财富）";
		strUrl = strBaseurl+"/ranking/getranking?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&ranking_type=23&user_id=10000162&page_index=0&page_size=50";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	@Test
	public void test_getuserranking1() {
		strName = "3.2. 获取用户在总排行榜中的排行（等级）";
		strUrl = strBaseurl+"/ranking/getuserranking?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&type=1&user_id=10000162";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	@Test
	public void test_getuserranking2() {
		strName = "3.2. 获取用户在总排行榜中的排行（财富）";
		strUrl = strBaseurl+"/ranking/getuserranking?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&type=2&user_id=10000162";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	@Test
	public void test_getuserrankingpercent1() {
		strName = "3.3. 获取用户在总排行榜中的百分比（等级）";
		strUrl = strBaseurl+"/ranking/getuserrankingpercent?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&type=1&user_id=10000162";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	@Test
	public void test_getuserrankingpercent2() {
		strName = "3.3. 获取用户在总排行榜中的百分比（财富）";
		strUrl = strBaseurl+"/ranking/getuserrankingpercent?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&type=2&user_id=10000162";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	
	public void sendmsg(String strName,String strHead,String strUrl,String strBody,int nOvertime,String strExpect) {
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		// 执行一次请求
		strResult = Ctools.sendMsg(strHead,strUrl,strBody);
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
	}
}