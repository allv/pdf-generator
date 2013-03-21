/**
 * 模块名： PDF 配置文件格式异常类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.exception;

import com.allen.pdf.util.PdfUtil;

public class IllegalFormatException extends PdfException {
	
	private String formatError ;

	public IllegalFormatException(String errorMsg) {
		this.formatError = errorMsg;
	}

	public String getMessage() {
		formatError = "PDF-XML配置文件 格式错误："+ formatError;
		return PdfUtil.convertGbkToIso(formatError);
	}
}
