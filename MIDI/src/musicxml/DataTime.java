package musicxml;

public class DataTime {

	private Integer beats;
	private Integer beatType;

	public Integer getBeats() {
		return beats;
	}

	public void setBeats(Integer beats) {
		this.beats = beats;
	}

	public Integer getBeatType() {
		return beatType;
	}

	public void setBeatType(Integer beatType) {
		this.beatType = beatType;
	}

	public String toString() {
		String result =
			"\t\t\t\t" + "<time>" + "\n"
				+ "\t\t\t\t\t" + "<beats>" + beats + "</beats>" + "\n"
				+ "\t\t\t\t\t" + "<beat-type>" + beatType + "</beat-type>" + "\n"
			+ "\t\t\t\t" + "</time>" + "\n";
		
		return result;
	}
}