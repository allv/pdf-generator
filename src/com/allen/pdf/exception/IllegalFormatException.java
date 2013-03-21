/**
 * ģ������ PDF �����ļ���ʽ�쳣��
 * �汾�� 1.0
 * ˵����  
 * ��д�ߣ�     lvjun
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
 */
package com.allen.pdf.exception;

import com.allen.pdf.util.PdfUtil;

public class IllegalFormatException extends PdfException {
	
	private String formatError ;

	public IllegalFormatException(String errorMsg) {
		this.formatError = errorMsg;
	}

	public String getMessage() {
		formatError = "PDF-XML�����ļ� ��ʽ����"+ formatError;
		return PdfUtil.convertGbkToIso(formatError);
	}
}
