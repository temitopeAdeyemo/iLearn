package com.backend.iLearn.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // To show it is a string bean
@RequiredArgsConstructor //Will create a constructor using any final string declared in there
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("in doFilterInternal *******************");
        final String authHeader= request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        System.out.println("authHeader -------------------_>" + authHeader);
        System.out.println("*********************    6");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            System.out.println("Checking authHeader if null or no Bearer *********************    6***");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        userEmail = jwtService.extractUsername(jwt);
        System.out.println("?//???????????????????????????????"+ SecurityContextHolder.getContext().getAuthentication());
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println("if userEmail or SecurityContextHolder.getContext().getAuthentication() is null ----------------------------------------------------------->    7");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt, userDetails)){
                System.out.println("Token is Valid *********************    8");
                System.out.println("UsernamePasswordAuthenticationToken next ********************* ");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                System.out.println("*********************    9");
                System.out.println("*********************    setting authToken Details in next line");
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                System.out.println(authToken.getDetails() + ">>-----------------====>"+ authToken.getDetails().toString());
                System.out.println("SecurityContextHolder.getContext().setAuthentication(authToken) next *********************    10");
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("*********************    10");
            }
            System.out.println("*********************    11");
            filterChain.doFilter(request, response);
        }
    } // This is a filter per pone request
}