package ejb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.TicketVO;

@Stateless
public class CreatePDFImpl implements CreatePDF {

	public CreatePDFImpl() {
	}
	
	@Override
	public void create(List<TicketVO> ticketList, OutputStream stream, String filename){
		Document document = new Document();
		
	    try {
	    	File file = new File(filename);
	    	file.createNewFile();
			PdfWriter.getInstance(document, stream);
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    document.open();
	    
	    
	    PdfPTable table = new PdfPTable(5);
	    PdfPCell c1 = new PdfPCell(new Phrase("First Name"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Last Name"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Date"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    
	    c1 = new PdfPCell(new Phrase("From Station"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    
	    c1 = new PdfPCell(new Phrase("To Station"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);
	    
	    for(TicketVO ticket: ticketList){
	    	table.addCell(ticket.getFirstName());
	    	table.addCell(ticket.getLastName());
	    	table.addCell(ticket.getDepTime());
	    	table.addCell(ticket.getStationFrom());
	    	table.addCell(ticket.getStationTo());
	    }
	    try {
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	    document.close();
	}
}
