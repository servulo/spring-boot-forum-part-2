package br.com.sprj.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Authentication
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Authorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topics").permitAll()
		.antMatchers(HttpMethod.GET, "/topics/*").permitAll()
		.anyRequest().authenticated().and().formLogin();
    }

    /**
     * JS, CSS, Images, etc
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }

}