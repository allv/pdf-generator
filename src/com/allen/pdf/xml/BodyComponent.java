/**
 * 模块名： BODY 处理类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.allen.pdf.base.Component;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Rectangle;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.PdfPageEvent;
import com.lowagie.text.pdf.PdfWriter;

public class BodyComponent implements Component {

	private OutputStream outputStream;
	
	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public Object unpack() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object unpack(Element element, Map source, Map preDefine) throws Exception{
		
		if(element == null ) { 
			return null;
		}
		String author = element.getAttributeValue("author");
		String title = element.getAttributeValue("title");
		String subject = element.getAttributeValue("subject");
		String keywords = element.getAttributeValue("keywords");
		String pageSize = element.getAttributeValue("pageSize");
		String isRotate = element.getAttributeValue("isRotate");
		String marginLeft = element.getAttributeValue("margin-left");
		String marginBottom = element.getAttributeValue("margin-bottom");
		String marginTop = element.getAttributeValue("margin-top");
		String marginRight = element.getAttributeValue("margin-right");
		String diplayTotalPageClass = element.getAttributeValue("diplayTotalPageClass");
		
		List components = new ArrayList();
		HeaderFooter header = null;
		HeaderFooter footer = null;
		Iterator it = element.getChildren().iterator();
		PdfXMLComponentFactory factory = PdfXMLComponentFactory.getInstance();
		while(it.hasNext()) { 
			Element e = (Element) it.next();
			if(e.getName().equals("header")) {
				Component c = factory.getComponent(e.getName());
				header = (HeaderFooter) c.unpack(e,source,preDefine);
				c = null;
			}else if(e.getName().equals("footer")){ 
				Component c = factory.getComponent(e.getName());
				footer = (HeaderFooter) c.unpack(e,source,preDefine);
				c = null;
			}else { 
				Component c = factory.getComponent(e.getName());
				components.add(c.unpack(e,source,preDefine));
				c = null;
			}
		}
		this.initDocument(author, title, subject, keywords, pageSize, isRotate, marginLeft, marginRight, marginTop, marginBottom,diplayTotalPageClass, header, footer,components);
		return null;
	}

	
	private void initDocument(String author,String title,String subject,String keywords,String pageSize,String isRotate,String marginLeft,String marginRight,String marginTop,String marginBottom,String diplayTotalPageClass,HeaderFooter header,HeaderFooter footer,List components) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		PdfPageEvent event = null; //显示页面总页数的接口
		
		if(author == null || author.trim().equals("")) { 
			author = "吕军";
		}
		if(title == null || title.trim().equals("")) { 
			title = "吕军";
		}
		if(subject == null || subject.trim().equals("")) { 
			subject = "吕军电子打印";
		}
		if(keywords == null || keywords.trim().equals("")) { 
			keywords = "吕军";
		}
		if(pageSize == null || pageSize.trim().equals("")) { 
			pageSize = "A4";
		}
		if(isRotate == null || isRotate.trim().equals("")) { 
			isRotate = "FALSE";
		}
		if(marginLeft == null || marginLeft.trim().equals("")) { 
			marginLeft = "10";
		}
		if(marginRight == null || marginRight.trim().equals("")) { 
			marginRight = "10";
		}
		if(marginTop == null || marginTop.trim().equals("")) { 
			marginTop = "50";
		}
		if(marginBottom == null || marginBottom.trim().equals("")) { 
			marginBottom = "50";
		}
		if(diplayTotalPageClass != null && !diplayTotalPageClass.trim().equals("")) { 
			event = (PdfPageEvent)Class.forName(diplayTotalPageClass).newInstance();
		}
		
		Rectangle vPageSize = null;
		if(pageSize.toUpperCase().equals("A3")) { 
			vPageSize = new RectangleReadOnly(842F, 1191F);
		}else { 
			vPageSize = new RectangleReadOnly(595F, 842F);
		}
		if(isRotate.toUpperCase().equals("TRUE")) {
			vPageSize = vPageSize.rotate();
		}
		
		float vMarginLeft = Float.parseFloat(marginLeft);
		float vMarginRight = Float.parseFloat(marginRight);
		float vMarginTop = Float.parseFloat(marginTop);
		float vMarginBottom = Float.parseFloat(marginBottom);
		
		Document d = new Document(vPageSize,vMarginLeft,vMarginRight,vMarginTop,vMarginBottom); 
		try {
			PdfWriter writer = PdfWriter.getInstance(d, outputStream);
			if(diplayTotalPageClass != null && !diplayTotalPageClass.trim().equals("")) { 
				writer.setPageEvent(event);
			}
			
			d.addTitle(title);
			d.addSubject(subject);
			d.addKeywords(keywords);
			d.addAuthor(author);
			d.setHeader(header); 
			d.setFooter(footer);
			
			d.open();//打开文档
			d = this.handleCollectionComponents(d, components);
			d.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Document handleCollectionComponents(Document d, List components) throws DocumentException{
		
		Iterator it = components.iterator();
		while(it.hasNext()) { 
			Object o = it.next();
			if(o instanceof com.lowagie.text.Element) { 
				d.add((com.lowagie.text.Element)o);
			}else if (o instanceof List ){
				d = handleCollectionComponents(d,(List)o);
			}
		}
		return d ; 
	}
}
