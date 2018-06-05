package musicxml;

import java.util.Stack;

public class DataPart {

	private String id;
	private String name;
	private Stack<DataMeasure> measures;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stack<DataMeasure> getMeasures() {
		return measures;
	}

	public void setMeasures(Stack<DataMeasure> measures) {
		this.measures = measures;
	}

	public String toString() {
		String result = 
			"\t" + "<part id=\"" + id + "\">" + "\n";
		
		for (DataMeasure measure : measures) {
			result +=
				measure.toString();
		}
		
		result +=
			"\t" + "</part>" + "\n";
		
		return result;
	}
}