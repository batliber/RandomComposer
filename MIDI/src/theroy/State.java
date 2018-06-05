package theroy;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class State {

	protected Collection<Note> notes = new LinkedList<Note>();
	protected Map<Degree, Note> scale = new HashMap<Degree, Note>();
	
	public State(Note key, Mode mode) {
		this.scale = ScaleFactory.getInstance().getScale(key, mode);
	}
	
	public Collection<Note> getNotes() {
		return this.notes;
	}
}