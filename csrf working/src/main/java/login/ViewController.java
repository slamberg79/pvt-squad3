package login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {
    	System.out.println("GET VIEW");
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String index2() {
    	System.out.println("POST VIEW");
        return "login";
    }
}	
