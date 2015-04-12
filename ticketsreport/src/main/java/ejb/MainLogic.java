/**
 * 
 */
package ejb;

import javax.ejb.Local;

/**
 * @author Damir Tuktamyshev
 *
 */
@Local
public interface MainLogic {
	/**Main method which sends form parameters to web service and creates pdf
	 * @param from date from
	 * @param to date to
	 * @param token token
	 * @return status
	 */
	public String process(String from, String to, String token);
}
