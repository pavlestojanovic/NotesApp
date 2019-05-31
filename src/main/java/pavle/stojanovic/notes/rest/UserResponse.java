package pavle.stojanovic.notes.rest;

import javax.xml.bind.annotation.XmlRootElement;

import pavle.stojanovic.notes.domain.User;

@XmlRootElement
public class UserResponse extends RESTResponse {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
