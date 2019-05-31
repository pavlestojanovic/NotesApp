package pavle.stojanovic.notes.rest;

import java.util.List;

import pavle.stojanovic.notes.domain.User;

public class UsersResponse extends RESTResponse {
	
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
