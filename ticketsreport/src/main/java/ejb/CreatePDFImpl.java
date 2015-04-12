package ejb;

import java.io.OutputStream;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
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
	public String create(List<TicketVO> ticketList, OutputStream stream, String filename){
		Document document = new Document();
		
	    try {
	    	LOG.info("Creating new file");
			PdfWriter.getInstance(document, stream);
		} catch (DocumentException e) {
			LOG.warn("Exception" + e);
			return "FileNotFound";
		}
	    document.open();
	    
	    LOG.info("Creating document's design");
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
	    
	    LOG.info("Adding content into document");
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
			LOG.warn("Exception" + e);
			return "Document Exception";
		}
	    document.close();
	    return "OK";
	}
}
