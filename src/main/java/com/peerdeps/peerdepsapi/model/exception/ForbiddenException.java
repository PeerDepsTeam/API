package com.peerdeps.peerdepsapi.model.exception;

public class ForbiddenException extends ApiException{
  public ForbiddenException(String message) {
    super(ExceptionType.CLIENT_EXCEPTION, message);
  }
}
