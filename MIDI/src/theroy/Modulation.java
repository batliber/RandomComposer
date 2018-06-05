package theroy;

public class Modulation extends State {

	public Modulation(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		this.notes.add(this.scale.get(Degree.V));
		this.notes.add(this.scale.get(Degree.VII));
		this.notes.add(this.scale.get(Degree.II));
		this.notes.add(this.scale.get(Degree.IV));
	}
}