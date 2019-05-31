package pavle.stojanovic.notes.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pavle.stojanovic.notes.domain.User;
import pavle.stojanovic.notes.domain.UserImage;
import pavle.stojanovic.notes.domain.UserImageQueries;
import pavle.stojanovic.notes.rest.ErrorMessage;
import pavle.stojanovic.notes.utils.ImageFormatValidator;

@Stateless
public class UserImageService {
	
	@PersistenceContext
	private EntityManager em;
	
	public UserImage createImage(UserImage image, User user)  {
		
		if(!ImageFormatValidator.validate(image.getType())) {
			throw new AppException(ErrorMessage.invalid_image_type);
		}
		
		em.persist(image);
		
		user = em.merge(user);
		
		image.setUser(user);
		user.setImage(image);
		
		return image;
	}
	
	public List<UserImage> getAll(String image, boolean like) {
		
		return UserImageQueries.getAll(em, image, like);
	}
	
	public UserImage getImage(long id) {
		
		UserImage i = UserImageQueries.getImage(em, id);
		
		if(i == null) {
			throw new AppException(ErrorMessage.image_does_not_exists);
		}
		
		return i;
	}

	public UserImage updateImage(long id, UserImage image) {
		
		if(!ImageFormatValidator.validate(image.getType())) {
			throw new AppException(ErrorMessage.invalid_image_type);
		}
		
		UserImage i = UserImageQueries.getImage(em, id);
		
//		if(i == null) {
//			return createImage(image);
//		}
		
		i.setType(image.getType());
		i.setContent(image.getContent());
		
		return i;
	}
	
	public void deleteImage(long id) {
		
		UserImage i = UserImageQueries.getImage(em, id);
		
		if(i == null) {
			throw new AppException(ErrorMessage.image_does_not_exists);
		}
		
		em.remove(i);
	}
	
}
