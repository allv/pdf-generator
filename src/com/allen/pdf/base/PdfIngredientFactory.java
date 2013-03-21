/**
 * 模块名： PDF 原料工厂
 * 版本： 1.0
 * 说明： 抽象出数据源和输出流的方法
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.base;

import java.io.OutputStream;
import java.util.Map;

public interface PdfIngredientFactory {
	
	public Map createDataSource();
	public OutputStream createOutputStream();
	
}
