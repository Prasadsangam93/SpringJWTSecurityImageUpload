package com.springsecurity.CustProdOrdePaymentService.config;


import com.springsecurity.CustProdOrdePaymentService.exception.BadRequestCls;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@Slf4j
/* this class execute each request*/
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailasService myUserDetailasService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("doFilter method entered..");

            // Skip JWT filter for multipart requests (file uploads)
            if (request.getContentType() != null && request.getContentType().startsWith("multipart/")) {
                log.info("Skipping JWT filter for multipart request...");
                filterChain.doFilter(request, response);
                return;
            }

            String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                log.info("Authorization header missing or invalid format...");
                filterChain.doFilter(request, response);
                return;
            }

            String token = header.substring(7);
            String userName = JwtService.getSubjectFromToken(token);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userInfo = myUserDetailasService.loadUserByUsername(userName);

                if (userInfo != null && JwtService.isTokenValid(token)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.info("Error in JwtAuthenticationFilter: {}", e.toString());
            try {
                throw new BadRequestCls("Bad request: " + e.getMessage());
            } catch (BadRequestCls ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}




