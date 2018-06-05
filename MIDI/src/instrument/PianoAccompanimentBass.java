package instrument;

import theroy.Clef;

public class PianoAccompanimentBass extends Instrument {

	public PianoAccompanimentBass() {
		this.tessitura = new Tessitura(24, 36);
		this.clef = Clef.F;
		this.clefLine = 4;
	}
	
	public String toString() {
		return "Piano accompaniment bass";
	}
}