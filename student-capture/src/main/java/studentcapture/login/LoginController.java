package studentcapture.login;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import studentcapture.login.LoginVerification;
import studentcapture.login.LoginUser;

@RestController
public class LoginController {
	private LoginUser user;
	private LoginVerification loginVerification = new LoginVerification();
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String setUserJSON(@RequestBody LoginUser user) {
    	this.user = user;   	
    	if(loginVerification.verify(user)) {
    		System.err.println("USER IS VERIFIED");
    		return "loggedin";
    	} else {
    		System.err.println("USER IS NOT VERIFIED");
    	}
        
        return "login";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public LoginUser getUser() {
        return user;
    }    
    
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Admin page");
        model.addObject("message", "You are logged in as admin!");
        model.setViewName("admin");
        return model;
    }   
}
