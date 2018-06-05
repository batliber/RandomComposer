package instrument;

import theroy.Clef;

public class Guitar extends Instrument {

	public Guitar() {
		// E4 a E8
//		this.tessitura = new Tessitura(52, 90);
		this.tessitura = new Tessitura(60, 78);
		this.clef = Clef.G;
		this.clefLine = 2;
	}

	public String toString() {
		return "Guitar";
	}
}