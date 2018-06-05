package theroy;

public class SuperTonic extends State {

	public SuperTonic(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		this.notes.add(this.scale.get(Degree.II));
		this.notes.add(this.scale.get(Degree.IV));
		this.notes.add(this.scale.get(Degree.VI));
//		this.notes.add(this.scale.get(Degree.I));
	}
}