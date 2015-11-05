package test.wblk;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tools.tools;


public class wblkTest {

	String strName = "";
	String strHead = "";
	String strUrl = "";
	String strBody = "";
	int nOvertime = 0;
	String strExpect = "";
	
	String strResult = "";
	String strBaseurl = "http://192.168.80.4";
//	String strBaseurl = "wxlk.chetuobang.com";
	String strFilename = "./logs/log.txt";

	tools Ctools = new tools();

	@Test
	public void test_configversion() {
		strName = "（一）接口版本信息";
		strUrl = strBaseurl+"/web_weixinlukuang/index.php?c=regulations&m=province_city_list";
		strBody = "app_id=1";
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
			fail("失败：<<<"+strName+">>>:"+strResult);
		}
		if(totleMili > nOvertime){
			fail("超时：<<<"+strName+">>>:"+totleMili+"毫秒("+nOvertime+")");
		}
	}
}