/**
 * 模块名： COLOR 处理类
 * 版本： 1.0
 * 说明：  
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.awt.Color;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.IllegalContentException;

public class ColorComponent implements Component {

	private Color color = null;
	
	public Object unpack() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element) throws Exception{
		//<color R="" G="" B="" class="" />
		if(element == null ) { 
			return null;
		}
		String className = element.getAttributeValue("class");
		if(className == null || className.trim().equals("")) { 
			String r = element.getAttributeValue("R");
			String g = element.getAttributeValue("G");
			String b = element.getAttributeValue("B");
			int r1 = 0;
			int g1 = 0;
			int b1 = 0;
			try{
				r1 = Integer.parseInt(r);
				g1 = Integer.parseInt(g);
				b1 = Integer.parseInt(b);
			} catch(NumberFormatException e ) { 
				throw new IllegalContentException("COLOR标签内R,G,B属性内容应该为数字");
			}
			color =new Color(r1,g1,b1);
		}else{
			try {
				color = (Color) Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				color = null;
			} catch (IllegalAccessException e) {
				color = null;
			} catch (ClassNotFoundException e) {
				color = null;
			}
		}
		return color;
	}

	public Object unpack(Element element, Map source) throws Exception{
		return this.unpack(element);
	}

	public Object unpack(Element element, Map source, Map preDefine) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
