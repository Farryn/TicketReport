package entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserJsonVO {

	public UserJsonVO() {
	}
	
	public UserJsonVO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	private String username;
	private String password;
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
