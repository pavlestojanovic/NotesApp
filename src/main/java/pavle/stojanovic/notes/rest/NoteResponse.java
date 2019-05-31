package pavle.stojanovic.notes.rest;

import javax.xml.bind.annotation.XmlRootElement;

import pavle.stojanovic.notes.domain.Note;

@XmlRootElement
public class NoteResponse extends RESTResponse {
	
	private Note note;

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
}
