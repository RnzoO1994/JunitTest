package mockito;

public class WebService {

	public void login (String user, String password, Callback calback) {
		if (checkLogin(user, password)) {
			calback.onSucces("usuario correct");
		}else {
			calback.onFail("error");
		}
	}
	
	public boolean checkLogin(String user , String password) {
		if (user.equals("Renzo") && password.equals("1234")) {
			return true;
		}
		return false;
		
	}
}
