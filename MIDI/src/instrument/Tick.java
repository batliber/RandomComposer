package instrument;

import theroy.Clef;

public class Tick extends Instrument {

	public Tick() {
		this.tessitura = new Tessitura(76, 77);
		this.clef = Clef.G;
		this.clefLine = 2;
	}
}