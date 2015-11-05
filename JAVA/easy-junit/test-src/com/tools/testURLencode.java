package com.tools;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class testURLencode {
	public static void main(String[] args) throws Exception {
//		System.out.println("请输入：");
//        //数组缓冲
//        byte[] b = new byte[1024];
//        //读取数据
//        int n = System.in.read(b);
//        //转换为字符串
//        String s = new String(b,0,n);
//        
//        String ss = s;
//        
//		System.out.println(ss);

		
		System.out.println("%E8%B6%85%E7%BA%A7%E7%89%9B%E7%89%9B");
		
		System.out.println(URLEncoder.encode("超级牛牛" , "UTF-8"));
		
		System.out.println(URLDecoder.decode("%E8%B6%85%E7%BA%A7%E7%89%9B%E7%89%9B", "UTF-8"));
		
	}
}