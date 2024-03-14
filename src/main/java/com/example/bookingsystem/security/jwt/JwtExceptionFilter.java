package com.example.bookingsystem.security.jwt;

import com.example.bookingsystem.security.jwt.JwtErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            // if JWT is expired
            setResponse(response, JwtErrorMessage.EXPIRED_TOKEN);
        } catch (MalformedJwtException e) {
            // if JWT is malformed
            setResponse(response, JwtErrorMessage.MALFORMED_TOKEN);
        } catch (JwtException e) {
            // other exceptions
            setResponse(response, JwtErrorMessage.UNKNOWN_ERROR);
        }
    }

    private void setResponse(
            HttpServletResponse response,
            JwtErrorMessage jwtErrorMessage) throws IOException {

        response.setStatus(jwtErrorMessage.getHttpStatus().value());
        response.setContentType("application/json");
        // construct JSON response
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", jwtErrorMessage.getTimestamp());
        errorDetails.put("status", jwtErrorMessage.getHttpStatus().value());
        errorDetails.put("error", jwtErrorMessage.getHttpStatus().getReasonPhrase());
        errorDetails.put("message", jwtErrorMessage.getMessage());

        // convert map to JSON string
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(errorDetails);

        // write JSON response to the output stream
        response.getWriter().write(jsonResponse);
    }
}