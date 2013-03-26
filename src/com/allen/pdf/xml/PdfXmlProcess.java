/**
 * 模块名： XML配置文件解析类
 * 版本： 1.0
 * 说明：  
 * 编写者：   lvjun  
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.xml;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class PdfXmlProcess {

	private Map dataSource;
	private OutputStream outputStream;

	public PdfXmlProcess() {
	}

	public PdfXmlProcess(Map dataSource, OutputStream outputStream) {
		this.dataSource = dataSource;
		this.outputStream = outputStream;
	}

	public Map getDataSource() {
		return dataSource;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void setDataSource(Map dataSource) {
		this.dataSource = dataSource;
	}

	public void process(Element root) throws Exception {
		Element defineElement = root.getChild("define");
		Element bodyElement = root.getChild("body");
		HashMap preDefine = null;
		DefineComponent defineComponent = new DefineComponent();
		preDefine = (HashMap) defineComponent.unpack(defineElement);
		if (preDefine == null) {
			preDefine = new HashMap();
		}

		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.setOutputStream(outputStream);
		bodyComponent.unpack(bodyElement, dataSource, preDefine);
	}

	public void process(File f) throws Exception {
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = builder.build(f);
		Element root = doc.getRootElement();
		Element defineElement = root.getChild("define");
		Element bodyElement = root.getChild("body");
		HashMap preDefine = null;
		DefineComponent defineComponent = new DefineComponent();
		preDefine = (HashMap) defineComponent.unpack(defineElement);
		if (preDefine == null) {
			preDefine = new HashMap();
		}

		BodyComponent bodyComponent = new BodyComponent();
		bodyComponent.setOutputStream(outputStream);
		bodyComponent.unpack(bodyElement, dataSource, preDefine);
	}

}
