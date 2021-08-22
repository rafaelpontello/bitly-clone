package com.rpontello.clones.bitly.config.security;

import com.rpontello.clones.bitly.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String BEARER_TOKEN = "Bearer";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        var token = extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));

        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
        }

        UserDetails userDetails = userRepository
                .findByUsername(jwtTokenUtil.getUsername(token))
                .orElse(null);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                Optional.ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(List.of())
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }


    private String extractToken(String header) {
        if (header != null && header.startsWith(BEARER_TOKEN)) {
            return header.split(" ")[1].trim();
        }
        return null;
    }

}
