package dev.devloup.exposition;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dev.devloup.shared.exposition.UserResponse;
import dev.devloup.use_case.register.exposition.GetUserByIdQuery;
import dev.devloup.use_case.register.exposition.ListAllUserQuery;
import dev.devloup.use_case.register.exposition.ListUserUseCase;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminUserController {
  private final ListUserUseCase listUserUseCase;
  private final ErrorHandler errorHandler;

  public AdminUserController(ListUserUseCase listUserUseCase, ErrorHandler errorHandler) {
    this.listUserUseCase = listUserUseCase;
    this.errorHandler = errorHandler;
  }

  @GET
  public List<UserResponse> getAllUsers() {
    try {
      ListAllUserQuery query = new ListAllUserQuery();
      return listUserUseCase.listAll(query);
    } catch (Exception e) {
      return errorHandler.handleException(e);
    }
  }

  @GET
  @Path("/{id}")
  public UserResponse getUser(@PathParam("id") GetUserByIdQuery id) {
    try {
      return listUserUseCase.get(id);
    } catch (Exception e) {
      return errorHandler.handleException(e);
    }
  }
}
