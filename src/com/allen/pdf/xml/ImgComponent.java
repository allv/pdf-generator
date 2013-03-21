/**
 * 模块名： IMG 处理类
 * 版本： 1.0
 * 说明：  图片
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.IllegalContentException;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

public class ImgComponent implements Component {

	private Image image = null;
	
	public Object unpack() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source, Map preDefine) throws Exception{
		/*
		 * <!ELEMENT img EMPTY >
		 * <!ATTLIST img src CDATA #REQUIRED>
		 * <!ATTLIST img align CDATA "CENTER">
		 * <!ATTLIST img widthPercentage CDATA #IMPLIED>
		 * <!ATTLIST img class CDATA #IMPLIED>
		 */
		if(element == null ) { 
			return null;
		}
		String className = element.getAttributeValue("class");
		if(className == null || className.trim().equals("")) { 
			String src = element.getAttributeValue("src");
			if(src == null || src.trim().equals("")){
				image = null;
			}else{
				try {
					image = Image.getInstance(src);
				} catch (BadElementException e) {
					image = null;
				} catch (MalformedURLException e) {
					image = null;
				} catch (IOException e) {
					image = null;
				}
			}
			String widthPercentage = element.getAttributeValue("widthPercentage");
			if(widthPercentage == null || widthPercentage.trim().equals("")) { 
				widthPercentage = "100";
			}
			float vWidthPercentage = 0f;
			try{
				vWidthPercentage = Float.parseFloat(widthPercentage);
			} catch(NumberFormatException e) { 
				throw new IllegalContentException("IMAGE标签内WidthPercentage属性内容应该为数字");
			}
			String align = element.getAttributeValue("align").toUpperCase();
			int vAlign = com.lowagie.text.Element.ALIGN_LEFT;
			if(align.equals("CENTER")) {
				vAlign = com.lowagie.text.Element.ALIGN_CENTER;
			}else if(align.equals("RIGHT")) {
				vAlign = com.lowagie.text.Element.ALIGN_RIGHT;
			}else{
				vAlign = com.lowagie.text.Element.ALIGN_LEFT;
			}
			if(image != null){
				image.setWidthPercentage(vWidthPercentage);
				image.setAlignment(vAlign);
			}
			
		}else { 
			try {
				image = (Image) Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				image = null;
			} catch (IllegalAccessException e) {
				image = null;
			} catch (ClassNotFoundException e) {
				image = null;
			}
		}
		return image;
	}

}
