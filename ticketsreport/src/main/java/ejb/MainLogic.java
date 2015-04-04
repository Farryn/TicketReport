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
	public void process(String from, String to, String token);
}
