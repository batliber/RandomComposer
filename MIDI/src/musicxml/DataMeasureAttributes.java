package musicxml;

public class DataMeasureAttributes {

	private Integer divisions;
	private DataKey key;
	private DataTime time;
	private DataClef clef;

	public Integer getDivisions() {
		return divisions;
	}

	public void setDivisions(Integer divisions) {
		this.divisions = divisions;
	}

	public DataKey getKey() {
		return key;
	}

	public void setKey(DataKey key) {
		this.key = key;
	}

	public DataTime getTime() {
		return time;
	}

	public void setTime(DataTime time) {
		this.time = time;
	}

	public DataClef getClef() {
		return clef;
	}

	public void setClef(DataClef clef) {
		this.clef = clef;
	}

	public String toString() {
		String result = 
			"\t\t\t" + "<attributes>" + "\n"
				+ "\t\t\t\t" + "<divisions>" + divisions + "</divisions>" + "\n"
				+ key.toString()
				+ time.toString()
				+ clef.toString()
			+ "\t\t\t" + "</attributes>" + "\n";
		
		return result;
	}
}