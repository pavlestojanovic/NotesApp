package pavle.stojanovic.notes.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pavle.stojanovic.notes.domain.Note;
import pavle.stojanovic.notes.domain.NoteQueries;
import pavle.stojanovic.notes.domain.User;
import pavle.stojanovic.notes.rest.ErrorMessage;

@Stateless
public class NoteService {
	
	@PersistenceContext
	private EntityManager em;
	
	public Note createNote(Note note, User user) {
		
		if(note.getTitle().length() > 50) {
			throw new AppException(ErrorMessage.invalid_title);
		}
		
		if(note.getContent().length() > 500) {
			throw new AppException(ErrorMessage.invalid_content);
		}
		
		if(NoteQueries.titleExists(em, note.getTitle())) {
			throw new AppException(ErrorMessage.title_exists);
		}
		
		em.persist(note);
		
		user = em.merge(user);
		
		note.setUser(user);
		user.getNotes().add(note);
		
		return note;
	}
	
	public List<Note> getAll(String note, boolean like) {
		
		return NoteQueries.getAll(em, note, like);
	}

	public Note getNote (long id) {
		
		Note n = NoteQueries.getNote(em, id);
		
		if(n == null) {
			throw new AppException(ErrorMessage.note_does_not_exists);
		}
		
		return n;
	}
	
	public Note updateNote (long id, Note note) {
		
		if(note.getTitle().length() > 50) {
			throw new AppException(ErrorMessage.invalid_title);
		}
		
		if(note.getContent().length() > 500) {
			throw new AppException(ErrorMessage.invalid_content);
		}
		
		Note n = NoteQueries.getNote(em, id);
		
//		if(n == null) {
//			return createNote(note);
//		}
		
		Note n1 = NoteQueries.findByTitle(em, note.getTitle());
		
		if(n1 != null && !(n1.getId() == n.getId())) {
			throw new AppException(ErrorMessage.title_exists);
		}
		
		n.setTitle(note.getTitle());
		n.setContent(note.getContent());
		
		return n;
	}
	
	public void deleteNote (long id) {
		
		Note n = NoteQueries.getNote(em, id);
		
		if(n == null) {
			throw new AppException(ErrorMessage.note_does_not_exists);
		}
		
		em.remove(n);
	}

}
