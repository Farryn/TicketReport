/**
 * 
 */
package ejb;

import java.util.List;

import javax.ejb.Local;

import entity.TicketVO;

/**
 * @author Damir Tuktamyshev
 *
 */
@Local
public interface CreatePDF {
	public String create(List<TicketVO> ticketList);
}
