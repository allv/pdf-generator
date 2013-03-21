/**
 * 模块名： TEXT 处理类
 * 版本： 1.0
 * 说明：  文本
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.UnDefinedException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;

public class TextComponent implements Component {

	
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

	public Object unpack(Element element, Map source, Map preDefine) throws Exception {
		/*
		 * <!ELEMENT text (#PCDATA)* >
		 * <!ATTLIST text font CDATA #IMPLIED >
		 */
		if(element == null ) { 
			return null;
		}
		String font = element.getAttributeValue("font");
		if(font == null || font.trim().equals("")){
			font = "defaultFont";
		}
		Font vFont = (Font) preDefine.get(font);
		if(vFont == null ) { 
			throw new UnDefinedException (font);
		}
		String text = element.getText();
//		if(!text.trim().equals("") && element.getChild("value") != null ){
//			throw new IllegalFormatException("text标签内不允许同时存在文本内容和VALUE标签");
//		}else{
//			if(element.getChild("value") != null){
//				ValueComponent c = new ValueComponent();
//				text = (String) c.unpack(element.getChild("value"), source, preDefine);
//			}
//		}
		Phrase phrase = new Phrase(text,vFont);
		return phrase;
	}

}
