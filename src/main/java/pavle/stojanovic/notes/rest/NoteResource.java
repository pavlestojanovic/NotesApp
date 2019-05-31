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

import pavle.stojanovic.notes.domain.Note;
import pavle.stojanovic.notes.domain.User;
import pavle.stojanovic.notes.service.AppException;
import pavle.stojanovic.notes.service.NoteService;
import pavle.stojanovic.notes.service.UserService;

@Path("note")
public class NoteResource {
	
	@EJB
	private NoteService noteService;
	@EJB
	private UserService userService;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public NoteResponse createNote(Note note) {
		
		NoteResponse response = new NoteResponse();
		
		try {
			long userId = note.getUserId();
			User user = userService.getUser(userId);
			
			Note n = noteService.createNote(note, user);
			
			response.setNote(n);
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
	public NotesResponse getAll(@QueryParam("note") String noteParam, 
			@QueryParam("like") boolean likeParam) {
		
		NotesResponse response = new NotesResponse();
		
		try {
			List<Note> u = noteService.getAll(noteParam, likeParam);
			
			response.setNotes(u);
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
	public NoteResponse getNote(@PathParam("id") long id) {
		
		NoteResponse response = new NoteResponse();
		
		try {
			Note n = noteService.getNote(id);
			
			response.setNote(n);
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
	public NoteResponse updateNote(@PathParam("id") long id, Note note) {
		
		NoteResponse response = new NoteResponse();
		
		try {
			Note n = noteService.updateNote(id, note);
			
			response.setNote(n);
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
	public RESTResponse deleteNote(@PathParam("id") long id) {
		
		RESTResponse response = new RESTResponse();
		
		try {
			noteService.deleteNote(id);
			
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
