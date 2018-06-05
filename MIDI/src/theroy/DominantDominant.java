package theroy;

import java.util.Map;

public class DominantDominant extends State {

	public DominantDominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		Map<Degree, Note> dominantScale = ScaleFactory.getInstance().getScale(this.scale.get(Degree.V), mode);
		
		this.notes.add(dominantScale.get(Degree.V));
		this.notes.add(dominantScale.get(Degree.VII));
		this.notes.add(dominantScale.get(Degree.II));
	}
}