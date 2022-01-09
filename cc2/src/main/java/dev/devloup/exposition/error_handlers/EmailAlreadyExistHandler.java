package dev.devloup.exposition.error_handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import dev.devloup.shared.domain.exception.EmailAlreadyExist;

@Provider
public final class EmailAlreadyExistHandler implements ExceptionMapper<EmailAlreadyExist> {

  @Override
  public Response toResponse(EmailAlreadyExist exception) {
    return Response.status(Status.CONFLICT).entity(exception.getLocalizedMessage()).build();
  }

}
