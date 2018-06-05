package theroy;

public class Dominant extends State {

	public Dominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		this.notes.add(this.scale.get(Degree.V));
		this.notes.add(this.scale.get(Degree.VII));
		this.notes.add(this.scale.get(Degree.II));
	}
}