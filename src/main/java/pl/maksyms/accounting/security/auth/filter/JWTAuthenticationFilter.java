package pl.maksyms.accounting.security.auth.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.maksyms.accounting.security.SecurityConstans.*;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String headerValue = request.getHeader(AUTH_HEADER);
        if (headerValue == null || !headerValue.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        } else {
            UsernamePasswordAuthenticationToken token = getAuthenticationToken(request, response);
            SecurityContextHolder.getContext().setAuthentication(token);
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
        String headerValue = request.getHeader(AUTH_HEADER);
        String username = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(headerValue.replace(TOKEN_PREFIX, "").trim())
                .getBody()
                .getSubject();
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (username!=null) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } else {
            return null;
        }
    }
}
