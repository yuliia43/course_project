package repair_system.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import repair_system.enums.Role;
import repair_system.factories.RolesFactory;
import repair_system.services.repositoryServices.UsersService;
import repair_system.utils.SecurityEncryptor;

/**
 * @author Yuliia Shcherbakova ON 27.12.2019
 * @project spring_app
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/authorisation",
                        "/mastersStats",
                        "/registration",
                        "/pages/**",
                        "/errorPages/**",
                        "/styles/**").permitAll()
                .antMatchers("/userPage").authenticated()
                .antMatchers("/user/**").hasAnyAuthority(RolesFactory.getStringValue(Role.USER))
                .antMatchers("/manager/**").hasAnyAuthority(RolesFactory.getStringValue(Role.MANAGER))
                .antMatchers("/master/**").hasAnyAuthority(RolesFactory.getStringValue(Role.MASTER))
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/authorisation")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/signOut"))
                .logoutSuccessUrl("/authorisation")
                .permitAll();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(getBcPasswordEncoder());
        return auth;
    }

    @Bean
    public PasswordEncoder getBcPasswordEncoder() {
        return SecurityEncryptor.getEncryptor().getEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }


}
