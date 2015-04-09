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
	public void create(List<TicketVO> ticketList, OutputStream output, String filename);
}
