package theroy;

public class Sensible extends State {

	public Sensible(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		this.notes.add(this.scale.get(Degree.VII));
		this.notes.add(this.scale.get(Degree.II));
		this.notes.add(this.scale.get(Degree.IV));
//		this.notes.add(this.scale.get(Degree.VI));
	}
}