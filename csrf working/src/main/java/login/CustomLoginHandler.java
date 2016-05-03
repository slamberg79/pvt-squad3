package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * 
 * 
 * 
 * Custom login handler to redirect different roles to different pages.
 * 
 * For example, ADMIN will be redirected to /admin, while USER will be redirected to /loggedin.
 * 
 * This will be used in LoginSecurityConfig.java as a custom login handler.
 * 
 * @author c13hbd
 *
 */

@Component
public class CustomLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {
   
    private static String ADMIN_URL = "/admin";
    private static String USER_URL = "/loggedin";
    
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        
        //Get role of the logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        //Select url based on role
        String targetUrl = "";
        if(role.contains("USER")) {
            System.out.println("QEWWEYTUGAD");
            targetUrl = USER_URL;
        } else if(role.contains("ADMIN")) {
            targetUrl = ADMIN_URL;
        }
        return targetUrl;
    }

}
