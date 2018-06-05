package musicxml;

public class DataBeam {

	private DataBeamType beamType;
	private Integer beamNumber;
	
	public DataBeamType getBeamType() {
		return beamType;
	}
	
	public void setBeamType(DataBeamType beamType) {
		this.beamType = beamType;
	}
	
	public Integer getBeamNumber() {
		return beamNumber;
	}
	
	public void setBeamNumber(Integer beamNumber) {
		this.beamNumber = beamNumber;
	}

	public String toString() {
		String result = null;
		
		switch (beamType) {
		case Begin:
			result =
				"\t\t\t\t" + "<beam number=\"" + beamNumber + "\">" + "begin" + "</beam>" + "\n";
			
			break;
		case Continue:
			result =
				"\t\t\t\t" + "<beam number=\"" + beamNumber + "\">" + "continue" + "</beam>" + "\n";
			
			break;
		case End:
			result =
				"\t\t\t\t" + "<beam number=\"" + beamNumber + "\">" + "end" + "</beam>" + "\n";
			
			break;
		}
		
		return result;
	}
}