/**
 * ģ������ PDF ԭ�Ϲ���
 * �汾�� 1.0
 * ˵���� ���������Դ��������ķ���
 * ��д�ߣ�     lvjun
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
 */
package com.allen.pdf.base;

import java.io.OutputStream;
import java.util.Map;

public interface PdfIngredientFactory {
	
	public Map createDataSource();
	public OutputStream createOutputStream();
	
}
