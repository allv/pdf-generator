/**
 * 模块名： TABLE 处理类
 * 版本： 1.0
 * 说明：  
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
import com.allen.pdf.util.PdfUtil;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class TableComponent implements Component {

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
		 * <!ELEMENT table (td|pdf:if|pdf:loop)* >
		 * <!ATTLIST table cols CDATA #REQUIRED>
		 * <!ATTLIST table widthPercentage CDATA #IMPLIED>
		 * <!ATTLIST table widths CDATA #IMPLIED>
		 * <!ATTLIST table headerRow CDATA #IMPLIED>
		 * <!ATTLIST table extendLastRow CDATA #IMPLIED>
		 * <!ATTLIST table class CDATA #IMPLIED >
		 */
		if(element == null ){ 
			return null;
		}
		String className = element.getAttributeValue("class");
		if(className == null || className.trim().equals("")) {
			String cols = element.getAttributeValue("cols");
			String widthPercentage = element.getAttributeValue("widthPercentage");
			String widths = element.getAttributeValue("widths");
			String headerRow = element.getAttributeValue("headerRow");
			String extendLastRow = element.getAttributeValue("extendLastRow");
			
			cols = PdfUtil.convertAttributeValue(cols, source);
			widthPercentage = PdfUtil.convertAttributeValue(widthPercentage, source);
			widths = PdfUtil.convertAttributeValue(widths, source);
			headerRow = PdfUtil.convertAttributeValue(headerRow, source);
			
			boolean vEextendLastRow = false;
			if(cols == null || cols.trim().equals("")) { 
				return null;
			}
			PdfPTable table = null;
			int vCols = 1;
			try{
				vCols = Integer.parseInt(cols);
			} catch(NumberFormatException e) {
				throw new IllegalContentException("TABLE标签内COLS属性内容应该为数字");
			}
			if(widths != null && !widths.trim().equals("")) {
				String[] vWidths = widths.split(",");
				if(vWidths.length != vCols) { 
					throw new IllegalContentException("TABLE标签内COLS属性内容和widhtPercentage内容不匹配");
				}
				float f[] = new float[vCols];
				for(int i=0;i<vCols;i++){
					try{
						f[i] = Float.parseFloat(vWidths[i]);
					}catch(NumberFormatException e ) { 
						throw new IllegalContentException("TABLE标签内widhtPercentage内容应为数字");
					}
				}
				table = new PdfPTable(f);
			}else{
				table = new PdfPTable(vCols); 
			}
			if(widthPercentage == null || widthPercentage.trim().equals("") ) {
				widthPercentage = "100"	;
			}
			float vWidthPercentage = 100;
			try{
				vWidthPercentage = Float.parseFloat(widthPercentage);
			} catch( NumberFormatException e) { 
				throw new IllegalContentException ("TABLE标签内属性widthPercentage内容应该为数字");
			}
			if(headerRow == null || headerRow.trim().equals("")) { 
				headerRow = "0";
			}
			if(extendLastRow == null || extendLastRow.trim().equals("")) { 
				vEextendLastRow = false;
			}else if(extendLastRow.toUpperCase().equals("TRUE")){ 
				vEextendLastRow = true;
			}else { 
				vEextendLastRow = false;
			}
			int vHeaderRow = 0;
			try{
				vHeaderRow = Integer.parseInt(headerRow);
			} catch( NumberFormatException e) { 
				throw new IllegalContentException ("TABLE标签内属性headerRow内容应该为数字");
			}
			table.setWidthPercentage(vWidthPercentage);
			table.setHeaderRows(vHeaderRow);
			table.setExtendLastRow(vEextendLastRow);
			ArrayList components = new ArrayList();
			Iterator it = element.getChildren().iterator();
			PdfXMLComponentFactory factory = PdfXMLComponentFactory.getInstance();
			while(it.hasNext()) { 
				Element e = (Element) it.next();
				Component c = factory.getComponent(e.getName());
				components.add(c.unpack(e, source, preDefine));
			}
			table = this.handleComponents(table, components);
			return table;
		} else { 
			PdfPTable table = (PdfPTable) Class.forName(className).newInstance();
			return table;
		}
	}
	
	private PdfPTable handleComponents(PdfPTable table,List components) { 
		Iterator it = components.iterator();
		while(it.hasNext()) { 
			Object o = it.next();
			if(o instanceof PdfPCell) {
				table.addCell((PdfPCell)o);
			}else if(o instanceof List) { 
				table = handleComponents(table, (List)o);
			}
		}
		return table;
	}

}
