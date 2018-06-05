package theroy;

public class DominantMinorSeventh extends State {

	public DominantMinorSeventh(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		this.notes.add(this.scale.get(Degree.V));
		this.notes.add(this.scale.get(Degree.VII));
		this.notes.add(this.scale.get(Degree.II));
		this.notes.add(this.scale.get(Degree.IV));
	}
}