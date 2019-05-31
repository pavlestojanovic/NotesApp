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
import pavle.stojanovic.notes.domain.Tag;
import pavle.stojanovic.notes.service.AppException;
import pavle.stojanovic.notes.service.NoteService;
import pavle.stojanovic.notes.service.TagService;

@Path("tag")
public class TagResource {
	
	@EJB
	private TagService tagService;
	@EJB
	private NoteService noteService;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public TagResponse createTag (Tag tag) {
		
		TagResponse response = new TagResponse();
		
		try {
			long noteId = tag.getNoteId();
			Note note = noteService.getNote(noteId);
			
			Tag t = tagService.createTag(tag, note);
			
			response.setTag(t);
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
	public TagsResponse getAll(@QueryParam("tag") String tagParam, 
			@QueryParam("like") boolean likeParam) {
		
		TagsResponse response = new TagsResponse();
		
		try {
			List<Tag> t = tagService.getAll(tagParam, likeParam);
			
			response.setTags(t);
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
	public TagResponse getTag(@PathParam("id") long id) {
		
		TagResponse response = new TagResponse();
		
		try {
			Tag t = tagService.getTag(id);
			
			response.setTag(t);
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
	public TagResponse updateTag(@PathParam("id") long id, Tag tag) {
		
		TagResponse response = new TagResponse();
		
		try {
			Tag t = tagService.updateTag(id, tag);
			
			response.setTag(t);
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
	public RESTResponse deleteTag(@PathParam("id") long id) {
		
		RESTResponse response = new RESTResponse();
		
		try {
			tagService.deleteTag(id);
			
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
