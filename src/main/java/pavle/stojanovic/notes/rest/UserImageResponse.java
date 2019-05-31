package pavle.stojanovic.notes.rest;

import javax.xml.bind.annotation.XmlRootElement;

import pavle.stojanovic.notes.domain.UserImage;

@XmlRootElement
public class UserImageResponse extends RESTResponse {
	
	private UserImage image;

	public UserImage getImage() {
		return image;
	}

	public void setImage(UserImage image) {
		this.image = image;
	}

}
