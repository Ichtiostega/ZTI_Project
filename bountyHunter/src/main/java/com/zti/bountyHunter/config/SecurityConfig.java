package com.zti.bountyHunter.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
* A configuration class that defines securtity settings.
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	/**
	* Configures the HttpSecurity module
	*
	* @param http	HttpSecurity object
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/", "/register", "/css/main.css").permitAll()
				.antMatchers("POST", "/add_contract").hasAuthority("CONTRACTOR")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}

	/**
	* Configures the AuthenticationManager
	*
	* @param auth	AuthenticationManagerBuilder object
	*/
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}
}