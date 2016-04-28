package login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


/**
 * 
 * Custom login handler to redirect different roles to different pages.
 * 
 * For example, ADMIN will be redirected to /admin, while USER will be redirected to /loggedin.
 * 
 * 
 * @author c13hbd
 *
 */
public class CustomLoginHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    protected void redirect(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        
    }

}
