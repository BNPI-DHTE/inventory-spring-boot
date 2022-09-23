package hu.bnpi.dhte.security.configuration;

import hu.bnpi.dhte.security.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class InventorySecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final AuthService authService;

    @Autowired
    public InventorySecurityConfig(PasswordEncoder passwordEncoder, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/css/*", "/js/*").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                    .loginPage("login")
                    .permitAll()
                    .defaultSuccessUrl("homepage", true)
                    .passwordParameter("password")
                    .usernameParameter("username").and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("somethingVerySecured")
                    .rememberMeParameter("remember-me").and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("logout", "GET")) //csrf nélkül ez mindenképpen javasolt
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JESSIONID", "remeber-me")
                    .logoutSuccessUrl("logout");
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(authService);
        return provider;
    }
}
