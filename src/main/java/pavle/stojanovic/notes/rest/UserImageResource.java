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
import pavle.stojanovic.notes.domain.UserImage;
import pavle.stojanovic.notes.service.AppException;
import pavle.stojanovic.notes.service.UserImageService;
import pavle.stojanovic.notes.service.UserService;

@Path("image")
public class UserImageResource {
	
	@EJB
	private UserImageService imageService;
	@EJB
	private UserService userService;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public UserImageResponse createImage(UserImage image) {
		
		UserImageResponse response = new UserImageResponse();
		
		try {
			long userId = image.getUserId();
			User user = userService.getUser(userId);
			
			UserImage i = imageService.createImage(image, user);
			
			response.setImage(i);
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
	public UserImagesResponse getAll(@QueryParam("image") String imageParam, 
			@QueryParam("like") boolean likeParam) {
		
		UserImagesResponse response = new UserImagesResponse();
		
		try {
			List<UserImage> t = imageService.getAll(imageParam, likeParam);
			
			response.setImages(t);
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
	public UserImageResponse getImage(@PathParam("id") long id) {
		
		UserImageResponse response = new UserImageResponse();
		
		try {
			UserImage t = imageService.getImage(id);
			
			response.setImage(t);
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
	public UserImageResponse updateImage(@PathParam("id") long id, UserImage image) {
		
		UserImageResponse response = new UserImageResponse();
		
		try {
			UserImage t = imageService.updateImage(id, image);
			
			response.setImage(t);
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
	public RESTResponse deleteImage(@PathParam("id") long id) {
		
		RESTResponse response = new RESTResponse();
		
		try {
			imageService.deleteImage(id);
			
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
