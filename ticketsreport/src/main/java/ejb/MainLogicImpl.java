/**
 * 
 */
package ejb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
	
	public void process(String from, String to, String token){
		DateFormat df = new SimpleDateFormat("dd-M-yyyy");
		DateFormat df2 = new SimpleDateFormat("dd-M-yyyy");
		String dateFrom = null;
		String dateTo = null;
    	try {
			dateFrom = df2.format(df.parse(from));
			dateTo = df2.format(df.parse(to));
		} catch (ParseException e) {
			e.printStackTrace();
		}       
		List<TicketVO> ticketList = new ArrayList<TicketVO>();
		ticketList = restClient.getTicketList(dateFrom, dateTo, token);
		String filename = createPDF.create(ticketList);
	}

	
}
