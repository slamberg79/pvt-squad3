package login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {
        return "login";
    }
    
    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String index2() {
        return "loggedin";
    }
}	
