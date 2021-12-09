package dev.devloup.adapter.in.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dev.devloup.application.port.in.CreateUserCommand;
import dev.devloup.application.port.in.CreateUserUseCase;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationController {
  private final CreateUserUseCase createUserUseCase;

  public RegistrationController(CreateUserUseCase createUserUseCase) {
    this.createUserUseCase = createUserUseCase;
  }

  @POST
  public Response post(CreateUserCommand command) {
    try {
      var event = createUserUseCase.createUser(command);
      return Response.ok(event.getId().toString()).build();
    } catch (Exception e) {
      return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
    }
  }
}