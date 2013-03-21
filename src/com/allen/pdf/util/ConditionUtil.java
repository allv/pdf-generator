/**
 * ģ������ IF��ǩ �����жϴ�����
 * �汾�� 1.0
 * ˵����  
 * ��д�ߣ�     lvjun
 * ��д���ڣ� 2010/03/01
 * �޸���Ϣ��  
 */
package com.allen.pdf.util;

import java.util.List;
import java.util.Map;

import com.allen.pdf.exception.InvalidCompareException;


public class ConditionUtil {

	/*
	 * ���������ж�
	 * @param condition:�������ʽ
	 * @param source: ���ݼ�
	 */
	public static boolean handleCondition(String condition,Map source) throws Exception{ 
		boolean flag = true;
		if(condition == null || condition.trim().equals("")) { 
			throw new InvalidCompareException("IF����Ϊ��");
		}
		//�õ������
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
			//����Ǳ�Ԫ
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
			//����Ǳ�Ԫ
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
//	 * ��ϵ�����
//	 * ==��<>�����ַ���֮��ıȽ�
//	 * =,!=��������֮��ıȽ�
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
//	 * �߼������&&,||
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
//	 * �߼������!
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
