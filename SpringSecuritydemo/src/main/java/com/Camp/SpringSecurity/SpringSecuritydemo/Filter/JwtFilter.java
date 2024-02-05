package com.Camp.SpringSecurity.SpringSecuritydemo.Filter;

import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerService.JwtService;
import com.Camp.SpringSecurity.SpringSecuritydemo.WorkerService.WorkerUserDetailService;
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
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
     @Autowired
     private JwtService jwtService;
     @Autowired
     private WorkerUserDetailService workerUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // capture header from HttpServlet request
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        // if authorization header is not null and has a JWT token which is also called bearer token in code
if(authHeader != null && authHeader.startsWith("Bearer ")){
    // extract the token
    token = authHeader.substring(7);
    userName = jwtService.getUserName(token);
}
 // now if they are not already authenticated and sch is not set up
  if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
      UserDetails userDetails = workerUserDetailService.loadUserByUsername(userName);
    if(jwtService.vlidateToken(token,userDetails)) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                userDetails,null,userDetails.getAuthorities());

        upToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(upToken);
    }
  }
  // this method will pass the filter chain to next filter
  filterChain.doFilter(request,response);

    }
}
