import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.pojo.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;
import com.revature.servlet.LoginServlet;

public class LoginServletTest {
	
	private static UserService userService = new UserServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		LoginServlet ls = new LoginServlet();
		User user = userService.loginUser("cuser@gmail.com", "cpass");
	}

}
