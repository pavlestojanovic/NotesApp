package pavle.stojanovic.notes.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pavle.stojanovic.notes.domain.Note;
import pavle.stojanovic.notes.domain.Tag;
import pavle.stojanovic.notes.domain.TagQueries;
import pavle.stojanovic.notes.rest.ErrorMessage;

@Stateless
public class TagService {
	
	@PersistenceContext
	private EntityManager em;
	
	public Tag createTag (Tag tag, Note note) {
		
		if(tag.getValue().length() > 20) {
			throw new AppException(ErrorMessage.invalid_tag_value);
		}
		
		if(TagQueries.tagExists(em, tag.getValue())) {
			List<Tag> t = TagQueries.getAll(em, tag.getValue(), true);
			tag = t.get(0);
		} else {
			em.persist(tag); 
		}
		
		note = em.merge(note);
		
		tag.getNotes().add(note);
		note.getTags().add(tag);
		
		return tag;
	}
	
	public List<Tag> getAll(String tag, boolean like) {
		
		return TagQueries.getAll(em, tag, like);
	}

	public Tag getTag(long id) {
		
		Tag t = TagQueries.getTag(em, id);
		
		if(t == null) {
			throw new AppException(ErrorMessage.tag_does_not_exists);
		}
		
		return t;
	}

	public Tag updateTag(long id, Tag tag) {
		
		if(tag.getValue().length() > 20) {
			throw new AppException(ErrorMessage.invalid_tag_value);
		}
		
		Tag t = TagQueries.getTag(em, id);
		
//		if(t == null) {
//			return createTag(tag);
//		}
		
		t.setValue(tag.getValue());
		
		return t;
	}
	
	public void deleteTag(long id) {
		
		Tag t = TagQueries.getTag(em, id);
		
		if(t == null) {
			throw new AppException(ErrorMessage.tag_does_not_exists);
		}
		
		em.remove(t);
	}
	
}
