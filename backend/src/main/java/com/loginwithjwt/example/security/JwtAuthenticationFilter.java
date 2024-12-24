package com.loginwithjwt.example.security;

import com.loginwithjwt.example.config.AppConfig;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (isWhitelisted(path)) {
            filterChain.doFilter(request, response); // Skip JWT validation
            return;
        }
        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;
        try{
            if(requestHeader != null && requestHeader.startsWith("Bearer")){
                token = requestHeader.substring(7);
                username = jwtHelper.getUserNameFromToken(token);
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    Boolean validateToken = jwtHelper.validateToken(token,userDetails);
                    if(validateToken){
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                    else {
                        System.out.println("Validation Failed");
                    }
                }
                filterChain.doFilter(request,response);
            }
            else{
                handleException(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT Token is empty or null");
            }
        } catch (Exception e) {
            System.out.println("Exception : "+e);
            handleException(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
        }
    }

    private boolean isWhitelisted(String requestPath) {
        List<String> stringList = Arrays.asList(AppConfig.AUTH_WHITELIST);
        return stringList.stream().anyMatch(path -> requestPath.matches(path.replace("**", ".*")));
    }

    private void handleException(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
