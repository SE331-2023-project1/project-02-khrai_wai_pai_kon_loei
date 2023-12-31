package se331.project.rest.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.http.HttpMethod;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.headers((headers) -> {
            headers.frameOptions((frameOptions) -> frameOptions.disable());
        });
        http
                .csrf((crsf) -> crsf.disable())
                .authorizeHttpRequests((authorize) -> {

                    authorize.requestMatchers("api/v1/auth/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"/events").permitAll()
                            .requestMatchers("/uploadFile").permitAll()
                            .requestMatchers(HttpMethod.PUT,"/updateStudent").permitAll()
                            .requestMatchers(HttpMethod.PUT,"/updateTeacher").permitAll()
                            .requestMatchers("/annoucements/**").permitAll()
                            .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                            .requestMatchers(HttpMethod.POST,"/events").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET,"/advisor").hasRole("ADVISEE")
                            .requestMatchers(HttpMethod.POST,"/register/teacher").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST,"/register").permitAll()
                            .requestMatchers(HttpMethod.PUT,"/update/teacher").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/setStudentToTeacher").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/update/student").permitAll()
                            .requestMatchers(HttpMethod.GET,"/students").permitAll()
                            .requestMatchers(HttpMethod.GET,"/teachers").permitAll()
                            .requestMatchers(HttpMethod.GET,"/students/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"/students/{id}").permitAll()
                            .requestMatchers(HttpMethod.GET,"/searchUser/{keyword}").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout((logout) -> {
                    logout.logoutUrl("/api/v1/auth/logout");
                    logout.addLogoutHandler(logoutHandler);
                    logout.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
                })
        ;
        http.cors(withDefaults());
        return http.build();

    }
}
