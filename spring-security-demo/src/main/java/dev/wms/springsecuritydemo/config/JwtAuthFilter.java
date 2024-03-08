package dev.wms.springsecuritydemo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwt;
        if (!isCookiesEmpty(request) && isJwtCookiePresent(request)) {
            jwt = extractJwtCookie(request);
        } else {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String userName = jwtService.extractUsername(jwt);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

            if (isAuthenticationEmpty() && jwtService.isTokenValid(jwt, userDetails)) {
                    authenticateUser(userDetails, request, jwt);
            }
        } catch (Exception ex) {
            request.logout();
        }
        filterChain.doFilter(request, response);
    }

    private boolean isCookiesEmpty(HttpServletRequest request) {
        return request.getCookies() == null;
    }

    private boolean isAuthenticationEmpty() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private boolean isJwtCookiePresent(HttpServletRequest request) {
        return Arrays.stream(request.getCookies()).anyMatch(cookie -> cookie.getName().equals("JWT"));
    }

    private String extractJwtCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("JWT")).findFirst().get().getValue();
    }

    private void authenticateUser(UserDetails userDetails, HttpServletRequest request, String jwt) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                jwt,
                userDetails.getAuthorities());

        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}

