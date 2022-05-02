package com.example.demo.service.security;

import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private AdminDetailService adminDetailService;

        @Autowired
        private JWTFilter jwtFilterAdmin;


        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .httpBasic().disable()
                    .cors()
                    .and()
                    .authorizeHttpRequests()
                    .antMatchers("/api/administrator/**").permitAll()
                    .antMatchers("/api/restaurantActions/viewRestaurantOrders").hasRole("ADMIN")
                    .antMatchers("/api/restaurantActions/filterOrder").hasRole("ADMIN")
                    .antMatchers("/api/restaurantActions/addRestaurant").hasRole("ADMIN")
                    .antMatchers("/api/restaurantActions/viewRestaurantPendingOrders").hasRole("ADMIN")
                    .antMatchers("/api/restaurantActions/acceptOrders").hasRole("ADMIN")
                    .antMatchers("/api/restaurantActions/addFoods").hasRole("ADMIN")
                    .antMatchers("/api/restaurantActions/viewAllMenuItems").hasRole("ADMIN")
                    .antMatchers("/api/restaurantActions/exportMenuToPdf").hasRole("ADMIN")
                    .antMatchers("/foodCategories/getAllCategories").hasRole("ADMIN")
                    .antMatchers("/deliveryZones/getZones").hasRole("ADMIN")
                    .antMatchers("/api/auth/admin").hasRole("ADMIN")
                    .antMatchers("/api/customer/**").permitAll()
                    .antMatchers("/api/restaurantActions/viewRestaurantMenu").hasRole("USER")
                    .antMatchers("/api/restaurantActions/viewCustomerOrders").hasRole("USER")
                    .antMatchers("/api/restaurantActions/getAllRestaurants").hasRole("USER")
                    .antMatchers("/api/auth/customer").hasRole("USER")
                    .and()
                    .userDetailsService(adminDetailService)
                    .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, authException) ->
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(jwtFilterAdmin, UsernamePasswordAuthenticationFilter.class);


        }
        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint(){
            BasicAuthenticationEntryPoint entryPoint =
                    new BasicAuthenticationEntryPoint();
            entryPoint.setRealmName("admin realm");
            return entryPoint;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Configuration
    @Order(2)
    public static class CustomerConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomerDetailsService customerDetailsService;

        @Autowired
        private JWTFilter jwtFilterCustomer;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            System.out.println("sdbdfbdfnbdfsng-----------------------------");
            http.csrf().disable()
                    .httpBasic().disable()
                    .cors()
                    .and()
                    .authorizeHttpRequests()
                    .antMatchers("/api/customer/**").permitAll()
                    .antMatchers("/api/restaurantActions/viewRestaurantMenu").hasRole("USER")
                    .antMatchers("/api/restaurantActions/viewCustomerOrders").hasRole("USER")
                    .antMatchers("/api/restaurantActions/getAllRestaurants").hasRole("USER")
                    .antMatchers("/api/auth/customer").hasRole("USER")
                    .and()
                    .userDetailsService(customerDetailsService)
                    .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, authException) ->
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(jwtFilterCustomer, UsernamePasswordAuthenticationFilter.class);
        }
    }

}
