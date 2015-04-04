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
public interface RestClient {

	List<TicketVO> getTicketList(String from, String to, String token);
	String checkCredentials(String username, String password);
}
