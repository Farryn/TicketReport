/**
 * 
 */
package ejb;

import java.io.OutputStream;
import java.util.List;

import javax.ejb.Local;

import entity.TicketVO;

/**
 * @author Damir Tuktamyshev
 *
 */
@Local
public interface CreatePDF {
	/**Generates pdf.
	 * @param ticketList list of TicketVO
	 * @param output stream
	 * @param filename filename
	 * @return status
	 */
	public String create(List<TicketVO> ticketList, OutputStream output, String filename);
}
