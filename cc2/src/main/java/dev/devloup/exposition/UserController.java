package dev.devloup.exposition;

import java.util.Objects;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.UserId;
import dev.devloup.use_case.addMoney.exposition.AddMoneyCommand;
import dev.devloup.use_case.addMoney.exposition.AddMoneyCommandHandler;
import dev.devloup.use_case.addMoney.exposition.AddMoneyRequest;
import dev.devloup.use_case.addMoney.exposition.AddMoneyResponse;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserController {
  private final AddMoneyCommandHandler addMoneyCommandHandler;
  private final ErrorHandler errorHandler;

  public UserController(AddMoneyCommandHandler addMoneyCommandHandler, ErrorHandler errorHandler) {
    this.addMoneyCommandHandler = Objects.requireNonNull(addMoneyCommandHandler);
    this.errorHandler = errorHandler;
  }

  @Path("/add-money")
  public AddMoneyResponse addMoney(AddMoneyRequest request) {
    try {
      var command = new AddMoneyCommand(Money.of(request.amount), UserId.of(UUID.fromString(request.userId)));
      return addMoneyCommandHandler.addMoney(command);
    } catch (Exception e) {
      errorHandler.handleException(e);
    }
  }
}
