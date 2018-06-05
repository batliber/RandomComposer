package musicxml;

public class DataWork {

	private String workTitle;

	public String getWorkTitle() {
		return workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}	

	public String toString() {
		String result = 
			"\t" + "<work>" + "\n"
				+ "\t\t" + "<work-title>" + workTitle + "</work-title>" + "\n"
			+ "\t" + "</work>" + "\n";
				
		return result;
	}
}