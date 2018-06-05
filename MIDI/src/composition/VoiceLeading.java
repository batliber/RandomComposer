package composition;

import instrument.Instrument;

import java.util.Random;

import math.NoteMapper;
import theroy.CompositionManager;
import theroy.Degree;
import theroy.Note;
import theroy.ScaleFactory;

public class VoiceLeading extends BaseVoice {

	private int chosenNoteIndex = 0;
	
	public VoiceLeading(Instrument instrument, int channelNumber, String id, String name) {
		super(instrument, channelNumber, id, name);
	}
	
	protected int chooseNote() {
		int result = this.chosenNoteNumber;
		
		Random random = new Random();
		
		Degree[] degrees = Degree.values();
		
		// Sorteo el largo del paso.
		int randomStep = random.nextInt(2);
		
		// Sorteo la dirección a donde moverme en la escala.
		boolean directionAscending = random.nextBoolean();
		if (directionAscending) {
			// Si se sube la altura.
			this.chosenNoteIndex = Math.min(this.chosenNoteIndex + randomStep + 1, degrees.length - 1);
		} else {
			// Si se baja la altura.
			this.chosenNoteIndex = Math.max(this.chosenNoteIndex - (randomStep + 1), 0);
		}
		
		// Obtengo el grado de la escala sorteado.
		Degree randomDegree = degrees[this.chosenNoteIndex];
		
		// Obtengo la nota.
		Note randomNote = 
			ScaleFactory.getInstance().getScale(
				CompositionManager.getInstance().getKey(), 
				CompositionManager.getInstance().getMode()
			).get(randomDegree);
		this.chosenNote = randomNote;
		
		// Sorteo la octava según las posibilidades del instrumento.
		int randomOctave = 
			random.nextInt(
				(this.instrument.getTessitura().getMax() - this.instrument.getTessitura().getMin()) / 12
			);
		
		this.chosenOctave = (this.instrument.getTessitura().getMin() / 12);
		
		// Calculo el número de nota a partir de la nota y la octava.
		result = NoteMapper.getInstance().getMapping(randomNote, randomOctave, this.instrument.getTessitura().getMin());
		
		return result;
	}
}