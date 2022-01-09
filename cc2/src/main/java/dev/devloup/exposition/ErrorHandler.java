package dev.devloup.exposition;

import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import dev.devloup.core.Logger;
import dev.devloup.shared.domain.exception.EmailAlreadyExist;
import dev.devloup.shared.domain.exception.NegativeMoneyAmount;

public class ErrorHandler {
  private final Logger logger;

  public ErrorHandler(Logger logger) {
    this.logger = logger;
  }

  public RestResponse<String> handleException(NegativeMoneyAmount exception) {
    return ResponseBuilder.create(Status.BAD_REQUEST, exception.getLocalizedMessage()).build();
  }

  public RestResponse<String> handleException(EmailAlreadyExist exception) {
    return ResponseBuilder.create(Status.CONFLICT, exception.getLocalizedMessage()).build();
  }

  public RestResponse<String> handleException(IllegalArgumentException exception) {
    return ResponseBuilder.create(Status.BAD_REQUEST, exception.getLocalizedMessage()).build();
  }

  public RestResponse<String> handleException(Exception exception) {
    logger.log(exception.getMessage());
    return ResponseBuilder.create(Status.INTERNAL_SERVER_ERROR, "Internal error").build();
  }
}
