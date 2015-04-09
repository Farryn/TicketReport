/**
 * 
 */
package ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import managedBeans.LoginBean;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import entity.TicketListVO;
import entity.TicketVO;
import entity.UserJsonVO;

/**
 * @author Damir Tuktamyshev
 *
 */
@Stateless
public class RestClientImpl implements RestClient {

	

	public RestClientImpl() {
	}

	/* (non-Javadoc)
	 * @see ejb.RestClient#getTicketList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TicketVO> getTicketList(String from, String to, String token) {
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/happytrain/tickets/" + from + "/" + to);
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("token", token);
		ClientResponse response = webResource
				.type("application/x-www-form-urlencoded")
				.accept("application/json")
                .post(ClientResponse.class, formData);
		if(response.getStatus() == 401){
			return new ArrayList<TicketVO>();
		}
		TicketListVO ticketList = response.getEntity(TicketListVO.class);
		List<TicketVO> resultList = ticketList.getTicketList();
		if (resultList == null || resultList.isEmpty()) {
			return new ArrayList<TicketVO>(); 
		}
		return resultList;
 
	}

	//comment
	@Override
	public String checkCredentials(String username, String password) {
		UserJsonVO user = new UserJsonVO(username, password);
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/happytrain/checkuser");
		ClientResponse response = webResource
								.type("application/json")
		                        .post(ClientResponse.class, user);
		String result = response.getEntity(String.class);
		return result;
		
	}
	
	

}
