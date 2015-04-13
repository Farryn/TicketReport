/**
 * 
 */
package ejb;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;





import org.apache.log4j.Logger;

import entity.TicketVO;

/**
 * @author Damir Tuktamyshev
 *
 */
@Stateless
public class MainLogicImpl implements MainLogic {

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(MainLogicImpl.class);
	
	
	public MainLogicImpl() {
	}
	
	@EJB
	private RestClient restClient;
	
	@EJB
	private CreatePDF createPDF;
	
	/* (non-Javadoc)
	 * @see ejb.MainLogic#process(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String process(String from, String to, String token){
		LOG.info("Reformatting dates");
		String dateFrom;
		String dateTo;
		try {
			dateFrom = reformatDate(from);
			dateTo = reformatDate(to);
		} catch (ParseException e) {
			LOG.warn("Parse Exception" + e);
			return "Wrong date format";
		}
		LOG.info("Getting info from web-service");
		List<TicketVO> ticketList = restClient.getTicketList(dateFrom, dateTo, token);
		if (ticketList == null || ticketList.isEmpty()) {
			LOG.info("Empty ticket list");
			return "Empty List";
		}
		LOG.info("Creating pdf");
		String status = createPdf(ticketList, from ,to);
		if (!status.equals("OK")) {
			LOG.warn("Something wrong");
			return status;
		}
		return "OK";
	}
	
	/**Reformats date
	 * @param date String with date to reformat
	 * @return String with reformatted date
	 * @throws ParseException
	 */
	private String reformatDate(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd.M.yyyy");
		DateFormat df2 = new SimpleDateFormat("dd-M-yyyy");
		String changedDate = null;
    	changedDate = df2.format(df.parse(date));
		  
    	return changedDate;
	}
	
	/**Creates pdf
	 * @param ticketList list of Tickets
	 * @return status
	 */
	private String createPdf(List<TicketVO> ticketList, String from, String to) {
		FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        LOG.info("Generating file name");
        DateFormat df = new SimpleDateFormat("dd-M-yyyy_HH-mm-ss");
    	String reportDate = df.format(new Date());
    	String filename = "File_"  + reportDate + ".pdf";
    	ec.responseReset();
        ec.setResponseContentType("application/pdf");
        ec.setResponseHeader("Content-Disposition", "inline; filename=\""
                        + filename + "\"");
        OutputStream output = null;
        try {
			output = ec.getResponseOutputStream();
		} catch (IOException e) {
			LOG.warn("IO Exception" + e);
			return "IO exception";
		}
        LOG.info("Creating design and putting content");
        String status = createPDF.create(ticketList, output, filename, from, to);
        if (!status.equals("OK")) {
        	LOG.warn("Fail");
        	return "Fail";
        }
        fc.responseComplete();
        return "OK";
	}
	
}
