package ejb;

import org.junit.Test;

/**
 * @author Damir Tuktamyshev
 *
 */
public class RestClientTest {

	public RestClientTest() {
	}

	private RestClient restClient;
	
	@Test
	public void getTicketListTest() {
		
	}
	
	@Test
	public void checkCredentialsTest() {
		restClient = new RestClientImpl();
		
		/*//test 1
		String result = restClient.checkCredentials("client", "client");
		if (result != null )
			fail();*/
		
		/*//test2
		result = restClient.checkCredentials("admin", "admin");
		if (result == null )
			fail();*/
		
	}
}
