package com.test.danstest.configs;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        try {
            String jwt = getJwtFromRequest((HttpServletRequest) request);
            logger.error(jwt);
            if (StringUtils.hasText(jwt)) {
                Claims claims = Jwts.parser().setSigningKey("app-secret")
                        .parseClaimsJws(jwt).getBody();

                String username = claims.getSubject();
                logger.error(username);
                httpRequest.setAttribute("username", username);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
        }
        filterChain.doFilter(request, response);
    }
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
