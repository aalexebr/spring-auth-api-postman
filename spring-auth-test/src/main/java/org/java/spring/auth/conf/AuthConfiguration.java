package org.java.spring.auth.conf;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthConfiguration {
	
	private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
	@Autowired private  JwtAuthenticationFilter jwtAuthFilter;
    @Autowired private  AuthenticationProvider authenticationProvider;
    private  LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
	}

//    @Bean
//    UserDetailsService userDetailsService() {
//	    return new UserService();
//	}
//    
//    @Bean
//	public static PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//    }
    
//    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//      
//    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//   
//    	authProvider.setUserDetailsService(userDetailsService());
//    	authProvider.setPasswordEncoder(passwordEncoder());
//   
//    	return authProvider;
//    }
//    
//    @Bean
//    FilterRegistrationBean<CorsFilter> getCorsSettings() {
//		
//        final CorsConfiguration config = new CorsConfiguration();
//        
//        // OPTIONS
////        config.setAllowCredentials(true);
//        
//        config.addAllowedOrigin("*"); // DEVELOP FE SERVER
//        
//        // HEADERS
//        config.addAllowedHeader("Content-Type");
//        config.addAllowedHeader("Authorization");
//        config.addAllowedHeader("X-XSRF-TOKEN");
//        config.addAllowedHeader("Accept");
//        
//        // METHODS
//        config.addAllowedMethod(HttpMethod.GET);
//        config.addAllowedMethod(HttpMethod.POST);
//		config.addAllowedMethod(HttpMethod.PUT);
//        config.addAllowedMethod(HttpMethod.DELETE);
//        
//        // SET CONFIG ON PATHS
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        
//        final FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        
//        return bean;
//    }

}
