/**
 * ģ������ PDF ��ǩ�����쳣��
 * �汾�� 1.0
 * ˵����  
 * ��д�ߣ�     lvjun
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
 */
package com.allen.pdf.exception;

import com.allen.pdf.util.PdfUtil;

public class IllegalContentException extends PdfException {
	
	private String contentError ;
	
	public IllegalContentException(String errMsg) { 
		this.contentError = errMsg;
	}
	
	public String getMessage() {
		contentError = "PDF-XML�����ļ� ��ǩ�����ݴ���"+ contentError;
		return PdfUtil.convertGbkToIso(contentError);
	}

}
