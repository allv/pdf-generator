/**
 * 模块名： PDF 核心处理类
 * 版本： 1.0
 * 说明： 抽象出核心处理方法   
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.base;

import java.io.OutputStream;
import java.util.Map;

public abstract class PdfMainProcess {

	public Map dataSource;
	public OutputStream outputStream;
	
	public abstract void process() throws Exception;

	public abstract void setDataSource(Map dataSource) ;

	public abstract void setOutputStream(OutputStream outputStream) ;
}
