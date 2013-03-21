/**
 * 模块名： PDF控件工厂
 * 版本： 1.0
 * 说明：  把控件名称转为控件处理类
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import com.allen.pdf.base.Component;

public class PdfXMLComponentFactory {

	private static PdfXMLComponentFactory factory = new PdfXMLComponentFactory();
	
	private PdfXMLComponentFactory(){}
	
	public static PdfXMLComponentFactory getInstance() {
		if (factory == null ) { 
			factory = new PdfXMLComponentFactory();
		}
		return factory;
	}
	
	public Component getComponent(String name) {
		Component c;
		if(name == null || name.trim().equals("")){
			return null;
		}
		try {
			String classPath = "com.allen.pdf.xml.";
			String firstCharacter = name.substring(0,1);
			String otherCharacters = name.substring(1);
			name = classPath + firstCharacter.toUpperCase()+otherCharacters+"Component";
			c = (Component) Class.forName(name).newInstance();
		} catch (InstantiationException e) {
			c = null;
		} catch (IllegalAccessException e) {
			c = null;
		} catch (ClassNotFoundException e) {
			c = null;
		}
		return c;
	}
}
