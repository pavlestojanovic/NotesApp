package pavle.stojanovic.notes.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pavle.stojanovic.notes.rest.ErrorMessage;
import pavle.stojanovic.notes.service.AppException;

public class NoteQueries {
	
	public static boolean titleExists(EntityManager em, String title) {
		
		String q = "select n from Note n where n.title = :title";
		
		TypedQuery<Note> query = em.createQuery(q, Note.class);
		query.setParameter("title", title);
		
		List<Note> notes = query.getResultList();
		
		if(notes.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static List<Note> getAll(EntityManager em, String note, boolean like) {
		
		if(like == true && note == null) {
			throw new AppException(ErrorMessage.invalid_query_string);
		}
		
		String q = "Select n from Note n";
		
		if(note != null && like == false) {
			q +=" where n.title = :title";
		} else if(note != null && like == true) {
			q +=" where n.title like concat('%', :title, '%')";
		}
		
		TypedQuery<Note> query = em.createQuery(q, Note.class);
		
		if(note != null) {
			query.setParameter("title", note);
		}
		
		return query.getResultList();
	}

	public static Note getNote(EntityManager em, long id) {
		
		return em.find(Note.class, id);
	}
	
	public static Note findByTitle(EntityManager em, String title) {
		
		String q = "select n from Note n where n.title = :title";
		
		TypedQuery<Note> query = em.createQuery(q, Note.class);
		query.setParameter("title", title);
		
		List<Note> n = query.getResultList();
		
		if(n.isEmpty()) return null;
		
		return n.get(0);
	}

}
