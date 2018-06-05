package instrument;

import theroy.Clef;

public class PianoAccompanimentHigh extends Instrument {

	public PianoAccompanimentHigh() {
		this.tessitura = new Tessitura(60, 84);
		this.clef = Clef.G;
		this.clefLine = 2;
	}
}