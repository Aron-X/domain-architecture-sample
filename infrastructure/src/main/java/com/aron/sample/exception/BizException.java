package com.aron.sample.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BizException extends RuntimeException {

  private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

  private Integer code;

  private String message;

  public BizException(BizError error) {
    this.code = error.getCode();
    this.message = error.getMessage();
  }

  public BizException(HttpStatus httpStatus, BizError error) {
    this.httpStatus = httpStatus;
    this.code = error.getCode();
    this.message = error.getMessage();
  }

  public BizException(HttpStatus httpStatus, BizError error, Object... messageParams) {
    this.httpStatus = httpStatus;
    this.code = error.getCode();
    this.message = String.format(error.getMessage(), messageParams);
  }

  public BizException(BizError error, Object... messageParams) {
    this.code = error.getCode();
    this.message = String.format(error.getMessage(), messageParams);
  }

}
