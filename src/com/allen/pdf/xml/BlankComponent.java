/**
 * ģ������ �ո� ������
 * �汾�� 1.0
 * ˵����  
 * ��д�ߣ�     lvjun
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
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
			throw new IllegalContentException("BLANK��ǩ��COUNT��������Ӧ��Ϊ����");
		}
		StringBuffer ss = new StringBuffer("");
		for(int i = 0 ;i < vCount ; i++ ){
			ss.append(" ");
		}
		return new Phrase(ss.toString());
	}

}
