package theroy;

public class SubDominant extends State {

	public SubDominant(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		this.notes.add(this.scale.get(Degree.IV));
		this.notes.add(this.scale.get(Degree.VI));
		this.notes.add(this.scale.get(Degree.I));
//		this.notes.add(this.scale.get(Degree.III));
	}
}