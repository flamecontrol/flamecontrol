package com.tools;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class UGCPOST {
	PostMethod httppost = null;
	HttpClient httpclient = new HttpClient();
	tools Ctools = new tools();
	
	public UGCPOST(String strBaseurl,String strLogin){
//		System.out.println("**************");

		String strHead = "";
		String strUrl = "";
		String strBody = "";
		
		//握手
		strUrl = strBaseurl + "/ctb_test/index.php?c=user&m=hs&d=passport";
		strBody = "platform=2&udid=484faeefdddba9f1&os=2.3.6&channel=36O&config_ver=1&ver=3.0.9";
		sendCode(strHead,strUrl,strBody);
		
		//登录
		strUrl = strBaseurl + "/ctb_test/index.php?c=user&m=login&d=passport";
		sendCode(strHead,strUrl,strLogin);		
	}


	public static void main(String[] args) throws Exception {
		PostMethod httppost = null;
		HttpClient httpclient = new HttpClient();
		httpclient.getParams().setSoTimeout(10 * 60 * 1000);// 设置超时时间
		String url = "http://www.google.com.hk/";
		httppost = new PostMethod(url);
		httppost.addParameter("id", "1");// 传递参数[POST]
		// httppost.setRequestBody("app_id=1");
		httpclient.executeMethod(httppost);

		byte[] by = httppost.getResponseBody();

		String response = new String(by);
		System.out.println(response);
	}

	public String sendCode(String strHead, String strUrl, String strBody) {
		httpclient.getParams().setSoTimeout(10 * 60 * 1000);// 设置超时时间
		strUrl = Ctools.sendCode(strHead, strUrl, strBody);
		httppost = new PostMethod(strUrl);
		String body[] = strBody.split("&");
		for(int i = 0;i < body.length;i++){
			String info[] = body[i].split("=");
			httppost.addParameter(info[0], info[1]);//传递参数[POST]
		}		
		String response = "";

//		System.out.println(strBody);

		try {
			httpclient.executeMethod(httppost);
			byte[] by = httppost.getResponseBody();
			response = new String(by);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(response);
		return response;
	}
}
