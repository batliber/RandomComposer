package musicxml;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class DataScore {

	private DataWork work;
	private DataIdentification identification;
	private ConcurrentMap<String, DataPart> parts;

	public DataWork getWork() {
		return work;
	}

	public void setWork(DataWork work) {
		this.work = work;
	}

	public DataIdentification getIdentification() {
		return identification;
	}

	public void setIdentification(DataIdentification identification) {
		this.identification = identification;
	}

	public Map<String, DataPart> getParts() {
		return parts;
	}

	public void setParts(ConcurrentMap<String, DataPart> parts) {
		this.parts = parts;
	}
	
	public String toString() {
		String result = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + "\n"
			+ "<!DOCTYPE score-partwise PUBLIC" + "\n"
				+ "\t" + "\"-//Recordare//DTD MusicXML 3.1 Partwise//EN\"" + "\n"
				+ "\t" + "\"http://www.musicxml.org/dtds/partwise.dtd\">" + "\n"
			+ "<score-partwise version=\"3.1\">" + "\n";
		
		result += 
			work.toString()
			+ identification.toString();
		
		result +=
				"\t" + "<part-list>" + "\n";
		
		List<DataPart> toOrder = new LinkedList<DataPart>();
		toOrder.addAll(parts.values());
		
		Collections.sort(toOrder, new Comparator<DataPart>() {
			public int compare(DataPart arg0, DataPart arg1) {
				return arg0.getId().compareTo(arg1.getId());
			}			
		});
		
		for (DataPart part : toOrder) {
			result +=
					"\t\t" + "<score-part id=\"" + part.getId() + "\">" + "\n"
						+ "\t\t\t" + "<part-name>" + part.getName() + "</part-name>" + "\n"
					+ "\t\t" + "</score-part>" + "\n";
		}
		
		result +=
				"\t" + "</part-list>" + "\n";
		
		for (DataPart part : toOrder) {
			result +=
				part.toString();
		}
		
		result +=
			"</score-partwise>" + "\n";
			
		return result;
	}
}