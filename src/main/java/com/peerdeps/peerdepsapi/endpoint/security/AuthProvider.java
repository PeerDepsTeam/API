package com.peerdeps.peerdepsapi.endpoint.security;

import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.model.exception.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {
  private static final String BEARER_PREFIX = "Bearer ";

  private static Principal getPrincipal() {
    SecurityContext context = SecurityContextHolder.getContext();
    Object principal = context.getAuthentication().getPrincipal();
    return ((Principal) principal);
  }

  public static String getBearer(HttpServletRequest req) {
    String authorization = req.getHeader("Authorization");
    if (authorization != null && authorization.startsWith(BEARER_PREFIX)) {
      return authorization.substring(7);
    }
    throw new ForbiddenException("Access denied");
  }

  public static User getUser() {
    return getPrincipal().getUser();
  }

  public static String getBearer() {
    return getPrincipal().getBearer();
  }

  @Override
  protected void additionalAuthenticationChecks(
      UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {}

  @Override
  protected UserDetails retrieveUser(
      String username, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    User user = (User) authentication.getPrincipal();
    String token = (String) authentication.getCredentials();
    return new Principal(token, user);
  }
}
