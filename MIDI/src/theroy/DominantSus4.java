package theroy;

public class DominantSus4 extends State {

	public DominantSus4(Note key, Mode mode) {
		super(key, mode);
		
		this.notes.clear();
		
		this.notes.add(this.scale.get(Degree.V));
		
		this.notes.add(
			ScaleFactory.getInstance().getScale(
				this.scale.get(Degree.V), 
				mode
			).get(Degree.IV)
		);
		
		this.notes.add(this.scale.get(Degree.II));
		
		this.notes.add(
			ScaleFactory.getInstance().getScale(
				ScaleFactory.getInstance().getScale(
					this.scale.get(Degree.V), 
					mode
				).get(Degree.IV),
				mode
			).get(Degree.IV)
		);
	}
}