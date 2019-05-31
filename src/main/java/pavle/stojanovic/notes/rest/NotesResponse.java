package pavle.stojanovic.notes.rest;

import java.util.List;

import pavle.stojanovic.notes.domain.Note;

public class NotesResponse extends RESTResponse {
	
	private List<Note> notes;

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
}
