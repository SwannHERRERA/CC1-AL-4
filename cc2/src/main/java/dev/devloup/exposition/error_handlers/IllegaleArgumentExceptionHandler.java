package dev.devloup.exposition.error_handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public final class IllegaleArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {

  @Override
  public Response toResponse(IllegalArgumentException exception) {
    return Response.status(Status.BAD_REQUEST).entity(exception.getLocalizedMessage()).build();
  }

}
