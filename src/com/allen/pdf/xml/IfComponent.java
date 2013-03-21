/**
 * 模块名： IF 处理类
 * 版本： 1.0
 * 说明：  判断标签
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.InvalidCompareException;
import com.allen.pdf.util.ConditionUtil;

public class IfComponent implements Component {

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
		 * <!ELEMENT pdf:if (pdf:loop|pdf:if|table|td|paragraph|text|blank|img)* >
		 * <!ATTLIST pdf:if condition CDATA #REQUIRED>
		 */
		if(element == null ) { 
			return null;
		}
		String condition = element.getAttributeValue("condition");
		if(condition == null || condition.trim().equals("")) { 
			return null;
		}
		boolean flag = false;
		try{
			flag = ConditionUtil.handleCondition(condition,source);
		} catch( Exception e ) { 
			throw new InvalidCompareException ("condition："+condition+"异常为："+e.toString());
		}
		ArrayList components = new ArrayList();
		if(flag){
			PdfXMLComponentFactory factory = PdfXMLComponentFactory.getInstance();
			Iterator it = element.getChildren().iterator();
			while(it.hasNext()) { 
				Element e = (Element) it.next();
				Component c = factory.getComponent(e.getName());
				components.add(c.unpack(e, source, preDefine));
			}
		}else {
			components  = null;
		}
		return components;
	}
}
