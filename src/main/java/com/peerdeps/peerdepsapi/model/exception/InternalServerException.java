package com.peerdeps.peerdepsapi.model.exception;

public class InternalServerException extends ApiException{
  public InternalServerException(String message) {
    super(ExceptionType.CLIENT_EXCEPTION, message);
  }
}
