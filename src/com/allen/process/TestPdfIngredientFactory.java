package com.allen.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allen.pdf.base.PdfIngredientFactory;

public class TestPdfIngredientFactory implements PdfIngredientFactory {

	@Override
	public Map createDataSource() {
		
		Map map = new HashMap<String, String>();
		map.put("test", "1");
		List<String> records = new ArrayList<String>();
		records.add("1");
		records.add("2");
		records.add("3");
		records.add("4");
		records.add("5");
		records.add("6");
		records.add("7");
		records.add("8");
		records.add("9");
		records.add("10");
		map.put("records1", records);
		map.put("records2", records);
		map.put("records3", records);
		map.put("records4", records);
		map.put("records5", records);
		map.put("records6", records);
		map.put("records7", records);
		map.put("records8", records);
		map.put("records9", records);
		map.put("records10", records);
		return map;
	}

	@Override
	public OutputStream createOutputStream() {
		File file = new File(this.getClass().getResource("/").getPath()+"/test.pdf");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			return new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
