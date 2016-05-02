package login;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import login.User;

@RestController
public class LoginController {
	private User user;
	private LoginVerification loginVerification = new LoginVerification();
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String setUserJSON(@RequestBody User user) {
    	this.user = user;
    	System.out.println("POST");
    	System.out.println(user.getPassword());
    	System.out.println(user.getName());
    	
    	if(loginVerification.verify(user)) {
    		System.out.println("USER IS VERIFIED");
    		return "loggedin";
    	} else {
    		System.out.println("USER IS NOT VERIFIED");
    	}
        
        return "login";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
        return user;
    }
    
    
    /**
     * TODO: Refactor these ModelAndView methods
     * 
     */
    
    //Jag har kommenterat bort dessa därför ViewController sköter om att byta till denna vy.
    //Bör vi ha en separat view controller eller bara köra allt på en controller? imo så blir det typ bättre med en separat view controller
    //så sköter man HTTP requests med RestController
    /*
    @RequestMapping(value = {"/loggedin"}, method = RequestMethod.GET)
    public ModelAndView loggedinPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Loggedin page");
        model.addObject("message", "You are logged in!");
        model.setViewName("loggedin");
        return model;

    }
    */
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Admin page");
        model.addObject("message", "You are logged in as admin!");
        model.setViewName("admin");
        return model;
    }
    
}
