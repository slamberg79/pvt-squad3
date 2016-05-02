package login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


/**
 * 
 * TODO: Implement this class properly. Not sure if this will work atm
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
public class CustomLoginHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    private static String ADMIN_URL = "/admin";
    private static String USER_URL = "/loggedin";
    
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    protected void redirect(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        
        String target = getTargetUrl(authentication);
        
        redirectStrategy.sendRedirect(request, response, target);
        
    }

    private String getTargetUrl(Authentication authentication) {
        
        String url = "";
        
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();

        List<String> roles = new ArrayList<String>();
        
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
            System.out.println(a.getAuthority());
        }
        
        if(roles.contains("ROLE_ADMIN")){
            url = ADMIN_URL;
        }else if(roles.contains("ROLE_USER")){
            url = USER_URL;
        }
        
        return url;
    }
    
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
