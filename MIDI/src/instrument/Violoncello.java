package instrument;

import theroy.Clef;

public class Violoncello extends Instrument {

	public Violoncello() {
		// C2 a A5
//		this.tessitura = new Tessitura(24, 69);
		this.tessitura = new Tessitura(36, 69);
		this.clef = Clef.F;
		this.clefLine = 4;
	}

	public String toString() {
		return "Cello";
	}
}