package instrument;

import theroy.Clef;

public class Violin extends Instrument {

	public Violin() {
		// C3 a A7
//		this.tessitura = new Tessitura(36, 93);
		this.tessitura = new Tessitura(48, 93);
		this.clef = Clef.G;
		this.clefLine = 2;
	}
	
	public String toString() {
		return "Violin";
	}
}