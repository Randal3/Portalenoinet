package com.Portalenoinet.Authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.Portalenoinet.model.Credentials.DEFAULT_ROLE;
import static com.Portalenoinet.model.Credentials.ADMIN_ROLE;

/**
 * The AuthConfiguration is a Spring Security Configuration.
 * It extends WebSecurityConfigurerAdapter, meaning that it provides the
 * settings for Web security.
 */

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * The datasource is automatically injected into the AuthConfiguration (using
     * its getters and setters)
     * and it is used to access the DB to get the Credentials to perform
     * authentiation and authorization
     */

    @Autowired
    DataSource datasource;

    /**
     * This method provides the whole authentication and authorization configuration
     * to use.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // authorization paragraph: qui definiamo chi può accedere a cosa
                .authorizeRequests()
                // chiunque (autenticato o no) può accedere alle pagine index, login, register,
                // ai css e alle immagini
                .antMatchers(HttpMethod.GET, "/", "/index", "/login", "/dashboard", "/newUser", "/copertura",
                        "/gestioneSim",
                        "/tableSim", "/infoSim", "/gestioneOperatori", "/css/**", "/js/**", "/images/**")
                .permitAll()
                // chiunque (autenticato o no) può mandare richieste POST al punto di accesso
                // per login e register
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                // solo gli utenti autenticati con ruolo ADMIN possono accedere a risorse con
                // path /admin/**
                .antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/default/**").hasAnyAuthority(DEFAULT_ROLE)
                .antMatchers(HttpMethod.POST, "/default/**").hasAnyAuthority(DEFAULT_ROLE)
                // tutti gli utenti autenticati possono accere alle pagine rimanenti
                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage("/login").failureUrl("/index#login").defaultSuccessUrl("/dashboard")

                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .clearAuthentication(true).permitAll().and().csrf().disable();
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(this.datasource)
                .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }

    /**
     * This method defines a "passwordEncoder" Bean.
     * The passwordEncoder Bean is used to encrypt and decrpyt the Credentials
     * passwords.
     */

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
