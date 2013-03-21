/**
 * 模块名： 工具类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.util;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class PdfUtil {

	/*
	 * 对于可动态定义的属性，解析出报元的值
	 * @param value:attribute中的值
	* @param source: 数据集
	 */
	public static String convertAttributeValue(String value, Map source) { 
		if(value != null ) {
			value = value.trim();
			if(value.startsWith("$")) { 
				//报元的形式
				String tagId = value.substring(1);
				if(tagId == null || tagId.trim().equals("")) { 
					return new String("");
				}
				Object o =  source.get(tagId);
				if(o != null ){
					if(o instanceof String){
						value = (String) o;
					}else if(o instanceof List) {
						String loopIndex = (String) source.get("loopIndex");
						if( loopIndex == null ){
							loopIndex = "0";
						}
						int index = Integer.parseInt(loopIndex);
						try{
							value = (String) ((List) o).get(index);
						} catch( IndexOutOfBoundsException e) {
							value = "";
						}
					}
				}else { 
					value = "";
				}
			}
		}
		return value;
	}
	
	public static String convertGbkToIso(String str){
		String temp = "";
		try{
			temp = new String(str.getBytes("ISO8859_1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			temp = str;
		}
		return temp;
	}

	public static String getPropPath() {
	    return "pdf.properties";
	}
}
