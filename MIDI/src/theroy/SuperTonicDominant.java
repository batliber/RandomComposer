package theroy;

import java.util.Map;

public class SuperTonicDominant extends State {

	public SuperTonicDominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		Map<Degree, Note> superTonicScale = ScaleFactory.getInstance().getScale(this.scale.get(Degree.II), mode);
		
		this.notes.add(superTonicScale.get(Degree.V));
		this.notes.add(superTonicScale.get(Degree.VII));
		this.notes.add(superTonicScale.get(Degree.II));
	}
}