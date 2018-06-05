package instrument;

import theroy.Clef;

public class PianoLeadingMedium extends Instrument {

	public PianoLeadingMedium() {
		this.tessitura = new Tessitura(60, 84);
		this.clef = Clef.G;
		this.clefLine = 2;
	}
	
	public String toString() {
		return "Piano leading medium";
	}
}