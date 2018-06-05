package theroy;

public class TonicDominantSeventh extends State {

	public TonicDominantSeventh(Note key, Mode mode) {
		super(key, mode);

		this.notes.clear();
		this.notes.add(this.scale.get(Degree.I));
		this.notes.add(this.scale.get(Degree.III));
		this.notes.add(this.scale.get(Degree.V));
		this.notes.add(
			ScaleFactory.getInstance().getScale(
				this.scale.get(Degree.IV), 
				mode
			).get(Degree.IV)
		);
	}
}