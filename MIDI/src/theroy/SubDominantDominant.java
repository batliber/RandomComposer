package theroy;

import java.util.Map;

public class SubDominantDominant extends State {

	public SubDominantDominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		Map<Degree, Note> subDominantScale = ScaleFactory.getInstance().getScale(this.scale.get(Degree.IV), mode);
		
		this.notes.add(subDominantScale.get(Degree.V));
		this.notes.add(subDominantScale.get(Degree.VII));
		this.notes.add(subDominantScale.get(Degree.II));
	}
}