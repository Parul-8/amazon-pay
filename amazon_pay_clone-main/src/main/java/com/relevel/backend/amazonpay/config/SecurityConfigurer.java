
package com.relevel.backend.amazonpay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.relevel.backend.amazonpay.services.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().headers().frameOptions().disable().and().authorizeRequests()
				.antMatchers("/ping").authenticated() //no need of any role
				.antMatchers("/users**").hasAnyRole("NORMAL") //role mapping is required
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated().and().formLogin().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
		/*
		 * 
		 * auth.inMemoryAuthentication() .withUser("parul1") .password("par1")
		 * .roles("NORMAL") .and() .withUser("parul2") .password("par2")
		 * .roles("ADMIN");
		 * 
		 * 
		 * }
		 */ 

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/h2console/**");

	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//
//	}

	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
