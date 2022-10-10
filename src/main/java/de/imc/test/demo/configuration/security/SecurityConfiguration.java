package de.imc.test.demo.configuration.security;

import de.imc.test.demo.dal.services.AppUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AppUserDetailService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        final DaoAuthenticationProvider authProvider = authProvider();
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/tasks/**")
                .authenticated()
                .and()
                .addFilter(new BasicAuthenticationFilter(authenticationManager())); //pattern for authorization all requests
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userDetailService);
        provider.setPasswordEncoder(encoder());
        return provider;
    }

    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(12);
    }
}
