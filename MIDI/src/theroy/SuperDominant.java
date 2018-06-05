package theroy;

public class SuperDominant extends State {

	public SuperDominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		this.notes.add(this.scale.get(Degree.VI));
		this.notes.add(this.scale.get(Degree.I));
		this.notes.add(this.scale.get(Degree.III));
//		this.notes.add(this.scale.get(Degree.V));
	}
}