package pavle.stojanovic.notes.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pavle.stojanovic.notes.rest.ErrorMessage;
import pavle.stojanovic.notes.service.AppException;

public class UserQueries {
	
	public static boolean nicknameExists(EntityManager em, String nickname) {
		
		String q = "select u from User u where u.nickname = :nickname";
		
		TypedQuery<User> query = em.createQuery(q, User.class);
		query.setParameter("nickname", nickname);
		
		List<User> users = query.getResultList();
		
		if(users.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean usernameExists(EntityManager em, String username) {
		
		String q = "select u from User u where u.username = :username";
		
		TypedQuery<User> query = em.createQuery(q, User.class);
		query.setParameter("username", username);
		
		List<User> users = query.getResultList();
		
		if(users.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static List<User> getAll(EntityManager em, String user, boolean like) {
		
		if(like == true && user == null) {
			throw new AppException(ErrorMessage.invalid_query_string);
		}
		
		String q = "Select u from User u";
		
		if(user != null && like == false) {
			q +=" where u.nickname = :nickname or u.username = :username";
		} else if(user != null && like == true) {
			q +=" where u.nickname like concat('%', :nickname, '%') or u.username like concat('%', :username, '%')";
		}
		
		TypedQuery<User> query = em.createQuery(q, User.class);
		
		if(user != null) {
			query.setParameter("nickname", user).setParameter("username", user);
		}
		
		return query.getResultList();
	}
	
	public static User getUser(EntityManager em, long id) {
		
		return em.find(User.class, id);
	}

	public static User findByNickname(EntityManager em, String nickname) {
		
		String q = "select u from User u where u.nickname = :nickname";
		
		TypedQuery<User> query = em.createQuery(q, User.class);
		query.setParameter("nickname", nickname);
		
		List<User> u = query.getResultList();
		
		if(u.isEmpty()) return null;
		
		return u.get(0);
	}

	public static User findByUsername(EntityManager em, String username) {
		
		String q = "select u from User u where u.username = :username";
		
		TypedQuery<User> query = em.createQuery(q, User.class);
		query.setParameter("username", username);
		
		List<User> u = query.getResultList();
		
		if(u.isEmpty()) return null;
		
		return u.get(0);
	}
	
}
