package musicxml;

public class DataNote {

	private DataNoteType noteType;
	private Integer duration;
	private String type;
	private Boolean dot;
	private DataBeam beam;

	public DataNoteType getNoteType() {
		return noteType;
	}

	public void setNoteType(DataNoteType noteType) {
		this.noteType = noteType;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getDot() {
		return dot;
	}

	public void setDot(Boolean dot) {
		this.dot = dot;
	}

	public DataBeam getBeam() {
		return beam;
	}

	public void setBeam(DataBeam beam) {
		this.beam = beam;
	}

	public String toString() {
		String result =
			"\t\t\t" + "<note>" + "\n"
				+ noteType.toString()
				+ "\t\t\t\t" + "<duration>" + duration + "</duration>" + "\n"
				+ "\t\t\t\t" + "<type>" + type + "</type>" + "\n";
		
		if (dot) {
			result +=
				"\t\t\t\t" + "<dot/>" + "\n";
		}
		
		if (beam != null) {
			result +=
				beam.toString();
		}
		
		result +=
			"\t\t\t" + "</note>" + "\n";
		
		return result;
	}
}