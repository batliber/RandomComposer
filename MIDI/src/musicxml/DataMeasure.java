package musicxml;

import java.util.Collection;

public class DataMeasure {

	private Long number;
	private DataMeasureAttributes measureAttributes;
	private DataSound tempo;
	private Collection<DataNote> notes;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public DataMeasureAttributes getMeasureAttributes() {
		return measureAttributes;
	}

	public void setMeasureAttributes(DataMeasureAttributes measureAttributes) {
		this.measureAttributes = measureAttributes;
	}

	public DataSound getTempo() {
		return tempo;
	}

	public void setTempo(DataSound tempo) {
		this.tempo = tempo;
	}

	public Collection<DataNote> getNotes() {
		return notes;
	}

	public void setNotes(Collection<DataNote> notes) {
		this.notes = notes;
	}

	public String toString() {
		String result = 
			"\t\t" + "<measure number=\"" + number + "\">" + "\n";
		
		if (measureAttributes != null) {
			result +=
				measureAttributes.toString()
				+ tempo.toString();
		}
		
		DataNote rest = null;
		for (DataNote note : notes) {
			if (note.getNoteType() instanceof DataRest) {
				// Si la nota es un silencio.
				if (rest != null) {
					// Si tengo silencios acumulados.
					
					// Trato de acumular.
					DataNote summedRest =
						ScoreDataFactory.getInstance().buildSummedDataNoteRest(
							rest,
							note
						);
					
					if (summedRest != null) {
						// Si se puede acumular.
						
						// Acumulo.
						rest = summedRest;
					} else {
						// Si no se puede acumular.
						
						// Imprimo lo acumulado anteriormente.
						result +=
							rest.toString();
						
						// Me quedo con la nota.
						rest = note;
					}
				} else {
					// Si no tengo silencios acumulados.
					
					// Me quedo con la nota.
					rest = note;
				}
			} else {
				// Si la nota no es un silencio.
				if (rest != null) {
					// Si tengo silencios acumulados.
					
					// Imprimo lo acumulado.
					result +=
						rest.toString();
					
					// Limpio lo acumulado.
					rest = null;
					
					// Imprimo la nota.
					result +=
						note.toString();
				} else {
					// Si no tengo silencios acumulados.
					
					// Imprimo la nota.
					result +=
						note.toString();
				}
			}
		}
		
		if (rest != null) {
			// Si quedaron silencios acumulados.
			
			// Imprimo lo acumulado.
			result +=
				rest.toString();
		}
		
		result +=
			"\t\t" + "</measure>" + "\n";
		
		return result;
	}
}