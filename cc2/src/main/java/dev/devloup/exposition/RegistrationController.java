package dev.devloup.exposition;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dev.devloup.use_case.register.exposition.CreateUserCommand;
import dev.devloup.use_case.register.exposition.CreateUserResponse;
import dev.devloup.use_case.register.exposition.CreateUserUseCase;
import dev.devloup.use_case.register.exposition.UserRequest;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationController {
  private final CreateUserUseCase createUserUseCase;

  public RegistrationController(CreateUserUseCase createUserUseCase) {
    this.createUserUseCase = createUserUseCase;
  }

  @POST
  public Response post(UserRequest userRequest) {
    try {
      var command = new CreateUserCommand(
          userRequest.firstName,
          userRequest.lastName,
          userRequest.email,
          userRequest.age,
          0);
      var event = createUserUseCase.createUser(command);
      var response = new CreateUserResponse(event.getUser().getId(), event.getUUID());
      return Response.ok(response).build();
    } catch (Exception e) {
      // TODO catch many type of error somtime render 500 / 400 email already exist
      return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
    }
  }
}