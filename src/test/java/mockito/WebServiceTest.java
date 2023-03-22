package mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WebServiceTest {
	@Mock
	private Callback callback;
	
	private WebService webservice;
	
	@BeforeEach
	public void SetUp() {
		webservice = new WebService();
		MockitoAnnotations.initMocks(this);
		//System.out.println("@BeforeEach -> setup()");
	}
	@Test
	public void checkLoginTest () {
		assertTrue(webservice.checkLogin("Renzo", "1234"));  
 	}
	@Test
	public void checkLoginErrorTest () {
		assertFalse(webservice.checkLogin("Pamela", "pameliz"));  
 	}
	@Test
	public void loginTest() {
		webservice.login("Renzo","1234", callback);
		verify(callback).onSucces("usuario correct");
	}
	
	@Test
	public void loginErrorTest() {
		webservice.login("Renzo1","1234", callback);
		verify(callback).onFail("error");
	}
}
