/**
 * 模块名： PDF控件接口
 * 版本： 1.0
 * 说明： 抽象出所有控件的解析方法   
 * 编写者：     lvjun
 * 编写者：     
 * 编写日期： 2010/03/01
 * 修改信息：  
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
