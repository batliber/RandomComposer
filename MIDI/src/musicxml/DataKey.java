package musicxml;

public class DataKey {

	private Integer fifths;
	private DataMode mode;

	public Integer getFifths() {
		return fifths;
	}

	public void setFifths(Integer fifths) {
		this.fifths = fifths;
	}	

	public DataMode getMode() {
		return mode;
	}

	public void setMode(DataMode mode) {
		this.mode = mode;
	}

	public String toString() {
		String result = 
			"\t\t\t\t" + "<key>" + "\n"
				+ "\t\t\t\t\t" + "<fifths>" + fifths + "</fifths>" + "\n";
		
		switch (mode) {
		case Major:
			result +=
				"\t\t\t\t\t" + "<mode>" + "major" + "</mode>" + "\n";
			
			break;
		case Minor:
			result +=
				"\t\t\t\t\t" + "<mode>" + "minor" + "</mode>" + "\n";
			
			break;
		case Diminished:
			result +=
				"\t\t\t\t\t" + "<mode>" + "diminished" + "</mode>" + "\n";
			
			break;
		case Aeolian:
			result +=
				"\t\t\t\t\t" + "<mode>" + "aeolian" + "</mode>" + "\n";
			
			break;
		case Dorian:
			result +=
				"\t\t\t\t\t" + "<mode>" + "dorian" + "</mode>" + "\n";
			
			break;
		case Ionian:
			result +=
				"\t\t\t\t\t" + "<mode>" + "ionian" + "</mode>" + "\n";
			
			break;
		case Locrian:
			result +=
				"\t\t\t\t\t" + "<mode>" + "locrian" + "</mode>" + "\n";
			
			break;
		case Lydian:
			result +=
				"\t\t\t\t\t" + "<mode>" + "lydian" + "</mode>" + "\n";
			
			break;
		case Mixolydian:
			result +=
				"\t\t\t\t\t" + "<mode>" + "mixolydian" + "</mode>" + "\n";
			
			break;
		case Phrygian:
			result +=
				"\t\t\t\t\t" + "<mode>" + "phrygian" + "</mode>" + "\n";
			
			break;
		case None:
			result +=
				"\t\t\t\t\t" + "<mode>" + "none" + "</mode>" + "\n";
			
			break;
		}
			
		result +=
			"\t\t\t\t" + "</key>" + "\n";
		
		return result;
	}
}