package managedBeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ejb.MainLogic;

/**
 * @author Damir Tuktamyshev
 *
 */
@ManagedBean
@SessionScoped
public class FormBean {

	public FormBean() {
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String dateStr = sdf.format(date);
		dateFrom = dateStr;
		dateTo = dateStr;
	}
	
	@EJB
	private MainLogic mainLogic; 
	
	private String dateFrom;
	private String dateTo;
	private String error;
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
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
		HttpSession session = (HttpSession) FacesContext
		          .getCurrentInstance()
		          .getExternalContext()
		          .getSession(false);
		String token = (String) session.getAttribute("token");
		error="";
		String status = mainLogic.process(dateFrom, dateTo, token);
		if (!status.equals("OK")) {
			error = status;
		}
		return "index";
	}
	
}
