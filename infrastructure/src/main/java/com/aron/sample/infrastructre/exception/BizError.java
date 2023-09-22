package com.aron.sample.infrastructre.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BizError {

  // Identity Service,start from 7010001
  INVALID_USER_STATE(7010001, "invalid user state: %s"),
  VERIFY_SIGNATURE_FAILED(7010002, "verify signature failed"),
  VERIFY_NONCE_FAILED(7010003, "verify nonce failed"),
  INVALID_CODE(7010004, "invalid code"),
  ACCOUNT_NOT_FOUND(7010005, "account not found"),
  INVALID_ROLE_NAME(7010006, "invalid role name"),
  AES_KEY_MUST_MATCH(7010007, "aes key length must be 32"),
  GCM_IV_MUST_MATCH(7010008, "gcm iv length must be 12"),
  ATTACHMENT_SIZE_INVALID(7010009, "attachment size: %s is invalid"),
  ATTACHMENT_TYPE_INVALID(7010010, "attachment type is invalid"),
  ATTACHMENT_EMPTY(7010011, "attachment is empty"),
  CSV_MALFORMED_EXCEPTION(7010012, "input file not is a standard csv file"),
  CSV_PROCESSOR_EXCEPTION(7010013, "process csv file failed"),
  DUPLICATED_NETWORK_ID(7010014, "found duplicated network id: %s"),
  TRANSIT_FAILED_EXCEPTION(7010015, "transit failed exception for field: %s"),
  TRANSIT_NOT_SUPPORT_EXCEPTION(7010016, "transit type not support exception"),
  API_NOT_SUPPORT(7010017, "api not support"),
  INVALID_ROLE_ID(7010018, "invalid role id"),

  //server
  SYSTEM_ERROR(7000001, "system internal error"),
  UNAUTHORIZED(7000002, "authentication failed"),
  REST_ERROR(700003, "rest invoke failed with: %s"),
  VALIDATION_FAILED(7000004, "validation failed"),
  PARAMETER_NOT_MATCH(7000005, "parameter not match:"),
  THIRD_PARTY_AUTH_FAILED(7000006, "third party auth failed."),
  THIRD_PARTY_SYSTEM_ERROR(7000007, "third party system error."),
  RESOURCE_ACCESS_ERROR(7000008, "resource access error"),
  FORBIDDEN(7000009, "no permission to access");


  /**
   * there are four parts of code: for example 101001001 prefix(1)+service(01)+module(001)+error code(001).
   */
  private int code;
  private String message;
}
