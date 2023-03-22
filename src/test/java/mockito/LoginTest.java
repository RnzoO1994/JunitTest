package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class LoginTest {

	@InjectMocks
	private Login login;
	@Mock
	private WebService webService;

	@Captor
	private ArgumentCaptor<Callback> callbacArgumentCaptor;

	@BeforeEach
	public void SetUp() {
		MockitoAnnotations.initMocks(this);
		// System.out.println("@BeforeEach -> setup()");
	}

	@Test
	public void doLogintest() {
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
				String user = (String) invocationOnMock.getArguments()[0];
				assertEquals("Renzo", user);
				String password = invocationOnMock.getArgument(1).toString();
				assertEquals("1234", password);
				Callback callback = (Callback) invocationOnMock.getArguments()[2];
				callback.onSucces("OK");
				/*
				 * if (user.equals("Renzo")) { callback.onSucces("OK"); }else {
				 * callback.onFail("error"); }
				 */
				return null;
			}

		}).when(webService).login(anyString(), anyString(), any(Callback.class));

		login.doLogin();
		verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
		assertEquals(login.isLogin, true);
	}

	@Test
	public void doLoginErrortest() {
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
				String user = (String) invocationOnMock.getArguments()[0];
				assertEquals("Renzo", user);
				String password = invocationOnMock.getArgument(1).toString();
				assertEquals("1234", password);
				Callback callback = (Callback) invocationOnMock.getArguments()[2];
				callback.onFail("error");
				return null;
			}
		}).when(webService).login(anyString(), anyString(), any(Callback.class));

		login.doLogin();
		verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
		assertEquals(login.isLogin, false);
	}
	@Test
	public void doLoginCaptorTest()
	{
		login.doLogin();
		verify(webService, times(1)).login(anyString(), anyString(), callbacArgumentCaptor.capture());
		assertEquals(login.isLogin,false);
		Callback callback =  callbacArgumentCaptor.getValue();
		callback.onSucces("OK");
		assertEquals(login.isLogin,true);
		callback.onFail("error");
 		assertEquals(login.isLogin,false);


	}
}
