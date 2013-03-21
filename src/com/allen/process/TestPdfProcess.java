package com.allen.process;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import com.allen.pdf.base.PdfIngredientFactory;
import com.allen.pdf.base.PdfProcess;
import com.allen.pdf.util.PdfUtil;
import com.allen.pdf.xml.PdfXmlProcess;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TestPdfProcess extends PdfProcess {

    private PdfIngredientFactory factory;
    String xmlPath;
    String propPath;// 配置文件的路径

    public void setFactory(PdfIngredientFactory factory2) {
	// TODO Auto-generated method stub

    }

    public void setXmlPath(String string) {
	// TODO Auto-generated method stub

    }

    public String getXmlPath() {
	return xmlPath;
    }

    @Override
    public void process() throws Exception {
	PdfXmlProcess xmlProcess = new PdfXmlProcess();
	xmlProcess.setDataSource(factory.createDataSource());
	xmlProcess.setOutputStream(factory.createOutputStream());
	File f = new File(xmlPath);
	if (!f.exists()) {
	    throw new FileNotFoundException();
	}
	try {
	    xmlProcess.process(f);
	} catch (Exception e) {
	    this.initErrorDocument(e.getMessage(), factory.createOutputStream());
	}
    }

    private void initErrorDocument(String errMsg, OutputStream outputStream)
	    throws MalformedURLException, IOException, DocumentException {

	Rectangle whiteBorder = new Rectangle(0f, 0f);
	whiteBorder.setBorderWidth(0f);
	whiteBorder.setBorderColor(new Color(255, 255, 255));

	BaseFont bfChinese = null;
	try {
	    bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
		    BaseFont.EMBEDDED);
	} catch (DocumentException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Font font = new Font(bfChinese, 10f, Font.BOLD);
	Font font1 = new Font(bfChinese, 10f, Font.NORMAL);
	Font font2 = new Font(bfChinese, 8f, Font.NORMAL);

	Document d = new Document(PageSize.A4, 10, 10, 50, 50);
	PdfWriter.getInstance(d, outputStream);

	PdfPTable subTable = new PdfPTable(3);
	float[] subTableWidth = { 18, 1, 81 };
	subTable.setWidths(subTableWidth);
	Properties prop = new Properties();
	File f = new File(propPath);
	FileInputStream fis = new FileInputStream(f);
	prop.load(fis);
	String imagePath = prop.getProperty("err_img");
	fis.close();
	fis = null;
	Image image = Image.getInstance(imagePath);
	image.setWidthPercentage(50);
	PdfPCell cell = new PdfPCell(image, true);
	cell.cloneNonPositionParameters(whiteBorder);
	cell.setBackgroundColor(new Color(240, 247, 255));
	cell.setBorderWidthLeft(0.5f);
	cell.setBorderWidthTop(0.5f);
	cell.setBorderWidthBottom(0.5f);
	cell.setBorderColorLeft(new Color(102, 153, 204));
	cell.setBorderColorTop(new Color(102, 153, 204));
	cell.setBorderColorBottom(new Color(102, 153, 204));
	cell.setPaddingLeft(1f);
	cell.setPaddingTop(1f);
	cell.setPaddingBottom(1f);

	subTable.addCell(cell);
	cell = new PdfPCell(new Phrase(" "));
	cell.cloneNonPositionParameters(whiteBorder);
	cell.setBackgroundColor(new Color(240, 247, 255));
	cell.setBorderWidthTop(0.5f);
	cell.setBorderWidthBottom(0.5f);
	cell.setBorderColorTop(new Color(102, 153, 204));
	cell.setBorderColorBottom(new Color(102, 153, 204));
	subTable.addCell(cell);

	PdfPTable subSubTable = new PdfPTable(3);
	float[] subSubTableWidth = { 30, 50, 20 };
	subSubTable.setWidths(subSubTableWidth);
	Phrase p = new Phrase(PdfUtil.convertGbkToIso("提示信息："), font);
	cell = new PdfPCell(p);
	cell.setColspan(3);
	cell.cloneNonPositionParameters(whiteBorder);
	cell.setBackgroundColor(new Color(240, 247, 255));
	subSubTable.addCell(cell);
	p = new Phrase("     " + errMsg, font1);
	cell = new PdfPCell(p);
	cell.cloneNonPositionParameters(whiteBorder);
	cell.setColspan(3);
	cell.setBackgroundColor(new Color(240, 247, 255));
	subSubTable.addCell(cell);
	cell = new PdfPCell(new Phrase(
		PdfUtil.convertGbkToIso("参考代码：0130Z1109001"), font2));
	cell.cloneNonPositionParameters(whiteBorder);
	cell.setBackgroundColor(new Color(240, 247, 255));
	subSubTable.addCell(cell);
	cell = new PdfPCell();
	cell.cloneNonPositionParameters(whiteBorder);
	cell.setBackgroundColor(new Color(240, 247, 255));
	subSubTable.addCell(cell);
	cell = new PdfPCell(
		new Phrase(PdfUtil.convertGbkToIso("PDF-工具"), font2));
	cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_RIGHT);
	cell.cloneNonPositionParameters(whiteBorder);
	cell.setBackgroundColor(new Color(240, 247, 255));
	subSubTable.addCell(cell);
	cell = new PdfPCell(subSubTable);
	cell.cloneNonPositionParameters(whiteBorder);
	subSubTable.addCell(cell);

	cell = new PdfPCell(subSubTable);
	cell.cloneNonPositionParameters(whiteBorder);
	cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_LEFT);
	cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_MIDDLE);
	cell.setBackgroundColor(new Color(240, 247, 255));
	cell.setBorderWidthRight(0.5f);
	cell.setBorderWidthTop(0.5f);
	cell.setBorderWidthBottom(0.5f);
	cell.setBorderColorRight(new Color(102, 153, 204));
	cell.setBorderColorTop(new Color(102, 153, 204));
	cell.setBorderColorBottom(new Color(102, 153, 204));
	cell.setPaddingRight(1f);
	subTable.addCell(cell);

	d.open();
	d.add(subTable);
	d.close();
    }

}
