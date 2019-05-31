package pavle.stojanovic.notes.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pavle.stojanovic.notes.rest.ErrorMessage;
import pavle.stojanovic.notes.service.AppException;

public class UserImageQueries {
	
	public static List<UserImage> getAll(EntityManager em, String image, boolean like) {
		
		if(like == true && image == null) {
			throw new AppException(ErrorMessage.invalid_query_string);
		}
		
		String q = "Select i from UserImage i";
		
		if(image != null && like == false) {
			q +=" where i.type = :type";
		} else if(image != null && like == true) {
			q +=" where i.type like concat('%', :type, '%')";
		}
		
		TypedQuery<UserImage> query = em.createQuery(q, UserImage.class);
		
		if(image != null) {
			query.setParameter("type", image);
		}
		
		return query.getResultList();
	}
	
	public static UserImage getImage(EntityManager em, long id) {
		
		return em.find(UserImage.class, id);
	}

}
