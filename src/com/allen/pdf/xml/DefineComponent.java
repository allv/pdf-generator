/**
 * 模块名： DEFINE 处理类
 * 版本： 1.0
 * 说明：  此标签内预定义了一些COLOR,BORDER,FONT
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;

public class DefineComponent implements Component {

	private HashMap elements = null;
	
	public DefineComponent () { 
		this.initValue();
	}
	
	public HashMap getElements() {
		return elements;
	}

	public Object unpack() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element) throws Exception{
		/*
		 * <define><color><font><border></define>
		 */
		if(element == null ) { 
			return elements;
		}
		Iterator it = element.getChildren().iterator();
		while(it.hasNext()){ 
			Element e = (Element)it.next();
			String elementName = e.getName();
			String id = e.getAttributeValue("id");
			if(id == null || id.trim().equals("")){
				continue;
			}
			PdfXMLComponentFactory factory = PdfXMLComponentFactory.getInstance();
			Component c =factory.getComponent(elementName);
			elements.put(id, c.unpack(e,elements));
		}
		return elements;
	}

	public Object unpack(Element element, Map source) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source, Map preDefine) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private HashMap initValue() { 
		elements = new HashMap();
		elements.put("defaultBorder", this.getDefaultBorder());
		elements.put("whiteBorder", this.getWhiteBorder());
		elements.put("defaultBackgroundColor", Color.WHITE);
		elements.put("defaultFontColor", Color.BLACK);
		elements.put("defaultFont", this.getDefaultFont());
		elements.put("boldFont", this.getBoldFont());
		elements.put("italicFont",this.getItalicFont());
		elements.put("boldItalicFont",this.getBoldItalicFont());
		elements.put("underlineFont", this.getUnderlineFont());
		
		elements.put("white", new Color(0,0,0));
		elements.put("black", new Color(255,255,255));
		return elements;
	}
	
	private Rectangle getDefaultBorder() {
		Rectangle border = new Rectangle(0f, 0f);
		border.setBorderWidthLeft(0.5f);
		border.setBorderWidthBottom(0.5f);
		border.setBorderWidthRight(0.5f);
		border.setBorderWidthTop(0.5f);
		border.setBorderColorLeft(Color.black);
		border.setBorderColorBottom(Color.black);
		border.setBorderColorTop(Color.black);
		border.setBorderColorRight(Color.black);
		return border;
	}
	
	private Rectangle getWhiteBorder() {
		Rectangle border = new Rectangle(0f, 0f);
		border.setBorderWidthLeft(0f);
		border.setBorderWidthBottom(0f);
		border.setBorderWidthRight(0f);
		border.setBorderWidthTop(0f);
		border.setBorderColorLeft(Color.white);
		border.setBorderColorBottom(Color.white);
		border.setBorderColorTop(Color.white);
		border.setBorderColorRight(Color.white);
		return border;
	}
	
	private Font getDefaultFont() { 
		BaseFont bfChinese = null;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font font = new Font(bfChinese, 8f, Font.NORMAL); 
		return font;
	}
	
	//粗体
	private Font getBoldFont() { 
		BaseFont bfChinese = null;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font font = new Font(bfChinese, 8f, Font.BOLD); 
		return font;
	}
	
	//斜体
	private Font getItalicFont() { 
		BaseFont bfChinese = null;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font font = new Font(bfChinese, 8f, Font.ITALIC); 
		return font;
	}
	
	//粗斜体
	private Font getBoldItalicFont() { 
		BaseFont bfChinese = null;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font font = new Font(bfChinese, 8f, Font.BOLDITALIC); 
		return font;
	}
	
	//下划线
	private Font getUnderlineFont() { 
		BaseFont bfChinese = null;
		try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font font = new Font(bfChinese, 8f, Font.UNDERLINE); 
		return font;
	}
}
