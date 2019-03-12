package com.tulian.springboot.apirest.app.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration   //indica a Spring que es una clase de configuracion
@EnableAuthorizationServer   //hace falta agregar el @EnableAuthorizationServer para poder habilitar el uso de esta clase de configuracion               
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//Se configurar los permisos de autenticacion
		security.tokenKeyAccess("permitAll()")    //Genera el token cuando se valida
		.checkTokenAccess("isAuthenticated()");		//chequear o validar el token generado
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		//Se agregan cuanto cliente tengamos ya sea android, reac, angular, ios ,etc.
		clients.inMemory().withClient("angularapp") // El cliente es guardado en memoria, y se indica el nombre del cliente
		.secret(passwordEncoder.encode("root")) // se acgrega la contraseña y se codifica con encode
		.scopes("read","write")	// le damos autorizacion y permiso de lectura y escritura para que pueda manejar los registros
		.authorizedGrantTypes("password","refresh_token")	// Asignamos el tipo de autenticacion del token, se suele usar password cuando es con credenciales es decir tenemos usuarios con contraseñas propias (aunteticacion con login) serian 2 autenticacion, una del cliente y otra del usuario
		.accessTokenValiditySeconds(3600)	//refresh_token Nos permite obtener un toque actualizado, renovado antes de que caduque, con accessTokenValiditySeconds agregamos el tiempo de caducidad del token en segundo 3600s = 1hs
		.refreshTokenValiditySeconds(3600); //aqui se agrega el tiempo que va a durar valido el token
		

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken,accessTokenConverter()));
		
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean // debe ser un metodo publico ya que se pasara al contenedor de spring para ser usador por otras clases del programa
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}
	
	
}
