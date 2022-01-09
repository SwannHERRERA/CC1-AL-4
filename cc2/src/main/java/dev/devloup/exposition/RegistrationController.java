package dev.devloup.exposition;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
  public Response register(UserRequest userRequest) {
    var command = new CreateUserCommand(
        userRequest.firstName,
        userRequest.lastName,
        userRequest.email,
        userRequest.age,
        0,
        userRequest.abilities,
        userRequest.profession,
        userRequest.longitude,
        userRequest.latitude,
        userRequest.activityRadius,
        userRequest.dailyRate);
    var event = createUserUseCase.createUser(command);
    var response = new CreateUserResponse(event.getUser().getId(), event.getUUID());
    return Response.ok(response).build();
  }

}