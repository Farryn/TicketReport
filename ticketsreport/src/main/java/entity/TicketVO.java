package entity;

import java.util.Date;



public class TicketVO {

	public TicketVO() {
	}

	private Date depTime;
	private String firstName;
	private String lastName;
	private String stationFrom;
	private String stationTo;
	
	public Date getDepTime() {
		return depTime;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getStationFrom() {
		return stationFrom;
	}
	public String getStationTo() {
		return stationTo;
	}
	public void setDepTime(Date depTime) {
		this.depTime = depTime;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setStationFrom(String stationFrom) {
		this.stationFrom = stationFrom;
	}
	public void setStationTo(String stationTo) {
		this.stationTo = stationTo;
	}
	
	
}
