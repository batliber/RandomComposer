package musicxml;

public class DataSound {

	private Integer tempo;

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public String toString() {
		String result =
			"\t\t\t" + "<sound tempo=\"" + tempo + "\"/>" + "\n";
		
		return result;
	}
}