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

	/**Send request to web-service to get list of Tickets
	 * @param from Date from
	 * @param to Date to
	 * @param token authentication token
	 * @return TicketVO list
	 */
	List<TicketVO> getTicketList(String from, String to, String token);
	
	/**Send request to web-service to get token
	 * @param username username
	 * @param password password
	 * @return token
	 */
	String checkCredentials(String username, String password);
}
