package com.tspeasy.test;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

public class UGCTest {

	String strName = "";
	String strHead = "";
	String strUrl = "";
	String strBody = "";
	int nOvertime = 0;
	String strExpect = "";
	
	static String strBaseurl = "http://192.168.80.7:84";
	static String strFilename = "./logs/log.txt";
	
	@BeforeClass
	public static void teststart()
	{
		tools.logPrint(strFilename,"路况基础服务测试开始。");
		String strHead = "";
		String strName = "3.1. 获取排行榜信息（等级）";
		String strUrl = strBaseurl+"/ranking/getranking?format=json";
		String strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&ranking_type=13&user_id=10000162&page_index=0&page_size=50";
		int nOvertime = 5000;
		String strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}

	@Test
	public void test_getranking13() {
		strName = "3.1. 获取排行榜信息（等级）";
		strUrl = strBaseurl+"/ranking/getranking?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&ranking_type=13&user_id=10000162&page_index=0&page_size=50";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	
	
	public static void sendmsg(String strName,String strHead,String strUrl,String strBody,int nOvertime,String strExpect) {

		String strResult = "";
		
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		// 执行一次请求
		strResult = tools.sendMsg(strHead,strUrl,strBody);
		long endMili=System.currentTimeMillis();
		long totleMili = endMili-startMili;
		tools.logPrint(strFilename,"服务：<<<"+strName+">>>总耗时为："+totleMili+"毫秒");
		tools.logPrint(strFilename,strResult);
		if(strResult.indexOf(strExpect) == -1){
			fail("失败：<<<"+strName+">>>:"+strResult+"，预期("+strExpect+")");
		}
		if(totleMili > nOvertime){
			fail("超时：<<<"+strName+">>>:"+totleMili+"毫秒，预期("+nOvertime+")");
		}
	}
}