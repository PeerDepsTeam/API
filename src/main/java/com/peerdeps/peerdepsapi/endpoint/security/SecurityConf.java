package com.peerdeps.peerdepsapi.endpoint.security;

import com.peerdeps.peerdepsapi.model.exception.ForbiddenException;
import com.peerdeps.peerdepsapi.service.UserService;
import com.peerdeps.peerdepsapi.service.firebase.FirebaseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static org.springframework.http.HttpMethod.OPTIONS;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConf {

  private final HandlerExceptionResolver exceptionResolver;
  private final FirebaseService firebaseService;
  private final UserService userService;
  private final AuthProvider provider;

  public SecurityConf(
      @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver,
      FirebaseService firebase,
      UserService service,
      AuthProvider auth) {
    exceptionResolver = resolver;
    firebaseService = firebase;
    userService = service;
    provider = auth;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.authenticationProvider(provider)
        .addFilterBefore(
            bearerFilter(
                new NegatedRequestMatcher(
                    new OrRequestMatcher(
                        new AntPathRequestMatcher("/ping"),
                        new AntPathRequestMatcher("/signup"),
                        new AntPathRequestMatcher("/users"),
                        new AntPathRequestMatcher("/courses"),
                        new AntPathRequestMatcher("/**", OPTIONS.toString())
                    ))),
            AnonymousAuthenticationFilter.class)
        .anonymous()
        .and()
        .authorizeRequests()
        .requestMatchers(HttpMethod.GET, "/ping").permitAll()
        .requestMatchers(HttpMethod.GET, "/users").permitAll()
        .requestMatchers(HttpMethod.GET, "/users/**").authenticated()
        .requestMatchers(HttpMethod.GET, "/courses").permitAll()
        .requestMatchers(HttpMethod.GET, "/courses/**").authenticated()
        .requestMatchers(HttpMethod.GET, "/signin").authenticated()
        .requestMatchers(HttpMethod.POST, "/signup").permitAll()
        .anyRequest()
        .denyAll()
        .and();
    return http.build();
  }

  private Exception forbiddenWithRemoteInfo(Exception e, HttpServletRequest req) {
    log.info(
        String.format(
            "Access is denied for remote caller: address=%s, host=%s, port=%s",
            req.getRemoteAddr(), req.getRemoteHost(), req.getRemotePort()));
    return new ForbiddenException(e.getMessage());
  }

  private AuthFilter bearerFilter(RequestMatcher requestMatcher) throws Exception {
    AuthFilter bearerFilter = new AuthFilter(requestMatcher, firebaseService, userService);
    bearerFilter.setAuthenticationManager(authenticationManager());
    bearerFilter.setAuthenticationSuccessHandler(
        (httpServletRequest, httpServletResponse, authentication) -> {});
    bearerFilter.setAuthenticationFailureHandler(
        (req, res, e) ->
            exceptionResolver.resolveException(req, res, null, forbiddenWithRemoteInfo(e, req)));
    return bearerFilter;
  }

  protected AuthenticationManager authenticationManager() {
    return new ProviderManager(provider);
  }


}
