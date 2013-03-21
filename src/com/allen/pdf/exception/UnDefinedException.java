/**
 * ģ������ PDF DEFINE��ǩ�쳣��
 * �汾�� 1.0
 * ˵����  
 * ��д�ߣ�     lvjun
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
 */
package com.allen.pdf.exception;

import com.allen.pdf.util.PdfUtil;

public class UnDefinedException extends PdfException {

	private String errMsg = null;
	
	public UnDefinedException (String errMsg) { 
		this.errMsg = errMsg;
	}

	public String getMessage() {
		errMsg = "PDF-XML�����ļ� ����δ�����" + errMsg;
		return PdfUtil.convertGbkToIso(errMsg);
	}
	
	
}
