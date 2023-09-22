package com.aron.sample.infrastructre.exception;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    return httpResponse.getStatusCode().series() == CLIENT_ERROR
        || httpResponse.getStatusCode().series() == SERVER_ERROR;
  }

  @Override
  public void handleError(ClientHttpResponse httpResponse) throws IOException {
    throw new BizException(httpResponse.getStatusCode(), BizError.REST_ERROR,
        CharStreams.toString(new InputStreamReader(httpResponse.getBody(), Charsets.UTF_8)));
  }
}
