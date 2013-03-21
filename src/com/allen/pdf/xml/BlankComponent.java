/**
 * 模块名： 空格 处理类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.IllegalContentException;
import com.lowagie.text.Phrase;

public class BlankComponent implements Component {

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
		 * <!ELEMENT blank EMPTY >
		 * <!ATTLIST blank count CDATA "1">
		 */
		if(element == null ){ 
			return "";
		}
		String count = element.getAttributeValue("count");
		if(count == null || count.trim().equals("")) { 
			return "";
		}
		int vCount = 0;
		try{
			vCount = Integer.parseInt(count);
		}catch(NumberFormatException e) { 
			throw new IllegalContentException("BLANK标签内COUNT属性内容应该为数字");
		}
		StringBuffer ss = new StringBuffer("");
		for(int i = 0 ;i < vCount ; i++ ){
			ss.append(" ");
		}
		return new Phrase(ss.toString());
	}

}
