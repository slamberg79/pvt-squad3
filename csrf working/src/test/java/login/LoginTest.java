package login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * JUnit tests for the login system.
 * 
 * 
 * 2016-05-02
 * Current working user is: 
 *      username: test
 *      password: test
 * Current working admin is:
 *      username: admin
 *      password: admin
 * 
 * 
 * 
 * @author c13hbd
 *
 */
public class LoginTest extends SpringLoginApplicationTests{

    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
    CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    public void getLoginPage() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }
    
    @Test
    public void getLoggedInPage() throws Exception{
        mockMvc.perform(get("/loggedin")).andExpect(status().isFound()); //Expect redirection to login page (status <302>)
    }
    
    @Test
    @WithMockUser(roles="USER")
    public void getUserPageAsUser() throws Exception{
        mockMvc.perform(get("/loggedin")).andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(roles="ADMIN")
    public void getUserPageAsAdmin() throws Exception{
        mockMvc.perform(get("/loggedin")).andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(roles="USER")
    public void getAdminPageAsUser() throws Exception{
        mockMvc.perform(get("/admin")).andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(roles="ADMIN")
    public void getAdminPageAsAdmin() throws Exception{
        mockMvc.perform(get("/admin")).andExpect(status().isOk());
    }
    
    @Test
    public void loginAsUser() throws Exception{
        mockMvc.perform(post("/login")
                .with(csrf()) //csrf is required
                .param("username", "test")
                .param("password", "test"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/loggedin"));
    }
    
    @Test
    public void loginWrongDetails() throws Exception{
        
        //Wrong username
        mockMvc.perform(post("/login")
                .with(csrf()) //csrf is required
                .param("username", "error")
                .param("password", "test"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login?error"));
        
        //Wrong password
        mockMvc.perform(post("/login")
                .with(csrf()) //csrf is required
                .param("username", "error")
                .param("password", "test"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login?error"));
    }
    
    @Test
    public void logout() throws Exception{
        
        
        //TODO: require login to be able to logout ?
        /*
        //Login as user
        mockMvc.perform(post("/login")
                .with(csrf()) //csrf is required
                .param("username", "test")
                .param("password", "test"))
                .andExpect(redirectedUrl("/loggedin"));
                */
        
        mockMvc.perform(post("/logout").with(csrf()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login?logout"));
       
        
    }
 
}