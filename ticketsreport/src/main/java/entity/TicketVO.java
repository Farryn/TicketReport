package entity;



public class TicketVO {

	public TicketVO() {
	}

	private String depTime;
	private String firstName;
	private String lastName;
	private String stationFrom;
	private String stationTo;
	
	public String getDepTime() {
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
	public void setDepTime(String depTime) {
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
