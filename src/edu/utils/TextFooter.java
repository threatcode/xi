package edu.utils;

/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/27780756/adding-footer-with-itext-doesnt-work
 */

 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;
 
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class TextFooter {
	private String strheader1 = "";
	private String strheader2 = "";
	private String strheader3 = "";
	Phrase footer ;
	int totcountPage = 0;
 
	public class HeaderTable extends PdfPageEventHelper {
		
		Font fontBig = new Font(Font.getFamily("Times New Roman"), 14, Font.BOLD);
		Font fontnormal = new Font(Font.getFamily("Times New Roman"), 12, Font.NORMAL);
		
		Font fontBold = new Font(Font.getFamily("Times New Roman"), 10, Font.BOLD);
		Font fontBold9 = new Font(Font.getFamily("Times New Roman"), 9, Font.BOLD);
		
		Font fontNormal = new Font(Font.getFamily("Times New Roman"), 9, Font.NORMAL);
		Font fontNormal8 = new Font(Font.getFamily("Times New Roman"), 8, Font.NORMAL);
		
        protected PdfPTable table;
        PdfTemplate total;
        protected float tableHeight;
        
        public HeaderTable() {
            table = new PdfPTable(1);
            table.setTotalWidth(523);
            table.setLockedWidth(true);
            PdfPCell cell = new PdfPCell(new Phrase(strheader1,fontBig));
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cell.setBorder(Color.WHITE.getRGB());
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(strheader2,fontnormal));
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cell.setBorder(Color.WHITE.getRGB());
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(strheader3,fontnormal));
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cell.setBorder(Color.WHITE.getRGB());
            table.addCell(cell);
            
            tableHeight = table.getTotalHeight();
        }
 
        public float getTableHeight() {
            return tableHeight;
        }
        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }
        public void onEndPage(PdfWriter writer, Document document) {
        	
        	PdfContentByte cb = writer.getDirectContent();
            table.writeSelectedRows(0, -1,
                    document.left(),
                    document.top() + ((document.topMargin() + tableHeight) / 2),
                    writer.getDirectContent());
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    400,
                    document.bottom() - 0, 0);
        	PdfPTable table1 = new PdfPTable(2);
            try {
                table1.setTotalWidth(527);
                PdfPCell cell = new PdfPCell(new Phrase(String.format("Page %d of ", writer.getPageNumber()),fontNormal8));
                
//                Image tmpImage1 = Image.getInstance("Page "+writer.getPageNumber()+" of ");
//                tmpImage1.scaleAbsolute(200, 10);
//                PdfPCell cell = new PdfPCell(tmpImage1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(Color.WHITE.getRGB());
                table1.addCell(cell);
                
                Image tmpImage = Image.getInstance(total);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);                
                tmpImage.scaleAbsolute(15, 11);
                cell = new PdfPCell(tmpImage);
                cell.setBorder(Color.WHITE.getRGB());
                table1.addCell(cell);
                table1.writeSelectedRows(0, -1, 34, 20, writer.getDirectContent());
            }
            catch(Exception de) {
                
            }
        }
        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
        }
    }
 
    public static final String DEST = "E:/abc/page_footer.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
//        new TextFooter().createPdf(DEST);
    }
 
    public void createPdf(String filename, PdfPTable table, String header1, String header2, String header3, String footer) throws IOException, DocumentException {
    	strheader1 = header1; strheader2 = header2; strheader3 = header3; 
    	Font fontNormal8 = new Font(Font.getFamily("Times New Roman"), 7, Font.NORMAL);
    	this.footer = new Phrase(footer, fontNormal8);  
    	
        // step 1
        Document document = new Document(PageSize.A4, 15, 15, 56, 10);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        HeaderTable event = new HeaderTable();
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4
//        PdfPTable table = new PdfPTable(2);
//        table.setTotalWidth(523);
//        
//        PdfPCell cell = new PdfPCell(new Phrase("This is a test document"));
////        cell.setBackgroundColor(BaseColor.ORANGE);
//        table.addCell(cell);
//        cell = new PdfPCell(new Phrase("This is a copyright notice"));
////        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
//        table.addCell(cell);  
//        
//        for (int i = 0; i < 300; i++) {
//            
////            document.add(new Paragraph("Test " + i));
//           cell = new PdfPCell(new Phrase("This is a <br>test \ndocument"));
//            cell.setBackgroundColor(BaseColor.ORANGE);
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("This is a copyright notice"));
//            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            table.addCell(cell);            
//        }
//        table.completeRow(); 
//
//        table.setHeaderRows(1);
        
        document.add(table);
        // step 5
        document.close();
    }
}