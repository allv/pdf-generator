/**
 * 模块名： HEADER 处理类
 * 版本： 1.0
 * 说明：  页头
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.UnDefinedException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;

public class HeaderComponent implements Component {

	public Object unpack() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source, Map preDefine)
			throws Exception {
		/*
		 * <!ELEMENT header (before,after)>
		 * <!ATTLIST header align CDATA "RIGHT">
		 * <!ATTLIST header isDisplayPage CDATA #IMPLIED>
		 * <!ATTLIST header class CDATA #IMPLIED>
		 */
		if(element == null ){ 
			return null;
		}
		String className = element.getAttributeValue("class");
		if(className == null || className.trim().equals("")) { 
			String align = element.getAttributeValue("align");
			String isDisplayPage = element.getAttributeValue("isDisplayPage");
			String font = element.getAttributeValue("font");
			
			if(align == null || align.trim().equals("")) { 
				align = "CENTER";
			}
			if(isDisplayPage == null || isDisplayPage.trim().equals("")) { 
				isDisplayPage = "TRUE";
			}
			if(font == null || font.trim().equals("")){
				font = "defaultFont";
			}
			Font vFont = (Font) preDefine.get(font);
			if(vFont == null ) { 
				throw new UnDefinedException (font);
			}
			Element before = element.getChild("before");
			Element after = element.getChild("after");
			Phrase vBefore = new Phrase("",vFont);
			Phrase vAfter = new Phrase("",vFont);
			ArrayList beforeComponents = new ArrayList();
			ArrayList afterComponents = new ArrayList();
			PdfXMLComponentFactory factory = PdfXMLComponentFactory.getInstance();
			//处理before标签内的内容
			Iterator beforeIt = before.getChildren().iterator();
			while(beforeIt.hasNext()) { 
				Element e = (Element) beforeIt.next();
				Component c = factory.getComponent(e.getName());
				beforeComponents.add(c.unpack(e, source, preDefine));
			}
			//处理after标签内的内容
			Iterator afterIt = after.getChildren().iterator();
			while(afterIt.hasNext()) { 
				Element e = (Element) afterIt.next();
				Component c = factory.getComponent(e.getName());
				afterComponents.add(c.unpack(e, source, preDefine));
			}
			vBefore = this.handleComponents(vBefore, beforeComponents);
			vAfter = this.handleComponents(vAfter, afterComponents);
			return this.makeHeader(align, vBefore, vAfter,isDisplayPage);
		}else { 
			HeaderFooter header = (HeaderFooter) Class.forName(className).newInstance();
			return header;
		}
	}

	private Phrase handleComponents(Phrase p , List components) { 
		Iterator it = components.iterator();
		while( it.hasNext() ){
			Object o = it.next();
			if(o instanceof com.lowagie.text.Element) { 
				p.add((com.lowagie.text.Element)o);
			}else if( o instanceof List) { 
				p = handleComponents(p,(List)o);
			}
		}
		return p ;
	}
	
	private HeaderFooter makeHeader(String align,Phrase before,Phrase after,String isDisplayPage){
		int vAlign = com.lowagie.text.Element.ALIGN_RIGHT;
		if(align.equals("LEFT")) { 
			vAlign = com.lowagie.text.Element.ALIGN_LEFT;
		}else if(align.equals("CENTER")) {
			vAlign = com.lowagie.text.Element.ALIGN_CENTER;
		}
		HeaderFooter header = null ;
		if(isDisplayPage.equals("FALSE")){
			header = new HeaderFooter(before,false);
		}else { 
			header = new HeaderFooter(before,after);
		}
		header.setBorder(Rectangle.NO_BORDER);
		header.setAlignment(vAlign);
		return header;
	}
	
}
