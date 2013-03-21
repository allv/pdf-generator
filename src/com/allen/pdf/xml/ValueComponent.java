/**
 * 模块名： VALUE 处理类
 * 版本： 1.0
 * 说明：  取值标签
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.UnDefinedException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;

public class ValueComponent implements Component {

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

	public Object unpack(Element element, Map source, Map preDefine) throws Exception {
		/*
		 * <!ELEMENT pdf:value EMPTY >
		 * <!ATTLIST value font CDATA #IMPLIED>
		 * <!ATTLIST pdf:value tagId CDATA #REQUIRED>
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
		String tagId = element.getAttributeValue("tagId");
		if(tagId == null || tagId.trim().equals("")) { 
			return new String("");
		}
		Object o =  source.get(tagId);
		String value = "";
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
		}
		Phrase phrase = new Phrase(value,vFont);
		return phrase;
	}

}
