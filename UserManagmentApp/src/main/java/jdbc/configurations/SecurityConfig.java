package jdbc.configurations;

import jdbc.services.UserDetailServiceImp;
import jdbc.utils.RoleBasedLandingPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImp userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/table", "/edituser", "/adduser").hasAnyAuthority("Admin")
                .antMatchers("/hello").hasAnyAuthority("User", "Admin")
                .antMatchers("/register").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated().and().httpBasic();

        http.formLogin().loginPage("/login")
                .successHandler(new RoleBasedLandingPage())
                .failureUrl("/login?error=true")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
