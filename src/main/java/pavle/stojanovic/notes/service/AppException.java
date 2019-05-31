package pavle.stojanovic.notes.service;

import javax.ejb.ApplicationException;

import pavle.stojanovic.notes.rest.ErrorMessage;

@ApplicationException(rollback = true)
public class AppException extends RuntimeException {
	
	private ErrorMessage error;
	
	public AppException(ErrorMessage error) {
		this.error = error;
	}

	public ErrorMessage getError() {
		return error;
	}

	public void setError(ErrorMessage error) {
		this.error = error;
	}
	
	private static final long serialVersionUID = 1L;

}
