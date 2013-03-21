/**
 * ģ������ PDF�ؼ��ӿ�
 * �汾�� 1.0
 * ˵���� ��������пؼ��Ľ�������   
 * ��д�ߣ�     lvjun
 * ��д�ߣ�     
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
 */
package com.allen.pdf.base;

import java.util.Map;

import org.jdom.Element;

public interface Component {

	public Object unpack() throws Exception;
	public Object unpack(Element element) throws Exception;
	public Object unpack(Element element,Map source) throws Exception;
	public Object unpack(Element element,Map source,Map preDefine) throws Exception;
}
