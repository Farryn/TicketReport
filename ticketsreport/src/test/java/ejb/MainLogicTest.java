package ejb;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Damir Tuktamyshev
 *
 */
public class MainLogicTest {

	private MainLogic mainLogic;
	
	public MainLogicTest() {
	}
	
	@Test
	public void processTest() {
		mainLogic = new MainLogicImpl();
		String result = null;
		
		//test1
		try {
			result = mainLogic.process(null, "29.04.2015", "token");
			fail();
		} catch (Exception e) {
			
		}
		
		//test2
		try {
			result = mainLogic.process("29.04.2015", null, "token");
			fail();
		} catch (Exception e) {
			
		}
		//test3
		try {
			result = mainLogic.process(null, null, "token");
			fail();
		} catch (Exception e) {
			
		}
		
		//test4
		result = mainLogic.process("29.04.2015", "", "token");
		if (!"Wrong date format".equals(result))
			fail();
		
		//test5
		try {
			result = mainLogic.process("10.04.2015", "29.04.2015", "token");
			fail();
		} catch(Exception e) {
			
		}

	}
}
