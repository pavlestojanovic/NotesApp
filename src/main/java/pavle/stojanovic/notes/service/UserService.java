package pavle.stojanovic.notes.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pavle.stojanovic.notes.domain.User;
import pavle.stojanovic.notes.domain.UserQueries;
import pavle.stojanovic.notes.rest.ErrorMessage;

@Stateless
public class UserService {
	
	@PersistenceContext
	private EntityManager em;
	
	public User createUser(User user) {
		
		if(user.getNickname().length() > 50) {
			throw new AppException(ErrorMessage.invalid_nickname);
		}
		
		if(user.getUsername().length() > 50) {
			throw new AppException(ErrorMessage.invalid_username);
		}
		
		if(UserQueries.nicknameExists(em, user.getNickname())) {
			throw new AppException(ErrorMessage.nickname_exists);
		}
		
		if(UserQueries.usernameExists(em, user.getUsername())) {
			throw new AppException(ErrorMessage.username_exists);
		}
		
		em.persist(user);
		
		return user;
	}
	
	public List<User> getAll(String user, boolean like) {
		
		return UserQueries.getAll(em, user, like);
	}
	
	public User getUser(long id) {
		
		User u = UserQueries.getUser(em, id);
		
		if(u == null) {
			throw new AppException(ErrorMessage.user_does_not_exists);
		}
		
		return u;
	}
	
	public User updateUser(long id, User user) {
		
		if(user.getNickname().length() > 50) {
			throw new AppException(ErrorMessage.invalid_nickname);
		}
		
		if(user.getUsername().length() > 50) {
			throw new AppException(ErrorMessage.invalid_username);
		}
		
		User u = UserQueries.getUser(em, id);
		
		if(u == null) {
			return createUser(user);
		}
		
		User u1 = UserQueries.findByNickname(em, user.getNickname());
		
		if(u1 != null && !(u1.getId() == u.getId())) {
			throw new AppException(ErrorMessage.nickname_exists);
		}
		
		u.setNickname(user.getNickname());

		User u2 = UserQueries.findByUsername(em, user.getUsername());
		
		if(u2 != null && !(u2.getId() == u.getId())) {
			throw new AppException(ErrorMessage.username_exists);
		}
		
		u.setUsername(user.getUsername());
		
		return u;
	}

	public void deleteUser(long id) {
		
		User u = UserQueries.getUser(em, id);
		
		if(u == null) {
			throw new AppException(ErrorMessage.user_does_not_exists);
		}
		
		em.remove(u);
	}
	
}
