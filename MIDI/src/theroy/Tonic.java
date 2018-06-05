package theroy;

public class Tonic extends State {

	public Tonic(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		this.notes.add(this.scale.get(Degree.I));
		this.notes.add(this.scale.get(Degree.III));
		this.notes.add(this.scale.get(Degree.V));
//		this.notes.add(this.scale.get(Degree.VII));
	}
}