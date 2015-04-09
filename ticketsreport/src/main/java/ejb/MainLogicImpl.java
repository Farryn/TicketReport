/**
 * 
 */
package ejb;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;




import entity.TicketVO;

/**
 * @author Damir Tuktamyshev
 *
 */
@Stateless
public class MainLogicImpl implements MainLogic {

	/**
	 * 
	 */
	public MainLogicImpl() {
	}
	
	@EJB
	private RestClient restClient;
	
	@EJB
	private CreatePDF createPDF;
	
	public String process(String from, String to, String token){
		String dateFrom;
		String dateTo;
		try {
			dateFrom = reformatDate(from);
			dateTo = reformatDate(to);
		} catch (ParseException e) {
			return "Wrong date format";
		}   
		List<TicketVO> ticketList = restClient.getTicketList(dateFrom, dateTo, token);
		if (ticketList == null || ticketList.isEmpty()) {
			return "Empty List";
		}
		String status = createPdf(ticketList);
		if (!status.equals("OK")) {
			return status;
		}
		return "OK";
	}
	
	private String reformatDate(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-M-yyyy");
		DateFormat df2 = new SimpleDateFormat("dd-M-yyyy");
		String changedDate = null;
    	changedDate = df2.format(df.parse(date));
		  
    	return changedDate;
	}
	
	private String createPdf(List<TicketVO> ticketList) {
		FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        DateFormat df = new SimpleDateFormat("dd-M-yyyy_HH-mm-ss");
    	String reportDate = df.format(new Date());
    	String filename = "C:/Program Files (x86)/wildfly/wildfly-8.2.0.Final/files/File_"  + reportDate + ".pdf";
    	ec.responseReset();
        ec.setResponseContentType("application/pdf");
        ec.setResponseHeader("Content-Disposition", "inline; filename=\""
                        + filename + "\"");
        OutputStream output = null;
        try {
			output = ec.getResponseOutputStream();
		} catch (IOException e) {
			return "IO exception";
		}
        createPDF.create(ticketList, output, filename);
        fc.responseComplete();
        return "OK";
	}
	
}
