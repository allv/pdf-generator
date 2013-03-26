package com.allen;

import com.allen.pdf.base.PdfIngredientFactory;
import com.allen.process.TestPdfIngredientFactory;
import com.allen.process.TestPdfProcess;

public class Test {
	public static void main(String[] args) {
		PdfIngredientFactory factory = new TestPdfIngredientFactory();
		TestPdfProcess process = new TestPdfProcess();
		process.setFactory(factory);
		process.setXmlPath("/xml/test.xml");
		process.setPropPath("/pdf.properties");
		try {
			process.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}