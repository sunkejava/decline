package com.sunkejava.util;

public class StringUtil {
	/**
	 * �ж��ַ����Ǹ�Ϊ��
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
	 * �ж��ַ����Ƿ��ǿ�
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
	 * ȡ�ı����
	 * @param str
	 * @param leftstr
	 * @return
	 */
	public static String leftString(String str,String leftstr ){
		String lstr = null;
		//��������ı��ĵ�һ���ַ�λ��
		int endInt = str.indexOf(leftstr);
		lstr = str.substring(0, endInt);
		return lstr;
	}
	/**
	 * ȡ�ı��ұ�
	 * @param str
	 * @param rightstr
	 * @return
	 */
	public static String rightString(String str,String rightstr ){
		String rstr = null;
		//�����ұ��ı������һ���ַ�λ��
		int beginInt = str.lastIndexOf(rightstr)+rightstr.length();
		rstr = str.substring(beginInt);
		return rstr;
	}
	/**
	 * ȡ�ı��м�
	 * @param str
	 * @param leftstr
	 * @param rightstr
	 * @return
	 */
	public static String betweenSting(String str,String leftstr,String rightstr ){
		String bstr = null;
		//��������ı������һ���ַ�λ��
		int beginInt = str.lastIndexOf(leftstr)+leftstr.length();
		//�����ұ��ı��ص�һ��λ��
		int endInt = str.indexOf(rightstr);
		bstr = str.substring(beginInt,endInt);
		return bstr;
	}
}
