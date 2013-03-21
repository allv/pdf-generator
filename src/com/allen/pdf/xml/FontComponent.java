/**
 * 模块名： FONT 处理类
 * 版本： 1.0
 * 说明：  
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.awt.Color;
import java.io.IOException;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.allen.pdf.exception.IllegalContentException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public class FontComponent implements Component {

	private Font font = null;
	private Map dataSource = null;
	
	public Object unpack() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source) throws Exception{
		//<font id="" font-family="" encoding="" EMBEDDED="true" font-size="" font-color="" style="" class="" />
		if(element == null ) { 
			return null;
		}
		String className = element.getAttributeValue("class");
		if(className == null || className.trim().equals("")) { 
			this.setDataSource(source);
			String fontFamily = element.getAttributeValue("font-family");
			String fontSize = element.getAttributeValue("font-size");
			String encoding = element.getAttributeValue("encoding");
			String embedded = element.getAttributeValue("EMBEDDED");
			String style = element.getAttributeValue("style");
			String color = element.getAttributeValue("font-color");
			if(fontFamily == null || fontFamily.trim().equals("")) { 
				fontFamily = "STSong-Light";
			}
			if(fontSize == null || fontSize.trim().equals("")) { 
				fontSize = "8";
			}
			if(encoding == null || encoding.trim().equals("")) { 
				encoding = "UniGB-UCS2-H";
			}
			if(embedded == null || embedded.trim().equals("")) { 
				embedded = "TRUE";
			}
			if(style == null || style.trim().equals("")) { 
				style= "NORMAL";
			}
			font = this.makeFont(fontFamily, encoding, embedded, fontSize, style.toUpperCase(),color);
		}else{
			try {
				font = (Font) Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				font = null;
			} catch (IllegalAccessException e) {
				font = null;
			} catch (ClassNotFoundException e) {
				font = null;
			}
		}
		return font;
	}

	public Object unpack(Element element, Map source, Map preDefine) {
		// TODO Auto-generated method stub
		return null;
	}

	private Font makeFont(String fontFamily, String encoding, String embedded, String fontSize, String style, String color) throws Exception{
		float size = 0f;
		try{
			size = Float.parseFloat(fontSize);
		}catch(NumberFormatException e ) { 
			throw new IllegalContentException("FONT标签内SIZE属性内容应该为数字");
		}
		int style1 = 1;
		Color c = Color.BLACK;
		if(color == null || color.trim().equals("")) { 
			c = Color.BLACK;
		}else{
			c = (Color)this.dataSource.get(color);
			if(c== null ){ 
				c = Color.BLACK;
			}
		}
		if(style.equals("NORMAL") ){
			style1 = Font.NORMAL;
		}else if(style.equals("UNDERLINE")) {
			style1 = Font.UNDERLINE;
		}else if(style.equals("BOLD")) {
			style1 = Font.BOLD;
		}else if(style.equals("BOLDITALIC")){
			style1 = Font.BOLDITALIC;
		}else if( style.equals("ITALIC")) { 
			style1 = Font.ITALIC;
		}
		try {
			font = new Font(BaseFont.createFont(fontFamily, encoding, embedded.toUpperCase().equals("TRUE")?BaseFont.EMBEDDED:BaseFont.NOT_EMBEDDED),size,style1,c);
		} catch (DocumentException e) {
			font = null;
		} catch (IOException e) {
			font = null;
		}  
		return font;
	}
	
	private void setDataSource(Map dataSource) {
		this.dataSource = dataSource;
	}
}
