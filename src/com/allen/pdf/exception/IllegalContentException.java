/**
 * 模块名： PDF 标签内容异常类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.exception;

import com.allen.pdf.util.PdfUtil;

public class IllegalContentException extends PdfException {
	
	private String contentError ;
	
	public IllegalContentException(String errMsg) { 
		this.contentError = errMsg;
	}
	
	public String getMessage() {
		contentError = "PDF-XML配置文件 标签内内容错误："+ contentError;
		return PdfUtil.convertGbkToIso(contentError);
	}

}
