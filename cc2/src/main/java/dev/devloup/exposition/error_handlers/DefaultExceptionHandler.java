package dev.devloup.exposition.error_handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import dev.devloup.core.Logger;

@Provider
public final class DefaultExceptionHandler implements ExceptionMapper<Exception> {
  private final Logger logger;

  public DefaultExceptionHandler(Logger logger) {
    this.logger = logger;
  }

  @Override
  public Response toResponse(Exception exception) {
    logger.log(exception.getMessage());
    return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal error").build();
  }

}
