package musicxml;

public class DataPitch extends DataNoteType {

	private String step;
	private Integer alter;
	private Integer octave;

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public Integer getAlter() {
		return alter;
	}

	public void setAlter(Integer alter) {
		this.alter = alter;
	}

	public Integer getOctave() {
		return octave;
	}

	public void setOctave(Integer octave) {
		this.octave = octave;
	}

	public String toString() {
		String result = 
			"\t\t\t\t" + "<pitch>" + "\n"
				+ "\t\t\t\t\t" + "<step>" + step + "</step>" + "\n";
		
		if (!alter.equals(0)) {
			result +=
				"\t\t\t\t\t" + "<alter>" + alter + "</alter>" + "\n";
		}
		
		result +=
				"\t\t\t\t\t" + "<octave>" + octave + "</octave>" + "\n"
			+ "\t\t\t\t" + "</pitch>" + "\n";
		
		return result;
	}
}