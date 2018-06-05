package theroy;

import java.util.Map;

public class SuperDominantDominant extends State {

	public SuperDominantDominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		Map<Degree, Note> superDominantScale = ScaleFactory.getInstance().getScale(this.scale.get(Degree.VI), mode);
		
		this.notes.add(superDominantScale.get(Degree.V));
		this.notes.add(superDominantScale.get(Degree.VII));
		this.notes.add(superDominantScale.get(Degree.II));
	}
}