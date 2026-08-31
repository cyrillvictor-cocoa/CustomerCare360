package org.example.customercare360.Util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.customercare360.Exception.InvalidToken;
import org.example.customercare360.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
            if(!request.getServletPath().equals("/auth/register") && !request.getServletPath().equals("/auth/login")){
                try {
                    String authHeader =
                            request.getHeader("Authorization");

                    if (authHeader != null &&
                            authHeader.startsWith("Bearer ")) {

                        String token = authHeader.substring(7);

                        if (jwtService.validateToken(token)) {

                            String username =
                                    jwtService.extractUserName(token);

                            UsernamePasswordAuthenticationToken auth =
                                    new UsernamePasswordAuthenticationToken(
                                            username,
                                            null,
                                            Collections.emptyList());
                            {
                                SecurityContextHolder.getContext()
                                        .setAuthentication(auth);
                            }
                        }
                    }else throw new InvalidToken("Authorization Token missing");
                } catch (InvalidToken e) {
                    resolver.resolveException(request,response,null,e);
                }
            }

            filterChain.doFilter(request, response);


    }
}