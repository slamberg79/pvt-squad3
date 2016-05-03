package login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }
    
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String getLoggedin() {
        return "loggedin";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmin() {
        return "admin";
    }
    
    
}	
