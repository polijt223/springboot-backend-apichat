package com.tulian.springboot.apirest.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usuarioService;

	@Bean   //Objetos que retornan metodos, nos permite injectar con autowired , es especifico para objetos que retornan metodos
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}


	  										//El objetivo es inyectar la clase AuthenticationManager, pero ocurre que esta es una clase que se obtiene por la extencion de WebSecurityConfigurerAdapter
	@Bean("authenticationManager") 			// por ello la unica forma de pasarsela al contenedor de spring para luego ser inyectable es con un @Override sobre la clase AuthenticationManager y a ese
	@Override								//metodo pasarle @Bean que es ña fprma de inyectar metodo que devuelven una clase
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//Esta linae se utilizaria si estubieramos autenticando y guardando la informacion en sesiones como cuando se trabaja con spring mvc, pero al ser esto solo backend se utilizan tokens para y la informacion se maneja con angular del lado del front
		//http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/clientes").permitAll()
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Desabilitamos la autenticacion por sesiones(SessionCreationPolicy.STATELESS)  ya que usamos tokens para autenticar
	}
	
	
	
	
	
}
