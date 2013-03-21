/**
 * 模块名： PDF IF标签异常类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.exception;

import com.allen.pdf.util.PdfUtil;

public class InvalidCompareException extends PdfException {

	private String errMsg;
	
	public InvalidCompareException(String errMsg) { 
		this.errMsg = errMsg;
	}

	public String getMessage() {
		errMsg = "PDF-XML配置文件 IF标签内内容错误：" + errMsg;
		return PdfUtil.convertGbkToIso(errMsg);
	}
	
}
