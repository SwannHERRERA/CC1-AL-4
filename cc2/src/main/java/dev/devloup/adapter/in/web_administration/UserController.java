package dev.devloup.adapter.in.web_administration;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.devloup.application.port.in.GetUserQuery;
import dev.devloup.application.port.in.ListAllUserQuery;
import dev.devloup.application.port.in.ListUserUseCase;
import dev.devloup.application.port.in.UserDTO;

@Path("/admin/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
  private final ListUserUseCase listUserUseCase;

  public UserController(ListUserUseCase listUserUseCase) {
    this.listUserUseCase = listUserUseCase;
  }

  @GET
  public List<UserDTO> getAllUsers(ListAllUserQuery query) {
    return listUserUseCase.listAll(query);
  }

  @GET
  public UserDTO getUser(GetUserQuery query) {
    return listUserUseCase.get(query);
  }
}
