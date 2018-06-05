package instrument;

import theroy.Clef;

public class Piano extends Instrument {

	public Piano() {
//		this.tessitura = new Tessitura(24, 94);
		this.tessitura = new Tessitura(36, 60);
		this.clef = Clef.F;
		this.clefLine = 4;
	}

	public String toString() {
		return "Piano";
	}
}