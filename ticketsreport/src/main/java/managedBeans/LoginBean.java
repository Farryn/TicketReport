/**
 * 
 */
package managedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ejb.RestClient;

/**
 * @author Damir Tuktamyshev
 *
 */
@ManagedBean
@SessionScoped
public class LoginBean {

	
	public LoginBean() {
	}
	
	@EJB
	private RestClient restClient; 
	
	private String username;
	private String password;
	

	public String login(){
		String input = restClient.checkCredentials(username,password);
		if (input != null && !input.equals("")) {
			HttpSession session = (HttpSession) FacesContext
										          .getCurrentInstance()
										          .getExternalContext()
										          .getSession(false);
            session.setAttribute("token", input);
            session.setAttribute("username", username);
			return "index";
		} else {
			return "login";
		}
		
	}
	
	public String logout() {
	      HttpSession session = (HttpSession) FacesContext
										          .getCurrentInstance()
										          .getExternalContext()
										          .getSession(false);
	      session.invalidate();
	      return "login";
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
