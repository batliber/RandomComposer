package composition;

import instrument.Instrument;

import java.util.Collection;
import java.util.Random;

import math.NoteMapper;
import theroy.CompositionManager;
import theroy.Note;
import theroy.StateFactory;
import timing.ProgressionManager;

public class Voice extends BaseVoice {
	
 	public Voice(Instrument instrument, int channelNumber, String id, String name) {
		super(instrument, channelNumber, id, name);
	}
	
	protected int chooseNote() {
		int result = this.chosenNoteNumber;
		
		Random random = new Random();
		
		// Obtengo las notas que puedo elegir según el estado en el que estoy.
		Collection<Note> notes = 
			StateFactory.getInstance().getStateByStage(
				ProgressionManager.getInstance().getCurrentStage(),
				CompositionManager.getInstance().getKey(),
				CompositionManager.getInstance().getMode()
			).getNotes();
		
		// Sorteo un índice de nota de las posibles dentro del estado actual.
		int randomNoteIndex = random.nextInt(notes.size());
		
		// Obtengo la nota a partir del índice.
		Note randomNote = notes.toArray(new Note[] {})[randomNoteIndex];
		
		// Sorteo la octava según las posibilidades del instrumento.
		int randomOctave = 
			random.nextInt(
				(this.instrument.getTessitura().getMax() - this.instrument.getTessitura().getMin()) / 12
			);
		
		// Calculo el número de nota a partir de la nota y la octava.
		result = NoteMapper.getInstance().getMapping(randomNote, randomOctave, this.instrument.getTessitura().getMin());
		
		return result;
	}
}