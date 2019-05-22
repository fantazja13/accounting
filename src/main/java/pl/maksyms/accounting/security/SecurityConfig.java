package pl.maksyms.accounting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.maksyms.accounting.security.auth.filter.RestUsernamePasswordAuthenticationFilter;
import pl.maksyms.accounting.security.auth.filter.JWTAuthenticationFilter;
import pl.maksyms.accounting.security.auth.handler.RestAuthenticationEntryPoint;
import pl.maksyms.accounting.security.auth.handler.RestAuthenticationFailureHandler;
import pl.maksyms.accounting.security.auth.handler.RestAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/auth", "/auth/**", "/h2", "/h2/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(restUsernamePasswordAuthenticationFilter())
                .addFilter(JWTAuthenticationFilter())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    private RestUsernamePasswordAuthenticationFilter restUsernamePasswordAuthenticationFilter() throws Exception{
        RestUsernamePasswordAuthenticationFilter filter =
                new RestUsernamePasswordAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/auth/login");
        filter.setAuthenticationFailureHandler(authFailureHandler());
        filter.setAuthenticationSuccessHandler(authSuccessHandler());
        return filter;
    }

    private JWTAuthenticationFilter JWTAuthenticationFilter() throws Exception{
        return new JWTAuthenticationFilter(authenticationManager(), userDetailsService());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    private AuthenticationFailureHandler authFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    private AuthenticationSuccessHandler authSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    private AuthenticationEntryPoint authEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }
}
