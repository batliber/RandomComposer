package instrument;

import theroy.Clef;

public class ElectricBass extends Instrument {

	public ElectricBass() {
		// C2 a C6
//		this.tessitura = new Tessitura(24, 72);
		this.tessitura = new Tessitura(36, 68);
		this.clef = Clef.F;
		this.clefLine = 4;
	}
	
	public String toString() {
		return "Electric bass";
	}
}