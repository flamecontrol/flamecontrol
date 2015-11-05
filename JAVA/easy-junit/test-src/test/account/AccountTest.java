package test.account;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tools.tools;


public class AccountTest {

	String strName = "";
	String strHead = "";
	String strUrl = "";
	String strBody = "";
	int nOvertime = 0;
	String strExpect = "";
	
	String strResult = "";
	String strBaseurl = "http://192.168.80.7:83";
//	String strBaseurl = "http://account.api.com";
	String strFilename = "./logs/log.txt";

	tools Ctools = new tools();

	@Test
	public void test_allocateuserid() {
		strName = "2.1. 分配用户Id";
		strUrl = strBaseurl+"/account/allocateuserid?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&os_version=4.0.4";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	@Test
	public void test_login() {
		strName = "2.4. 用户登录";
		strUrl = strBaseurl+"/account/login?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&login_name=13332424931&password=123456";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	@Test
	public void test_getuserinfobyuidlist() {
		strName = "2.6. 批量获取用户信息";
		strUrl = strBaseurl+"/account/getuserinfobyuidlist?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&user_ids=20097097,20006734,20006736&fields=\"mobile_no,email,bangyou_name,device_id,nick_name,user_type,status,portrait,gender,gold,exp_points,credit_percent,credit_level,maturities,user_level\"";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	@Test
	public void test_setpassword() {
		strName = "2.7. 设置密码";
		strUrl = strBaseurl+"/account/setpassword?format=json";
		strBody = "app_id=1&app_version=3.1&os_type=2&device_id=abe4d870fcd40b6e&type=0&mobile_no=13332424931&check_old_password=0&user_id=20006726&old_password=&password=123456";
		nOvertime = 5000;
		strExpect = "\"ret\":200";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
//	@Test
//	public void test_() {
//		strName = "";
//		strUrl = strBaseurl+"/account/?format=json";
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
//		System.out.println(strResult);
		if(strResult.indexOf(strExpect) == -1){
			fail("失败：<<<"+strName+">>>:"+strResult);
		}
		if(totleMili > nOvertime){
			fail("超时：<<<"+strName+">>>:"+totleMili+"毫秒("+nOvertime+")");
		}
	}
}