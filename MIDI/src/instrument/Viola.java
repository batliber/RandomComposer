package instrument;

import theroy.Clef;

public class Viola extends Instrument {

	public Viola() {
		// C3 a A6
//		this.tessitura = new Tessitura(36, 81);
		this.tessitura = new Tessitura(36, 72);
		this.clef = Clef.C;
		this.clefLine = 3;
	}

	public String toString() {
		return "Viola";
	}
}