package instrument;

import theroy.Clef;

public class PianoAccompanimentMedium extends Instrument {

	public PianoAccompanimentMedium() {
		this.tessitura = new Tessitura(36, 60);
		this.clef = Clef.F;
		this.clefLine = 4;
	}
	
	public String toString() {
		return "Piano accompaniment medium";
	}
}