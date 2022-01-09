package dev.devloup.exposition.error_handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import dev.devloup.shared.domain.exception.NegativeMoneyAmount;

@Provider
public final class NegativeMoneyAmountHandler implements ExceptionMapper<NegativeMoneyAmount> {

  @Override
  public Response toResponse(NegativeMoneyAmount exception) {
    return Response.status(Status.BAD_REQUEST).entity(exception.getLocalizedMessage()).build();
  }
}
