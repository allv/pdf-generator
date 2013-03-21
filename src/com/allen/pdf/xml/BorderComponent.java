/**
 * 模块名： 边框 处理类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.awt.Color;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.IllegalContentException;
import com.lowagie.text.Rectangle;

public class BorderComponent implements Component {

	private Rectangle border = null; ;
	private Map dataSource = null;
	
	private void setDataSource(Map dataSource) {
		this.dataSource = dataSource;
	}
	
	public Object unpack() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source) throws Exception{
		/*
		 * 	<!ELEMENT border EMPTY >
		 *  <!ATTLIST border id CDATA #REQUIRED>
			<!ATTLIST border width CDATA #IMPLIED>
			<!ATTLIST border color CDATA #IMPLIED>
			<!ATTLIST border leftWidth CDATA #IMPLIED>
			<!ATTLIST border bottomWidth CDATA #IMPLIED>
			<!ATTLIST border rightWidth CDATA #IMPLIED>
			<!ATTLIST border topWidth CDATA #IMPLIED>
			<!ATTLIST border leftColor CDATA #IMPLIED>
			<!ATTLIST border bottomColor CDATA #IMPLIED>
			<!ATTLIST border rightColor CDATA #IMPLIED>
			<!ATTLIST border topColor CDATA #IMPLIED>
			<!ATTLIST border width CDATA #IMPLIED>
			<!ATTLIST border class CDATA #IMPLIED>
		 */
		if(element == null ) { 
			return null;
		}
		String className = element.getAttributeValue("class");
		if(className == null || className.trim().equals("")) { 
			this.setDataSource(source);
			String width = element.getAttributeValue("width");
			String leftWidth = element.getAttributeValue("leftWidth");
			String bottomWidth = element.getAttributeValue("bottomWidth");
			String rightWidth = element.getAttributeValue("rightWidth");
			String topWidth = element.getAttributeValue("topWidth");
			String color = element.getAttributeValue("color");
			String leftColor = element.getAttributeValue("leftColor");
			String bottomColor = element.getAttributeValue("bottomColor");
			String rightColor = element.getAttributeValue("rightColor");
			String topColor = element.getAttributeValue("topColor");
			
			if(width == null || width.trim().equals("")) { 
				width="0";
				if(leftWidth == null || leftWidth.trim().equals("")) { 
					leftWidth = "0";
				}
				if(bottomWidth == null || bottomWidth.trim().equals("")) { 
					bottomWidth = "0";
				}
				if(rightWidth == null || rightWidth.trim().equals("")) { 
					rightWidth = "0";
				}
				if(topWidth == null || topWidth.trim().equals("")) { 
					topWidth = "0";
				}
			}else{
				leftWidth = width;
				bottomWidth = width;
				rightWidth = width;
				topWidth = width;
			}
			if(color == null || color.trim().equals("")) { 
				color="white";
				if(leftColor == null || leftColor.trim().equals("")) {
					leftColor = "white";
				}
				if(bottomColor == null || bottomColor.trim().equals("")) {
					bottomColor = "white";
				}
				if(rightColor == null || rightColor.trim().equals("")) {
					rightColor = "white";
				}
				if(topColor == null || topColor.trim().equals("")) {
					topColor = "white";
				}
			}else { 
				leftColor = color;
				bottomColor = color;
				rightColor = color;
				topColor = color;
			}
			border = this.makeBorder(width, color, leftWidth, bottomWidth, rightWidth, topWidth, leftColor, bottomColor, rightColor, topColor);
		}else {
			try {
				border = (Rectangle) Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				border = null;
			} catch (IllegalAccessException e) {
				border = null;
			} catch (ClassNotFoundException e) {
				border = null;
			}
		}
		return border;
	}

	public Object unpack(Element element, Map source, Map preDefine) {
		// TODO Auto-generated method stub
		return null;
	}

	private Rectangle makeBorder(String width, String color,String leftWidth,String bottomWidth,String rightWidth,String topWidth,String leftColor,String bottomColor, String rightColor, String topColor) throws Exception{ 
		float vLeftWidth = 0f;
		float vBottomWidth = 0f;
		float vRightWidth = 0f;
		float vTopWidth = 0f;
		try{
			vLeftWidth = Float.parseFloat(leftWidth);
			vBottomWidth = Float.parseFloat(bottomWidth);
			vRightWidth = Float.parseFloat(rightWidth);
			vTopWidth = Float.parseFloat(topWidth);
		}catch(NumberFormatException e){
			throw new IllegalContentException("BORDER标签内WIDTH属性内容应该为数字");
		}
		Color vLeftColor = Color.WHITE;
		Color vBottomColor = Color.WHITE;
		Color vRightColor = Color.WHITE;
		Color vTopColor = Color.WHITE;
		vLeftColor = (Color)this.dataSource.get(color);
		vBottomColor = (Color)this.dataSource.get(color);
		vRightColor = (Color)this.dataSource.get(color);
		vTopColor = (Color)this.dataSource.get(color);
		
		Rectangle normalBorder = new Rectangle(0f, 0f);
		normalBorder.setBorderWidthLeft(vLeftWidth);
		normalBorder.setBorderWidthBottom(vBottomWidth);
		normalBorder.setBorderWidthRight(vRightWidth);
		normalBorder.setBorderWidthTop(vTopWidth);
		normalBorder.setBorderColorLeft(vLeftColor);
		normalBorder.setBorderColorBottom(vBottomColor);
		normalBorder.setBorderColorRight(vRightColor);
		normalBorder.setBorderColorTop(vTopColor);
		return normalBorder;

	}
}
