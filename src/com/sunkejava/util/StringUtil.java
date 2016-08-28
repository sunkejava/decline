package com.sunkejava.util;

public class StringUtil {
	/**
	 * 判断字符串是负为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断字符串是否不是空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(str != null && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 取文本左边
	 * @param str
	 * @param leftstr
	 * @return
	 */
	public static String leftString(String str,String leftstr ){
		String lstr = null;
		//返回左边文本的第一个字符位置
		int endInt = str.indexOf(leftstr);
		lstr = str.substring(0, endInt);
		return lstr;
	}
	/**
	 * 取文本右边
	 * @param str
	 * @param rightstr
	 * @return
	 */
	public static String rightString(String str,String rightstr ){
		String rstr = null;
		//返回右边文本的最后一个字符位置
		int beginInt = str.lastIndexOf(rightstr)+rightstr.length();
		rstr = str.substring(beginInt);
		return rstr;
	}
	/**
	 * 取文本中间
	 * @param str
	 * @param leftstr
	 * @param rightstr
	 * @return
	 */
	public static String betweenSting(String str,String leftstr,String rightstr ){
		String bstr = null;
		//返回左边文本的最后一个字符位置
		int beginInt = str.lastIndexOf(leftstr)+leftstr.length();
		//返回右边文本地第一个位置
		int endInt = str.indexOf(rightstr);
		bstr = str.substring(beginInt,endInt);
		return bstr;
	}
}
