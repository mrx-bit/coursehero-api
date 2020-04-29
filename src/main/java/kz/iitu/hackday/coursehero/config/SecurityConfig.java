package kz.iitu.hackday.coursehero.config;

import kz.iitu.hackday.coursehero.repository.UserRepository;
import kz.iitu.hackday.coursehero.security.AuthenticationTokenValidationFilter;
import kz.iitu.hackday.coursehero.security.CustomAuthenticationEntryPoint;
import kz.iitu.hackday.coursehero.security.JwtUsernameAndPasswordAuthenticationFilter;
import kz.iitu.hackday.coursehero.service.SessionService;
import kz.iitu.hackday.coursehero.service.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;
    private final SessionService sessionService;
    private final AuthenticationTokenValidationFilter authenticationTokenValidationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          SessionService sessionService,
                          AuthenticationTokenValidationFilter authenticationTokenValidationFilter,
                          CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.sessionService = sessionService;
        this.authenticationTokenValidationFilter = authenticationTokenValidationFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .addFilter(authenticationFilter())
                .addFilterAfter(authenticationTokenValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/auth/**", "/swagger-ui.html", "/v2/api-docs/**", "/webjars/**", "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/catalogs/**").permitAll()
                .antMatchers("/users/hello").permitAll()
                .anyRequest().authenticated();
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    public JwtUsernameAndPasswordAuthenticationFilter authenticationFilter() throws Exception {
        return new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), sessionService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
