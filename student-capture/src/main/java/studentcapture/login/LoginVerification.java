package studentcapture.login;


public class LoginVerification {

	
	public boolean verify(LoginUser user) {
    	System.out.println(user.getPassword());
    	System.out.println(user.getName());
		if (user.getName().equals("test") && user.getPassword().equals("test")) {
			return true;
		}
		return false;
	}

}
