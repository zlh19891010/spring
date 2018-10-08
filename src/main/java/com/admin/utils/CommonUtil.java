package com.admin.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件名称： com.admin.utils.CommonUtil.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月13日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 *
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者        日期       修改内容<br/>
 *
 *
 * ================================================<br/>
 *  Copyright (c) 2010-2011 .All rights reserved.<br/>
 */
public class CommonUtil {

	static Map<String,Boolean> ipMap =new HashMap<String,Boolean>();
	static {
		ipMap.put("211.151.20.126",true);
		ipMap.put("211.151.20.127",true);
		ipMap.put("117.121.57.82",true);
	}


	/**
	 *
	 *
	 * 方法描述: [验证IP.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月14日-下午9:32:09<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param ip
	 * @return
	 * boolean
	 *
	 */
	public static boolean checkIp(HttpServletRequest request){
		String ip=getRemoteHost(request);
		return ipMap.get(ip)==null?false:ipMap.get(ip);

	}

	private  static String getRemoteHost(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}



	public static String originSign = "";
	/**
	 *
	 *
	 * 方法描述: [生产不重复的8位授权码.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月13日-下午9:58:37<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * String
	 *
	 */
	public static String getAuthCode(){
		char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P','Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z','0','1','2','3','4','5','6','7','8','9'};
		boolean[] flags = new boolean[letters.length];
		char[] chs = new char[8];
		for (int i = 0; i < chs.length; i++) {
			int index;
			do {
				index = (int) (Math.random() * letters.length);
			} while (flags[index]);// 判断生成的字符是否重复
			chs[i] = letters[index];
			flags[index] = true;
		}
		return new String(chs);
	}

	/**
	 *
	 *
	 * 方法描述: [生产不重复的8位授权码.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月13日-下午9:58:37<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @return
	 * String
	 *
	 */
	public static String getAuthCode2(){
		char[] letters = { '0','1','2','3','4','5','6','7','8','9'};
		boolean[] flags = new boolean[letters.length];
		char[] chs = new char[8];
		for (int i = 0; i < chs.length; i++) {
			int index;
			do {
				index = (int) (Math.random() * letters.length);
			} while (flags[index]);// 判断生成的字符是否重复
			chs[i] = letters[index];
			flags[index] = true;
		}
		return new String(chs);
	}

	/**
	 * 从 HTTP请求参数 生成待签字符串, 此方法需要在 serverlet 下测试, 测试的时候取消注释, 引入该引入的类
	 */
	public static String getValues(HttpServletRequest request){

		Enumeration<String> requestParams=request.getParameterNames();//获得所有的参数名
		List<String> params=new ArrayList<String>();
		while (requestParams.hasMoreElements()) {
			params.add(requestParams.nextElement());
		}
		sortParamNames(params);// 将参数名从小到大排序，结果如：adfd,bcdr,bff,zx

		String paramValues="";
		for (String param : params) {//拼接参数值
			if (param.equals("sign")) {
				originSign = request.getParameter(param);
				continue;
			}
			String paramValue=request.getParameter(param);
			if (paramValue!=null) {
				paramValues+=paramValue;
			}
		}

		return paramValues;
	}





	/**
	 * 将参数名从小到大排序，结果如：adfd,bcdr,bff,zx
	 *
	 * @param List<String> paramNames
	 */
	public static void sortParamNames(List<String> paramNames) {
		Collections.sort(paramNames, new Comparator<String>() {
			@Override
			public int compare(String str1,String str2) {
				return str1.compareTo(str2);
			}
		});
	}



	/**

	 * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回

	 * @param sourceDate

	 * @param formatLength

	 * @return 重组后的数据

	 */

	public static String frontCompWithZore(int sourceDate,int formatLength)
	{
		/*

		 * 0 指前面补充零

		 * formatLength 字符总长度为 formatLength

		 * d 代表为正数。

		 */

		String newString = String.format("%0"+formatLength+"d", sourceDate);

		return newString;

	}
	public static void main(String[] args) {

		System.out.println(frontCompWithZore(5,6));
	}
}
