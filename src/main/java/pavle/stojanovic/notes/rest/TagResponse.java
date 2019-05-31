package pavle.stojanovic.notes.rest;

import javax.xml.bind.annotation.XmlRootElement;

import pavle.stojanovic.notes.domain.Tag;

@XmlRootElement
public class TagResponse extends RESTResponse {
	
	private Tag tag;

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
