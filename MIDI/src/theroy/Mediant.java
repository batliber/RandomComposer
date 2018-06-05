package theroy;

public class Mediant extends State {

	public Mediant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		this.notes.add(this.scale.get(Degree.III));
		this.notes.add(this.scale.get(Degree.V));
		this.notes.add(this.scale.get(Degree.VII));
//		this.notes.add(this.scale.get(Degree.II));
	}
}