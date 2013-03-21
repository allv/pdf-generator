/**
 * 模块名： LOOP 处理类
 * 版本： 1.0
 * 说明：  循环标签
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
			throw new IllegalContentException("LOOP标签内COUNT属性内容应该为数字");
		}
		ArrayList components = new ArrayList();
		Object tagValue = source.get(tagId);
		if(tagValue != null ){
			//初始化循环索引为0
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
