package musicxml;

public class DataClef {

	private String sign;
	private Integer line;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public String toString() {
		String result =
			"\t\t\t\t" + "<clef>" + "\n"
				+ "\t\t\t\t\t" + "<sign>" + sign + "</sign>" + "\n"
				+ "\t\t\t\t\t" + "<line>" + line + "</line>" + "\n"
			+ "\t\t\t\t" + "</clef>" + "\n";
		
		return result;
	}
}