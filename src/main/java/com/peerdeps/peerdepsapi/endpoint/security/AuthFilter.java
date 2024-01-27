package com.peerdeps.peerdepsapi.endpoint.security;

import com.google.firebase.auth.FirebaseToken;
import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.model.exception.ForbiddenException;
import com.peerdeps.peerdepsapi.service.UserService;
import com.peerdeps.peerdepsapi.service.firebase.FirebaseService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Slf4j
public class AuthFilter extends AbstractAuthenticationProcessingFilter {
  private final FirebaseService firebaseService;
  private final UserService userService;

  protected AuthFilter(RequestMatcher requestMatcher, FirebaseService conf, UserService userSvc) {
    super(requestMatcher);
    firebaseService = conf;
    userService = userSvc;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    String token = AuthProvider.getBearer(request);
    FirebaseToken authUser = firebaseService.getUserByBearer(token);
    if (authUser != null) {
      log.info("Authenticated user {}", authUser.getEmail());
      User user = userService.getUserByFirebaseIdAndEmail(authUser.getUid(), authUser.getEmail());
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(user, token);
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      return getAuthenticationManager().authenticate(authentication);
    }
    throw new ForbiddenException("Access denied");
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {
    super.successfulAuthentication(request, response, chain, authResult);
    chain.doFilter(request, response);
  }
}
