package pavle.stojanovic.notes.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import pavle.stojanovic.notes.domain.User;
import pavle.stojanovic.notes.service.AppException;
import pavle.stojanovic.notes.service.UserService;

@Path("user")
public class UserResource {
	
	@EJB
	private UserService userService;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public UserResponse createUser(User user) {
		
		UserResponse response = new UserResponse();
		
		try {
			User u = userService.createUser(user);
			
			response.setUser(u);
			response.setErrorCode(ErrorMessage.ok);
			return response;
			
		} catch(PersistenceException pe) {
			
			response.setErrorCode(ErrorMessage.db_problem);
			return response;
			
		} catch(AppException ae) {
			
			 response.setErrorCode(ae.getError());
			 return response;
		}	
	}
	
	@GET
	@Produces("application/json")
	public UsersResponse getAll(@QueryParam("user") String userParam, 
			@QueryParam("like") boolean likeParam) {
		
		UsersResponse response = new UsersResponse();
		
		try {
			List<User> u = userService.getAll(userParam, likeParam);
			
			response.setUsers(u);
			response.setErrorCode(ErrorMessage.ok);
			return response;
			
		} catch(PersistenceException pe) {
			
			response.setErrorCode(ErrorMessage.db_problem);
			return response;
			
		} catch(AppException ae) {
			
			response.setErrorCode(ae.getError());
			return response; 
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public UserResponse getUser(@PathParam("id") long id) {
		
		UserResponse response = new UserResponse();
		
		try {
			User u = userService.getUser(id);
			
			response.setUser(u);
			response.setErrorCode(ErrorMessage.ok);
			return response;
			
		} catch(PersistenceException pe) {
			
			response.setErrorCode(ErrorMessage.db_problem);
			return response;
			
		} catch(AppException ae) {
			
			response.setErrorCode(ae.getError());
			return response; 
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public UserResponse updateUser(@PathParam("id") long id, User user) {
		
		UserResponse response = new UserResponse();
		
		try {
			User u = userService.updateUser(id, user);
			
			response.setUser(u);
			response.setErrorCode(ErrorMessage.ok);
			return response;
			
		} catch(PersistenceException pe) {
			
			response.setErrorCode(ErrorMessage.db_problem);
			return response;
			
		} catch(AppException ae) {
			
			response.setErrorCode(ae.getError());
			return response; 
		}	
	}
	
	@DELETE
	@Path("/{id}")
	public RESTResponse deleteUser(@PathParam("id") long id) {
		
		RESTResponse response = new RESTResponse();
		
		try {
			userService.deleteUser(id);
			
			response.setErrorCode(ErrorMessage.ok);
			return response;
			
		} catch(PersistenceException pe) {
			
			response.setErrorCode(ErrorMessage.db_problem);
			return response;
			
		} catch(AppException ae) {
			
			response.setErrorCode(ae.getError());
			return response; 
		}	
	}

}
