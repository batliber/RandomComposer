package musicxml;

public class DataRest extends DataNoteType {

	private String measure;

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String toString() {
		String result = 
			"\t\t\t\t" + "<rest measure=\"yes\"/>" + "\n";
		
		return result;
	}
}