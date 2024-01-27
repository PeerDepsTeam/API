package com.peerdeps.peerdepsapi.model.exception;

public class InternalServerException extends ApiException{
  public InternalServerException(String message) {
    super(ExceptionType.SERVER_EXCEPTION, message);
  }
}
