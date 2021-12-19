package dev.devloup.exposition;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.devloup.use_case.register.exposition.GetUserByIdQuery;
import dev.devloup.use_case.register.exposition.ListAllUserQuery;
import dev.devloup.use_case.register.exposition.ListUserUseCase;
import dev.devloup.use_case.register.exposition.UserDTO;

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
  public UserDTO getUser(GetUserByIdQuery query) {
    return listUserUseCase.get(query);
  }
}
