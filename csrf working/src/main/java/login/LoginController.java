package login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import login.User;

@RestController
public class LoginController {
	private static User user;
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void setUserJSON(@RequestBody User user) {
    	System.out.println("POST");
    	System.out.println(user.getPassword());
    	System.out.println(user.getName());
        this.user = user;
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
    	System.out.println("GET");
        return user;
    }
    
    
    /**
     * TODO: Refactor these ModelAndView methods
     * 
     */
    
    @RequestMapping(value = {"/loggedin"}, method = RequestMethod.GET)
    public ModelAndView loggedinPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Loggedin page");
        model.addObject("message", "You are logged in!");
        model.setViewName("loggedin");
        return model;

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
