package com.ibp.admin.security

import com.ibp.admin.OtpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    OtpService otpService

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new VendorsAuthenticationProvider(otpService))
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/vendors/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/vendors/login")
                .loginProcessingUrl("/vendors/authenticate")
                .defaultSuccessUrl("/vendors/home")
                .failureUrl("/vendors/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/vendors/logout")
                .logoutSuccessUrl("/vendors/login?logout=true")
                .permitAll()
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers('/', '/error', '/index', '/index.gsp', '/shutdown', '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**', '/**/favicon.ico', '/vendorsLogin/login', '/vendorsLogin/sendOtp', '/vendorsLogin/authenticate').permitAll()
//                .antMatchers('/vendorsLogin/home', '/vendorsLogin/logout').authenticated()
//                .antMatchers('/user/**').authenticated()
//                .and()
//                .formLogin()
//                .loginPage('/vendorsLogin/login')
//                .loginProcessingUrl('/vendorsLogin/authenticate')
//                .defaultSuccessUrl('/vendorsLogin/home')
//                .failureUrl('/vendorsLogin/login?error=true')
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl('/vendorsLogin/logout')
//                .logoutSuccessUrl('/vendorsLogin/login?logout=true')
//                .permitAll()
//
//        return http.build()
//    }

    @Bean
    VendorsAuthenticationProvider vendorAuthenticationProvider(OtpService otpService) {
        return new VendorsAuthenticationProvider(otpService)
    }

    /* Keshav
    @Bean
    UserDetailsService userDetailsService() {
        (mobileNo) -> Vendors.findByMobileNo(mobileNo) as UserDetailsService
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService())
        authProvider
    }*/

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean()
    }
}
