package com.aron.sample.infrastructre.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
@Slf4j
public class BizExceptionHandler {

  @ExceptionHandler(value = {BizException.class})
  public ResponseEntity<ErrorResponse> bizExceptionHandler(HttpServletRequest req,
      BizException e) {
    log.error(
        "[BizException] exception happened when call url = {}, query string = {}, error code = {}, error message = {}",
        req.getRequestURI(), req.getQueryString(), e.getCode(), e.getMessage(), e);
    return new ResponseEntity<>(error(e.getCode(), e.getMessage()),
        e.getHttpStatus());
  }

  @ExceptionHandler(value = {AccessDeniedException.class})
  public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException e) {
    log.error("[AccessDeniedException] unauthorized error", e);
    return new ResponseEntity<>(
        error(BizError.FORBIDDEN), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorResponse> badRequestHandler(MethodArgumentNotValidException e) {
    log.error("[MethodArgumentNotValidException] Bad request error", e);
    List<ErrorResponse.FieldError> errors =
        e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(this::errorDetail)
            .collect(toList());
    return new ResponseEntity<>(
        error(BizError.VALIDATION_FAILED, errors), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {BindException.class})
  public ResponseEntity<ErrorResponse> badRequestHandler(BindException e) {
    log.error("[BindException] Bad request error", e);
    List<ErrorResponse.FieldError> errors =
        e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(this::errorDetail)
            .collect(toList());
    return new ResponseEntity<>(
        error(BizError.VALIDATION_FAILED, errors), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> badRequestHandler(MethodArgumentTypeMismatchException e) {
    log.error("[MethodArgumentTypeMismatchException] Bad request error", e);

    ErrorResponse.FieldError errorDetail = new ErrorResponse.FieldError(e.getName(), e.getMessage());

    return new ResponseEntity<>(
        error(BizError.VALIDATION_FAILED, Collections.singletonList(errorDetail)),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      ConstraintViolationException.class, MissingServletRequestPartException.class})
  public ResponseEntity<ErrorResponse> badRequestHandler(ConstraintViolationException e) {
    log.error("[ConstraintViolationException, MissingServletRequestPartException] Bad request error", e);
    List<ErrorResponse.FieldError> errors = e.getConstraintViolations().stream()
        .map(constraintViolation ->
            new ErrorResponse.FieldError(
                ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName(),
                constraintViolation.getMessage())
        ).collect(toList());
    return new ResponseEntity<>(
        error(BizError.VALIDATION_FAILED, errors),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {HttpMessageNotReadableException.class})
  public ResponseEntity<ErrorResponse> validationParameterTypeFailedHandler(HttpServletRequest req,
      HttpMessageNotReadableException e) {
    log.error("[HttpMessageNotReadableException] Error happen when call url = {}, query string = {}, error = {}",
        req.getRequestURI(), req.getQueryString(), e.getMessage(), e);
    return new ResponseEntity<>(
        error(BizError.PARAMETER_NOT_MATCH, getDetailMessage(e.getMessage())),
        HttpStatus.BAD_REQUEST);
  }

  private String getDetailMessage(String message) {
    if (Objects.isNull(message)) {
      return null;
    }
    return message.split("; ")[0];
  }


  @ExceptionHandler(value = {RuntimeException.class})
  public ResponseEntity<ErrorResponse> defaultErrorHandler(HttpServletRequest req,
      RuntimeException e) {
    log.error("[RuntimeException] Error happen when call url = {}, query string = {}, error = {}",
        req.getRequestURI(), req.getQueryString(), e.getMessage(), e);

    return new ResponseEntity<>(
        error(BizError.SYSTEM_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {IllegalArgumentException.class, ValidationException.class})
  public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(HttpServletRequest req,
      IllegalArgumentException e) {
    log.error("[IllegalArgumentException] Error happen when call url = {}, query string = {}, error = {}",
        req.getRequestURI(), req.getQueryString(), e.getMessage(), e);

    return new ResponseEntity<>(
        error(BizError.PARAMETER_NOT_MATCH, getDetailMessage(e.getMessage())),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> badRequestHandler(MissingServletRequestParameterException e) {
    log.error("[MissingServletRequestParameterException] Bad request error", e);

    ErrorResponse.FieldError errorDetail = new ErrorResponse.FieldError(e.getParameterName(), e.getMessage());

    return new ResponseEntity<>(
        error(BizError.VALIDATION_FAILED, Collections.singletonList(errorDetail)),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = ResourceAccessException.class)
  public ResponseEntity<ErrorResponse> requestTimeoutHandler(HttpServletRequest req,
      ResourceAccessException e) {
    log.error("[IllegalArgumentException] Error happen when call url = {}, query string = {}, error = {}",
        req.getRequestURI(), req.getQueryString(), e.getMessage(), e);

    return new ResponseEntity<>(
        error(BizError.RESOURCE_ACCESS_ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ErrorResponse error(Integer code, String... message) {
    return ErrorResponse.builder()
        .code(code)
        .message(StringUtils.join(message, "|"))
        .build();
  }

  private ErrorResponse error(BizError bizError) {
    return ErrorResponse.builder()
        .code(bizError.getCode())
        .message(bizError.getMessage())
        .build();
  }

  private ErrorResponse error(BizError bizError, String... message) {
    return ErrorResponse.builder()
        .code(bizError.getCode())
        .message(bizError.getMessage() + StringUtils.join(message, " "))
        .build();
  }

  private ErrorResponse error(BizError bizError, List<ErrorResponse.FieldError> fieldErrors) {
    return ErrorResponse.builder()
        .code(bizError.getCode())
        .message(bizError.getMessage())
        .fieldErrors(fieldErrors)
        .build();
  }

  private ErrorResponse.FieldError errorDetail(
      org.springframework.validation.FieldError fieldError) {
    String message =
        Optional.ofNullable(fieldError)
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .orElse(Strings.EMPTY);
    String field = Optional.ofNullable(fieldError)
        .map(org.springframework.validation.FieldError::getField)
        .orElse(Strings.EMPTY);
    return ErrorResponse.FieldError.builder()
        .field(field)
        .message(message)
        .build();
  }


}
