/**
 * 模块名： IF标签 条件判断处理类
 * 版本： 1.0
 * 说明：  
 * 编写者：     lvjun
 * 编写日期： 2010/03/01
 * 修改信息：  
 */
package com.allen.pdf.util;

import java.util.List;
import java.util.Map;

import com.allen.pdf.exception.InvalidCompareException;


public class ConditionUtil {

	/*
	 * 条件语句的判断
	 * @param condition:条件表达式
	 * @param source: 数据集
	 */
	public static boolean handleCondition(String condition,Map source) throws Exception{ 
		boolean flag = true;
		if(condition == null || condition.trim().equals("")) { 
			throw new InvalidCompareException("IF条件为空");
		}
		//得到运算符
		String op = new String("");
        if(condition.indexOf('=') != -1)
            op = new String("=");
        if(condition.indexOf("!=") != -1)
        	op = new String("!=");
        if(condition.indexOf('>') != -1)
            op = new String(">");
        if(condition.indexOf('<') != -1)
            op = new String("<");
        if(condition.indexOf(">=") != -1)
            op = new String(">=");
        if(condition.indexOf("<=") != -1)
            op = new String("<=");
        if(condition.indexOf("==") != -1)
        	op = new String("==");
        if(condition.indexOf("<>") != -1)
            op = new String("<>");
		
        String leftValue = "";
        String rightValue = "";
        int index = condition.indexOf(op);
        leftValue = condition.substring(0, index).trim();
        rightValue = condition.substring(index + op.length()).trim();
        
        String loopIndex = (String) source.get("loopIndex");
		if( loopIndex == null ){
			loopIndex = "0";
		}
		int loopInd = Integer.parseInt(loopIndex);
        
		if(leftValue.substring(0, 1).equals("$")) { 
			//如果是报元
			String tagId = leftValue.substring(1);
			try{
				Object o = source.get(tagId);
				if(o != null ) { 
					if(o instanceof String) { 
						leftValue = (String) o ;
					}else if(o instanceof List){
						leftValue = (String) ((List) o).get(loopInd);
					}else { 
						leftValue = "";
					}
				}else { 
					leftValue = "";
				}
			} catch( IndexOutOfBoundsException e) {
				leftValue = "";
			}
		}
		
		if(rightValue.substring(0, 1).equals("$")) { 
			//如果是报元
			String tagId = rightValue.substring(1);
			try{
				Object o = source.get(tagId);
				if(o != null ) { 
					if(o instanceof String) { 
						rightValue = (String) o ;
					}else if(o instanceof List){
						rightValue = (String) ((List) o).get(loopInd);
					}else {
						rightValue = "";
					}
				}else { 
					rightValue = "";
				}
			} catch( IndexOutOfBoundsException e) {
				rightValue = "";
			}
		}
		
		if(op.equals("==")){
			flag = leftValue.equals(rightValue);
		}
		if(op.equals("<>")){
			flag = !leftValue.equals(rightValue);
		}
		if(op.equals("=")) { 
			flag = Float.valueOf(leftValue).floatValue() == Float.valueOf(rightValue).floatValue();
		}
		if(op.equals("!=")){
			flag = Float.valueOf(leftValue).floatValue() != Float.valueOf(rightValue).floatValue();
		}
		if(op.equals(">")) { 
			flag = Float.valueOf(leftValue).floatValue() > Float.valueOf(rightValue).floatValue();
		}
		if(op.equals(">=")) { 
			flag = Float.valueOf(leftValue).floatValue() >= Float.valueOf(rightValue).floatValue();
		}
		if(op.equals("<")) { 
			flag = Float.valueOf(leftValue).floatValue() < Float.valueOf(rightValue).floatValue();
		}
		if(op.equals("<=")) { 
			flag = Float.valueOf(leftValue).floatValue() <= Float.valueOf(rightValue).floatValue();
		}
		
		return flag ;
	}
	
//	/*
//	 * 关系运算符
//	 * ==，<>代表字符串之间的比较
//	 * =,!=代表数字之间的比较
//	 */
//	private boolean compare(String strL,String op,String strR) {
//		boolean result = true;
//		if(op.equals("==")){
//			return strL.equals(strR);
//		}
//		if(op.equals("<>")){
//			return !strL.equals(strR);
//		}
//		if(op.equals("=")) { 
//			return Float.valueOf(strL).floatValue() == Float.valueOf(strR).floatValue();
//		}
//		if(op.equals("!=")){
//			return Float.valueOf(strL).floatValue() != Float.valueOf(strR).floatValue();
//		}
//		if(op.equals(">")) { 
//			return Float.valueOf(strL).floatValue() > Float.valueOf(strR).floatValue();
//		}
//		if(op.equals(">=")) { 
//			return Float.valueOf(strL).floatValue() >= Float.valueOf(strR).floatValue();
//		}
//		if(op.equals("<")) { 
//			return Float.valueOf(strL).floatValue() < Float.valueOf(strR).floatValue();
//		}
//		if(op.equals("<=")) { 
//			return Float.valueOf(strL).floatValue() <= Float.valueOf(strR).floatValue();
//		}
//		return result;
//	}
//	
//	/*
//	 * 逻辑运算符&&,||
//	 */
//	private boolean compare(boolean strL,String op,boolean strR) { 
//		boolean result = true;
//		if(op.equals("&&")) { 
//			return strL && strR;
//		}
//		if(op.equals("||")) { 
//			return strL || strR;
//		}
//		return result;
//	}
//	
//	/*
//	 * 逻辑运算符!
//	 */
//	private boolean compare(boolean strR,String op) { 
//		boolean result = true;
//		if(op.equals("!")) { 
//			return !strR;
//		}
//		return result;
//	}
//	
//	private String getOp(String expression) {
//        String op = new String("");
//        if(expression.indexOf('=') != -1)
//            op = new String("=");
//        if(expression.indexOf("!=") != -1)
//        	op = new String("!=");
//        if(expression.indexOf('>') != -1)
//            op = new String(">");
//        if(expression.indexOf('<') != -1)
//            op = new String("<");
//        if(expression.indexOf(">=") != -1)
//            op = new String(">=");
//        if(expression.indexOf("<=") != -1)
//            op = new String("<=");
//        if(expression.indexOf("==") != -1)
//        	op = new String("==");
//        if(expression.indexOf("<>") != -1)
//            op = new String("<>");
//        return op;
//    }
}
