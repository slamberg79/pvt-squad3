package login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.NullRequestCache;
 
/**
 * 
 * Login security configuration.
 * 
 * @author c13hbd
 *
 */

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomLoginHandler customLoginHandler;
    
    
    //Set username, password and role
    //The role can be used to allow role-specific actions
    //Current roles are "USER" and "ADMIN".
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("test").password("test").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN"); //login with user and password
    }
    
    
    
    //Custom login configuration. Currently redirects to "/login" when not logged in
    //Allows for custom login.html
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        
        http
            .authorizeRequests()
                .antMatchers("/resources/**").permitAll() //Everyone can access /resources
                .antMatchers("/loggedin").access("hasRole('USER') or hasRole('ADMIN')") //Users and admins can access /loggedin
                .antMatchers("/admin").access("hasRole('ADMIN')") //Admins can access /admin
                .antMatchers("/login").permitAll()
                .antMatchers("/user").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                //.successHandler(customLoginHandler) //Custom redirection based on role
                .defaultSuccessUrl("/loggedin", true) //always redirect to "/loggedin"
                .and()
            .logout()
                .logoutUrl("/logout")
                .permitAll()
                .and()
            .csrf(); //Might be problems with invalid csrf token. Enabled by default?
    }
    
    
}
