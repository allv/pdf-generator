/**
 * 模块名： TD 处理类
 * 版本： 1.0
 * 说明：  
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.IllegalFormatException;
import com.allen.pdf.exception.UnDefinedException;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;

public class TdComponent implements Component {

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
		 * <!ELEMENT td (text|blank|table|img|if|loop)* >
		 * <!ATTLIST td vAlign CDATA "MIDDLE">
		 * <!ATTLIST td hAlign CDATA "LEFT">
		 * <!ATTLIST td colspan CDATA #IMPLIED>
		 * <!ATTLIST td font CDATA #IMPLIED>
		 * <!ATTLIST td border CDATA #IMPLIED>
		 * <!ATTLIST td padding CDATA #IMPLIED>
		 * <!ATTLIST td backgroundColor CDATA #IMPLIED >
		 * <!ATTLIST td class CDATA #IMPLIED >
		 */
		if(element == null ) { 
			return null;
		}
		String className = element.getAttributeValue("class");
		if(className == null || className.equals("")) { 
			String vAlign = element.getAttributeValue("valign");
			String hAlign = element.getAttributeValue("halign");
			String colspan = element.getAttributeValue("colspan");
			String border = element.getAttributeValue("border");
			String padding = element.getAttributeValue("padding");
			String backgroundColor = element.getAttributeValue("backgroundColor");
			if(vAlign == null || vAlign.trim().equals("")) { 
				vAlign = "MIDDLE";
			}
			if(hAlign == null || hAlign.trim().equals("")) { 
				hAlign = "LEFT";
			}
			if(border == null || border.trim().equals("")) {
				border = "defaultBorder";
			}
			if(backgroundColor == null || backgroundColor.trim().equals("")) {
				backgroundColor = "defaultBackgroundColor";
			}
			if(colspan == null || colspan.trim().equals("")) { 
				colspan = "1";
			}
			if(padding == null || padding.trim().equals("")) { 
				padding = "-1";
			}
			PdfPCell cell = this.makeCell(vAlign.toUpperCase(), hAlign.toUpperCase(), border,padding,backgroundColor, colspan, preDefine);
			PdfXMLComponentFactory factory = PdfXMLComponentFactory.getInstance();
			ArrayList components = new ArrayList();
			Iterator it = element.getChildren().iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				Component c = factory.getComponent(e.getName());
				components.add((c.unpack(e, source, preDefine)));
			}
			cell = this.handleComponents(cell, components);
			return cell;
		}else {
			PdfPCell p = (PdfPCell) Class.forName(className).newInstance();
			return p;
		}
	}

	private PdfPCell handleComponents(PdfPCell cell, List components) { 
		Iterator it = components.iterator();
		while(it.hasNext()){
			Object o = it.next();
			if(o instanceof Phrase) {
				Phrase newPhrase = (Phrase)o;
				Phrase temp = cell.getPhrase();
				if(temp != null ) {
					temp.add(newPhrase);
					cell.setPhrase(temp);
				}else {
					cell.setPhrase(newPhrase);
				}
			}else if( o instanceof List) { 
				cell = handleComponents(cell, components);
			}else if( o instanceof com.lowagie.text.Element) { 
				cell.addElement((com.lowagie.text.Element)o);
			}
		}
		return cell;
	}
	
	private PdfPCell makeCell(String vAlignment, String hAlignment,String border,String padding,String backgroundColor,String colspan,Map preDefine) throws UnDefinedException, IllegalFormatException {
		PdfPCell cell = null;
		int vHAlignment = com.lowagie.text.Element.ALIGN_LEFT;
		int vVAlignment = com.lowagie.text.Element.ALIGN_MIDDLE;
		if(hAlignment.equals("CENTER")) {
			vHAlignment = com.lowagie.text.Element.ALIGN_CENTER;
		}else if(hAlignment.equals("RIGHT")) {
			vHAlignment = com.lowagie.text.Element.ALIGN_RIGHT;
		}
		if(vAlignment.equals("TOP")) { 
			vVAlignment = com.lowagie.text.Element.ALIGN_TOP;
		}else if (vAlignment.equals("BOTTOM")) { 
			vVAlignment = com.lowagie.text.Element.ALIGN_BOTTOM;
		}
		Rectangle vBorder = (Rectangle) preDefine.get(border);
		if(vBorder == null ) { 
			throw new UnDefinedException(border);
		}
		float vPadding = 1f;
		try{
			vPadding = Float.parseFloat(padding);
		} catch(NumberFormatException e){ 
			throw new IllegalFormatException("TD标签属性PADDING应该为数字");
		}
		Color vBackgroundColor = (Color) preDefine.get(backgroundColor);
		if(vBackgroundColor == null ) { 
			throw new UnDefinedException(padding);
		}
		int vColspan = 1;
		try{
			vColspan = Integer.parseInt(colspan);
		} catch(NumberFormatException e) { 
			throw new IllegalFormatException("TD标签属性COLSPAN应该为数字");
		}
		if(border.equals("defaultBorder")) { 
			cell = this.makeBaseCell(vVAlignment, vHAlignment, vBackgroundColor, vColspan,vPadding);
		}else {
			cell = this.makeBaseCell(vVAlignment, vHAlignment, vBorder, vBackgroundColor, vColspan,vPadding);
		}
		return cell;
	}
	
	private PdfPCell makeBaseCell (int vAlignment, int hAlignment,Color backgroundColor,int cellSpan,float padding) {
		PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(vAlignment);
        cell.setHorizontalAlignment(hAlignment);
        cell.setBackgroundColor(backgroundColor);
        cell.setColspan(cellSpan);
        if(padding != -1){
			cell.setPadding(padding);
		}
        return cell;
	}
	
	private PdfPCell makeBaseCell (int vAlignment, int hAlignment,Rectangle border,Color backgroundColor,int cellSpan,float padding) {
		PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(vAlignment);
        cell.setHorizontalAlignment(hAlignment);
        cell.setBackgroundColor(backgroundColor);
        cell.setColspan(cellSpan);
		cell.cloneNonPositionParameters(border);
		if(padding != -1){
			cell.setPadding(padding);
		}
        return cell;
	}
	
}
