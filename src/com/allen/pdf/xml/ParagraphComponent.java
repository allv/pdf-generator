/**
 * 模块名： PARAGRAPH 处理类
 * 版本： 1.0
 * 说明：  段落
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.util.Iterator;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.lowagie.text.Paragraph;

public class ParagraphComponent implements Component {

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
		 * <!ELEMENT paragraph (text|pdf:if|blank)* >
		 * <!ATTLIST paragraph align CDATA  "LEFT">
		 * <!ATTLIST paragraph class CDATA #IMPLIED >
		 */
		if(element == null ){ 
			return null;
		}
		String className = element.getAttributeValue("class"); 
		if(className == null || className.trim().equals("")) { 
			PdfXMLComponentFactory factory = PdfXMLComponentFactory.getInstance();
			String align = element.getAttributeValue("align");
			if(align == null || align.trim().equals("")) { 
				align = "LEFT";
			}
			Paragraph paragraph = new Paragraph();
			Iterator it = element.getChildren().iterator();
			while(it.hasNext()) { 
				Element e = (Element) it.next();
				Component c = factory.getComponent(e.getName());
				paragraph.add(c.unpack(e, source, preDefine));
			}
			return paragraph;
		}else {
			Paragraph paragraph = (Paragraph) Class.forName(className).newInstance();
			return paragraph;
		}
		
	}

}
