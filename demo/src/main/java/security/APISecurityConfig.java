//package security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@Order(1)
//public class APISecurityConfig {
//
//@Value("${yourapp.http.auth-token-header-name}")
//private String principalRequestHeader;
//
//@Value("${yourapp.http.auth-token}")
//private String principalRequestValue;
//
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader);
//    filter.setAuthenticationManager(new AuthenticationManager() {
//
//        @Override
//        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//            String principal = (String) authentication.getPrincipal();
//            if (!principalRequestValue.equals(principal))
//            {
//                throw new BadCredentialsException("The API key was not found or not the expected value.");
//            }
//            authentication.setAuthenticated(true);
//            return authentication;
//        }
//    });
//    http.csrf()
//    .disable()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .addFilter(filter)
//            .authorizeRequests()
//            .antMatchers("/").permitAll()
//            .antMatchers(HttpMethod.GET,"/api/intervals").permitAll()
//            .anyRequest()
//            .authenticated();
//
//    return http.build();
//}
//}