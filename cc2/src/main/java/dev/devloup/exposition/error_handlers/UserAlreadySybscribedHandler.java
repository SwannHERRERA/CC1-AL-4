package dev.devloup.exposition.error_handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import dev.devloup.use_case.transaction.exposition.UserAlreadySubscribed;

@Provider
public final class UserAlreadySybscribedHandler implements ExceptionMapper<UserAlreadySubscribed> {

  @Override
  public Response toResponse(UserAlreadySubscribed exception) {
    return Response.status(Status.BAD_REQUEST).entity(exception.getLocalizedMessage()).build();
  }

}
