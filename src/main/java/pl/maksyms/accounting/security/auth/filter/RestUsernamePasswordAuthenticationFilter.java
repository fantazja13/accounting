package pl.maksyms.accounting.security.auth.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.maksyms.accounting.security.auth.handler.RestAuthenticationSuccessHandler;
import pl.maksyms.accounting.security.user.AuthUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static pl.maksyms.accounting.security.SecurityConstans.EXPIRATION_TIME;
import static pl.maksyms.accounting.security.SecurityConstans.SECRET;

public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthUser authUser = new ObjectMapper().readValue(request.getInputStream(), AuthUser.class);
            return authManager.authenticate(new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword()));
        } catch (IOException e) {
            // TODO authentication service exception
            throw new RuntimeException("Authentication service error");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ZonedDateTime creationTime = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime expirationTime = creationTime.plus(EXPIRATION_TIME, ChronoUnit.MILLIS);
        AuthUser authUser = (AuthUser) authResult.getPrincipal();
        String token = Jwts.builder()
                .setSubject(authUser.getUsername())
                .setIssuer("ms.accounting")
                .setExpiration(Date.from(expirationTime.toInstant()))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.set("timestamp", mapper.convertValue(creationTime.toString(), JsonNode.class));
        json.set("token", mapper.convertValue(token, JsonNode.class));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(json));
    }

    @Autowired
    public RestUsernamePasswordAuthenticationFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
    }
}
