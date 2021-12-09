package dev.devloup.adapter.in.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dev.devloup.application.port.in.CreateUserCommand;
import dev.devloup.application.service.UserService;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationController {
  private final UserService service;

  public RegistrationController(UserService service) {
    this.service = service;
  }

  @POST
  public Response post(CreateUserCommand command) {
    try {
      var event = service.createUser(command);
      return Response.ok(event.getId().toString()).build();
    } catch (Exception e) {
      return Response.status(Status.BAD_REQUEST).entity(e.toString()).build();
    }
  }
}