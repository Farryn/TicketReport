package ejb;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;










import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.TicketVO;

/**
 * @author Damir Tuktamyshev
 *
 */
@Stateless
public class CreatePDFImpl implements CreatePDF {

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(CreatePDFImpl.class);
	
	public CreatePDFImpl() {
	}
	
	/* (non-Javadoc)
	 * @see ejb.CreatePDF#create(java.util.List, java.io.OutputStream, java.lang.String)
	 */
	@Override
	public String create(List<TicketVO> ticketList, OutputStream stream, String filename, String from, String to){
		if(ticketList.isEmpty())
			return "Empty List";
		
		Document document = new Document();
		PdfWriter writer;
	    try {
			writer = PdfWriter.getInstance(document, stream);
		} catch (DocumentException e) {
			LOG.warn("Exception" + e);
			return "FileNotFound";
		}
	    document.open();
	    
	    Font font = new Font(FontFamily.HELVETICA, 16, Font.NORMAL, BaseColor.WHITE);
	    Font titleFont = new Font(FontFamily.HELVETICA, 30, Font.BOLD, BaseColor.WHITE);
	    
	    LOG.info("Creating document's design");
	    
	    Paragraph par = new Paragraph(new Phrase("Tickets report", titleFont));
    	par.setAlignment(Element.ALIGN_CENTER);
    	par.setSpacingAfter(50);
    	
	    PdfPTable table = new PdfPTable(5);
	    table.getDefaultCell().setBorderColor(BaseColor.WHITE);
	    table.getDefaultCell().setBorderWidth(2);
	    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.getDefaultCell().setPadding(5);
	    
	    LOG.info("Creating table's headers");
	    String[] array = {"First Name", "Last Name", "Date", "From Station", "To Station"};
	    for(String str: array) {
	    	PdfPCell c1 = new PdfPCell(new Phrase(str, font));
		    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    c1.setBorderColor(BaseColor.WHITE);
		    c1.setBorderWidth(2);
		    c1.setPadding(5);
		    table.addCell(c1);
	    }
	    
	    table.setHeaderRows(1);
	    
	    LOG.info("Adding content into document");
	    for(TicketVO ticket: ticketList){
	    	
	    	table.addCell(new Phrase(ticket.getFirstName(), font));
	    	table.addCell(new Phrase(ticket.getLastName(), font));
	    	table.addCell(new Phrase(ticket.getDepTime(), font));
	    	table.addCell(new Phrase(ticket.getStationFrom(), font));
	    	table.addCell(new Phrase(ticket.getStationTo(), font));
	    	
	    }
	    try {
	    	document.add(par);
			document.add(table);
		} catch (DocumentException e) {
			LOG.warn("Exception" + e);
			return "Document Exception";
		}
	    Image image = null;
		try {
			PdfContentByte canvas = writer.getDirectContentUnder();
			image = Image.getInstance("C:/Downloads/light-blue-large.jpg");
			image.scaleAbsolute(PageSize.A4);
	        image.setAbsolutePosition(0, 0);
	        canvas.addImage(image);
		} catch (IOException | DocumentException e) {
			LOG.warn("Exception" + e);
			return "Document Exception";
		}
	    document.close();
	    return "OK";
	}
}
