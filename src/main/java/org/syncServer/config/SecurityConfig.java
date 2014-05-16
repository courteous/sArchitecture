package org.syncServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebMvcSecurity
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
//        	.headers().disable()
        	.authorizeRequests()
                .antMatchers(	"/register",
                				"/h2/**",
                				"/static/**",
                				"/resources/**",
        						"/static/css/**", 
                				"/static/img/**" , 
                				"/static/js/**", 
                				"/static/pdf/**",
        						"/resources/static/css/**", 
                				"/resources/static/img/**" , 
                				"/resources/static/js/**", 
                				"/resources/static/pdf/**",
                				"/pdf/**",
                				"/css/**",
                				"/js/**",
                				"/img/**"
                				).permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }


    
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//           auth.inMemoryAuthentication()
//   
//            .withUser("admin2").password("#passworD").roles("USER");
//        }
    
        
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
              
              auth
              .inMemoryAuthentication()
              .withUser("admin2").password("#passworD").roles("USER");
             
          }
        
//    @Autowired
//    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//        
//        .userDetailsService(iUserDetailsService);        
//    }
  
}
