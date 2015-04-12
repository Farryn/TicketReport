package ejb;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entity.TicketVO;

/**
 * @author Damir Tuktamyshev
 *
 */
public class CreatePDFTest {

	 
	public CreatePDFTest() {
	}
	
	
	private CreatePDF createPDF;
	
	@Test
	public void createTest(){
		createPDF =  new CreatePDFImpl();
		
		List<TicketVO> ticketList = new ArrayList<TicketVO>();
		OutputStream stream = null;
	    try {
			 stream = new FileOutputStream("filename");
		} catch (FileNotFoundException e) {
			fail();
		}
		//test 1
	   String result = createPDF.create(ticketList, stream, "filename");
	   if (!"Empty List".equals(result))
		   fail();
	   
	   //test2
	   ticketList.add(new TicketVO());
	   result = createPDF.create(ticketList, stream, "filename");
	   if (!"OK".equals(result))
		   fail();
	   
	   //test3
	   try {
		   result = createPDF.create(ticketList, null, "filename");
		   fail();
	   } catch(Exception e) {
		   
	   }
	   
	 //test4
	   try {
		   result = createPDF.create(null, stream, "filename");
		   fail();
	   } catch(Exception e) {
		   
	   }
	}
}
