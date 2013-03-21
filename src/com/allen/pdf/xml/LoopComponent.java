/**
 * ģ������ LOOP ������
 * �汾�� 1.0
 * ˵����  ѭ����ǩ
 * ��д�ߣ�   lvjun  
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
 */
package com.allen.pdf.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.IllegalContentException;

public class LoopComponent implements Component {

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
		 * <!ELEMENT pdf:loop (pdf:loop|pdf:if|table|td|paragraph|img)* >
		 * <!ATTLIST pdf:loop tagId CDATA #REQUIRED>
		 * <!ATTLIST pdf:loop count CDATA #IMPLIED>
		 */
		if(element == null ) { 
			return null;
		}
		String tagId = element.getAttributeValue("tagId");
		if(tagId == null || tagId.trim().equals("")){
			return null;
		}
		String count = element.getAttributeValue("count");
		if(count==null || count.trim().equals("") ) { 
			count = "-1";
		}
		int vCount = -1;
		try{
			vCount = Integer.parseInt(count);
		} catch(NumberFormatException e) { 
			throw new IllegalContentException("LOOP��ǩ��COUNT��������Ӧ��Ϊ����");
		}
		ArrayList components = new ArrayList();
		Object tagValue = source.get(tagId);
		if(tagValue != null ){
			//��ʼ��ѭ������Ϊ0
			source.put("loopIndex", "0");
			
			PdfXMLComponentFactory factory = PdfXMLComponentFactory.getInstance();
			if(tagValue instanceof String){
				Iterator it = element.getChildren().iterator();
				while(it.hasNext()) { 
					Element e = (Element) it.next();
					Component c = factory.getComponent(e.getName());
					components.add(c.unpack(e, source, preDefine));
				}
			}else if(tagValue instanceof List) {
				List tagValueList = (List) tagValue;
				int length = tagValueList.size();
				int i = 0;
				while(i < length) { 
					if( i >= vCount && vCount != -1 ){
						break;
					}
					source.put("loopIndex", String.valueOf(i));
					Iterator it1 = element.getChildren().iterator();
					while(it1.hasNext()) { 
						Element e = (Element) it1.next();
						Component c = factory.getComponent(e.getName());
						components.add(c.unpack(e, source, preDefine));
					}
					i++;
				}
			}
		}else{
			components = null;
		}
		
		return components;
	}

}
