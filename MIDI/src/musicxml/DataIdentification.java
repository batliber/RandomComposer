package musicxml;

public class DataIdentification {

	private String composer;

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String toString() {
		String result = 
			"\t" + "<identification>" + "\n"
				+ "\t\t" + "<creator type=\"composer\">" + composer + "</creator>" + "\n"
			+ "\t" + "</identification>" + "\n";
		
		return result;
	}
}