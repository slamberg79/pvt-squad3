package test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class LoginTest extends SpringTestApplicationTests{

    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
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
 

    /*

    //Not sure what perform(formLogin()) does, or what status is expected. 
    //There must be a better way to test a user logging in.
    
    @Test
    public void loginAsUser() throws Exception{
        mockMvc.perform(formLogin("/login").user("test").password("test")).andExpect(status().isFound());
    }
    
    @Test
    public void loginWrongUsername() throws Exception{
        mockMvc.perform(formLogin("/login").user("error").password("test")).andExpect(status().isUnauthorized());
    }
    
    @Test
    public void loginWrongPassword() throws Exception{
        mockMvc.perform(formLogin("/login").user("test").password("error")).andExpect(status().isUnauthorized());
    }
    
    */

}
