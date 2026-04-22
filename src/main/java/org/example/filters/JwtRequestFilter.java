package org.example.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain)
            throws ServletException, IOException {

        String jwt = null;
        boolean fromCookie = false;

        // 1. Try cookie first (admin SSR pages)
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("admin_jwt".equals(c.getName())) {
                    jwt = c.getValue();
                    fromCookie = true;
                    break;
                }
            }
        }

        // 2. Fallback: Authorization header (REST API)
        if (jwt == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);
            }
        }

        if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                if (jwtUtil.validateToken(jwt)) {
                    String username = jwtUtil.getSubject(jwt);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            username, null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    // Auto-refresh token if expiring within 30 minutes (cookie-based only)
                    if (fromCookie && jwtUtil.isExpiringSoon(jwt)) {
                        String newToken = jwtUtil.generateToken(username);
                        Cookie refreshed = new Cookie("admin_jwt", newToken);
                        refreshed.setHttpOnly(true);
                        refreshed.setPath("/");
                        refreshed.setMaxAge(7200);
                        response.addCookie(refreshed);
                    }
                }
            } catch (Exception ignored) {
                // Invalid token — SecurityContext remains empty, Security will handle redirect
            }
        }

        chain.doFilter(request, response);
    }
}
