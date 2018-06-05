package theroy;

import java.util.Map;

public class MediantDominant extends State {

	public MediantDominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		Map<Degree, Note> mediantScale = ScaleFactory.getInstance().getScale(this.scale.get(Degree.III), mode);
		
		this.notes.add(mediantScale.get(Degree.V));
		this.notes.add(mediantScale.get(Degree.VII));
		this.notes.add(mediantScale.get(Degree.II));
	}
}