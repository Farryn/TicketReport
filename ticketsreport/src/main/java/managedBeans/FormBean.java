package managedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ejb.MainLogic;

@ManagedBean
@SessionScoped
public class FormBean {

	public FormBean() {
	}
	
	@EJB
	private MainLogic mainLogic; 
	
	private String dateFrom;
	private String dateTo;
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
	public String getDateFrom() {
		return dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	public String getTicketList(){
		mainLogic.process(dateFrom, dateTo, token);
		return "welcome";
	}
	
}
