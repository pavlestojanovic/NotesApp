package pavle.stojanovic.notes.rest;

import java.util.List;

import pavle.stojanovic.notes.domain.Tag;

public class TagsResponse extends RESTResponse {
	
	private List<Tag> tags;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
