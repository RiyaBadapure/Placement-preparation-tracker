package com.example.demo.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtService jwtService,UserDetailsService userDetailsService)
    {
        this.jwtService=jwtService;
        this.userDetailsService=userDetailsService;
    }

    

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws IOException,ServletException
    {

        String path = request.getServletPath();

if (path.startsWith("/h2-console")
    || path.equals("/auth/login")
    || path.equals("/auth/register")) {

    filterChain.doFilter(request, response);
    return;
};
        String authHeader=request.getHeader("Authorization");

        if(authHeader!=null && authHeader.startsWith("Bearer "))
        {
            String token=authHeader.substring(7);

            String username=jwtService.extractUsername(token);
            System.out.println("Username from JWT: " + username);

            UserDetails userDetails=userDetailsService.loadUserByUsername(username);

            if(jwtService.validateToken(token,userDetails))
            {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                null,
                    userDetails.getAuthorities()
                );

                SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

                    
            }
        }  
        filterChain.doFilter(request, response);
      
    }

    
}
