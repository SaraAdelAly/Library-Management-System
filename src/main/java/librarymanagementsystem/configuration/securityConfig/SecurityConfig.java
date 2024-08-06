//package librarymanagementsystem.configuration.securityConfig;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//public class SecurityConfig {
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
////        configureBasicAuthentication(httpSecurity);
////        httpSecurity.csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/**").authenticated()
////                        .requestMatchers("/api/admin/**").hasAnyRole("Admin")
////                        .anyRequest().permitAll()
////                ).sessionManagement(session -> session
////                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                )
////                .authenticationProvider(authenticationProvider)
////                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
////        return httpSecurity.build();
////    }
//}
