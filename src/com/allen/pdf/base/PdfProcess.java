/**
 * ģ������ PDF ���Ĵ�����
 * �汾�� 1.0
 * ˵���� ��������Ĵ�����   
 * ��д�ߣ�     lvjun
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
 */
package com.allen.pdf.base;

import java.io.OutputStream;
import java.util.Map;

public abstract class PdfProcess {

	public Map dataSource;
	public OutputStream outputStream;
	
	public abstract void process() throws Exception;
	
}
