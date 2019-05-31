package pavle.stojanovic.notes.rest;

public class ErrorMessage {
	
	private int code;
	
	private String message;

	private ErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public static final ErrorMessage ok = new ErrorMessage(0, "Operation successful");
	
	public static final ErrorMessage db_problem = new ErrorMessage(100, "Database error");
	
	public static final ErrorMessage invalid_nickname = new ErrorMessage(101, "The nickname is longer than 50 characters");
	
	public static final ErrorMessage invalid_username = new ErrorMessage(102, "The username is longer than 50 characters");
	
	public static final ErrorMessage nickname_exists = new ErrorMessage(103, "The nickname already exists");
	
	public static final ErrorMessage username_exists = new ErrorMessage(104, "The username already exists");
	
	public static final ErrorMessage invalid_query_string = new ErrorMessage(105, "Query string is not properly formed");
	
	public static final ErrorMessage user_does_not_exists = new ErrorMessage(106, "User with given id does not exist");
	
	public static final ErrorMessage invalid_title = new ErrorMessage(107,"The title is longer than 50 characters");
	
	public static final ErrorMessage invalid_content = new ErrorMessage(108,"The content is longer than 500 characters");
	
	public static final ErrorMessage title_exists = new ErrorMessage(109, "The title already exists");
	
	public static final ErrorMessage note_does_not_exists = new ErrorMessage(110, "Note with given id does not exist");
	
	public static final ErrorMessage invalid_tag_value = new ErrorMessage(111, "Tag is longer than 20 characters");
	
	public static final ErrorMessage tag_does_not_exists = new ErrorMessage(112, "Tag with given id does not exist");
	
	public static final ErrorMessage invalid_image_type = new ErrorMessage(113, "Unsupported image type");
	
	public static final ErrorMessage image_does_not_exists = new ErrorMessage(114, "Image with given id does not exist");
	
}
