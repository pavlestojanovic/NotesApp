package pavle.stojanovic.notes.rest;

import java.util.List;

import pavle.stojanovic.notes.domain.UserImage;

public class UserImagesResponse extends RESTResponse {
	
	private List<UserImage> images;

	public List<UserImage> getImages() {
		return images;
	}

	public void setImages(List<UserImage> images) {
		this.images = images;
	}

}
