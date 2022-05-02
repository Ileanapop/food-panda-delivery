package com.example.demo.service.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.repository.AdministratorRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

@Component
public class JWTFilter extends OncePerRequestFilter {


    @Autowired private JWTUtil jwtUtil;
    @Autowired private AdministratorRepository administratorRepository;
    @Autowired private CustomerRepository customerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
        if(authHeader != null && !authHeader.isEmpty() && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            System.out.println(jwt);
            if(jwt.isEmpty()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
            }else {
                try{
                    String username = jwtUtil.validateTokenAndRetrieveSubject(jwt);
                    System.out.println(username);
                    String type = jwtUtil.retrieveUserType(jwt);
                    UserDetailsService userDetailsService;
                    if(type.equals("admin")){
                        userDetailsService = new AdminDetailService(administratorRepository);
                    }
                    else
                        userDetailsService = new CustomerDetailsService(customerRepository);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    System.out.println(userDetails);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
                    if(SecurityContextHolder.getContext().getAuthentication() == null){
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        System.out.println("_-------------");
                        System.out.println(authToken);
                    }
                }catch(JWTVerificationException exc){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                }

            }
        }

        try{
            filterChain.doFilter(request, response);
        }
        catch (Exception e){
            System.out.println("Exception");
        }

    }
}
