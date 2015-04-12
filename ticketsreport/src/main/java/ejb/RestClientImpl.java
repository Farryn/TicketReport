/**
 * 
 */
package ejb;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

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

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(RestClientImpl.class);

	public RestClientImpl() {
	}

	/* (non-Javadoc)
	 * @see ejb.RestClient#getTicketList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TicketVO> getTicketList(String from, String to, String token) {
		LOG.info("Sending request to web-service");
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/happytrain/tickets/" + from + "/" + to);
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("token", token);
		ClientResponse response = webResource
				.type("application/x-www-form-urlencoded")
				.accept("application/json")
                .post(ClientResponse.class, formData);
		if(response.getStatus() == 401){
			LOG.warn("Bad token");
			return new ArrayList<TicketVO>();
		}
		TicketListVO ticketList = response.getEntity(TicketListVO.class);
		List<TicketVO> resultList = ticketList.getTicketList();
		if (resultList == null || resultList.isEmpty()) {
			LOG.warn("Bad Ticket List");
			return new ArrayList<TicketVO>(); 
		}
		return resultList;
 
	}

	
	/* (non-Javadoc)
	 * @see ejb.RestClient#checkCredentials(java.lang.String, java.lang.String)
	 */
	@Override
	public String checkCredentials(String username, String password) {
		LOG.info("Creating UserVO entity for json transfer");
		String hashed = encodePassword(password);
		UserJsonVO user = new UserJsonVO(username, hashed);
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/happytrain/checkuser");
		ClientResponse response = webResource
								.type("application/json")
		                        .post(ClientResponse.class, user);
		String result = response.getEntity(String.class);
		return result;
		
	}
	
	/**Encrypt password with AES algorithm
	 * @param password
	 * @return
	 */
	private String encodePassword(String password) {
		LOG.info("Encrypting password");
		SecretKey mySecretKey = generateSecretKey();
		Cipher cipher;
		String encoded = "";
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, mySecretKey);
			byte[] cipherText = cipher.doFinal(password.getBytes());
			encoded = new String(cipherText);
		} catch (Exception e) {
			LOG.warn("Exception " + e);
			return "";
		}
		return DatatypeConverter.printBase64Binary(encoded.getBytes());
	}

	private SecretKey generateSecretKey() {
		byte[] key = null;
		try {
			key = "SoSecretKey".getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			LOG.warn("Exception " + e);
			return null;
		}
		return new SecretKeySpec(key, "AES");
		
	}
}
