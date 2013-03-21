/**
 * 模块名： PDF DEFINE标签异常类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.exception;

import com.allen.pdf.util.PdfUtil;

public class UnDefinedException extends PdfException {

	private String errMsg = null;
	
	public UnDefinedException (String errMsg) { 
		this.errMsg = errMsg;
	}

	public String getMessage() {
		errMsg = "PDF-XML配置文件 错误：未定义的" + errMsg;
		return PdfUtil.convertGbkToIso(errMsg);
	}
	
	
}
