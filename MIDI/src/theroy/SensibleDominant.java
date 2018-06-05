package theroy;

import java.util.Map;

public class SensibleDominant extends State {

	public SensibleDominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		Map<Degree, Note> sensibleScale = ScaleFactory.getInstance().getScale(this.scale.get(Degree.VII), mode);
		
		this.notes.add(sensibleScale.get(Degree.V));
		this.notes.add(sensibleScale.get(Degree.VII));
		this.notes.add(sensibleScale.get(Degree.II));
	}
}