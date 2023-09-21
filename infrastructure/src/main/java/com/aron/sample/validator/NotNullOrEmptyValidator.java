package com.aron.sample.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

import static java.util.Objects.isNull;

public class NotNullOrEmptyValidator implements ConstraintValidator<NotNullOrEmpty, Object> {

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {

    if (isNull(value)) {
      return false;
    }

    Class clazz = value.getClass();

    if (String.class.isAssignableFrom(clazz)) {
      return StringUtils.isNotBlank(String.valueOf(value));
    }

    if (Collection.class.isAssignableFrom(clazz)) {
      return !((Collection) value).isEmpty();
    }

    if (clazz.isArray()) {
      return ((Object[]) value).length != 0;
    }
    return true;
  }
}
