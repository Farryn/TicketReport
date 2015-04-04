/**
 * 
 */
package managedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ejb.MainLogic;
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
	
	@ManagedProperty(value = "#{formBean}")
	private FormBean formBean;
	
	/**
	 * @return the formBean
	 */
	public FormBean getFormBean() {
		return formBean;
	}

	/**
	 * @param formBean the formBean to set
	 */
	public void setFormBean(FormBean formBean) {
		this.formBean = formBean;
	}

	private String username;
	private String password;
	private String token;
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	public String login(){
		String input = restClient.checkCredentials(username,password);
		if (input != null && !input.equals("")) {
			token = input;
			formBean.setToken(input);
			return "index";
		} else {
			return "login";
		}
		
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