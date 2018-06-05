package instrument;

import theroy.Clef;

public class DoubleBass extends Instrument {

	public DoubleBass() {
		// C1 a G4
//		this.tessitura = new Tessitura(12, 55);
		this.tessitura = new Tessitura(24, 48);
		this.clef = Clef.F;
		this.clefLine = 4;
	}

	public String toString() {
		return "Contrabass";
	}
}