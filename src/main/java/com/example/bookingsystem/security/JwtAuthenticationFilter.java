package com.example.bookingsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // if header is empty, and it doesn't use Bearer authentication
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // token exists, extract token
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);

        if (username == null) {
            // Invalid JWT, send 401 Unauthorized response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Get user details
            UserDetails userDetails;

            userDetails = userDetailService.loadUserByUsername(username);
            // Validate token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Update authentication in security context holder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // next filter
        filterChain.doFilter(request, response);
    }
}