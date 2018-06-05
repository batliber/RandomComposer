package instrument;

import theroy.Clef;

public abstract class Instrument {

	protected Tessitura tessitura;
	protected Clef clef;
	protected Integer clefLine;
	
	public Tessitura getTessitura() {
		return this.tessitura;
	}

	public Clef getClef() {
		return clef;
	}

	public Integer getClefLine() {
		return clefLine;
	}
}