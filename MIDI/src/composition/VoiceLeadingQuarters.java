package composition;

import java.util.Collection;
import java.util.Random;

import math.NoteMapper;
import instrument.Instrument;
import theroy.CompositionManager;
import theroy.Note;
import theroy.Stage;
import theroy.StateFactory;
import theroy.Value;
import timing.ProgressionManager;

public class VoiceLeadingQuarters extends BaseVoice {

	public VoiceLeadingQuarters(Instrument instrument, int channelNumber, String id, String name) {
		super(instrument, channelNumber, id, name);
	}
	
	protected int chooseNote() {
		int result = this.chosenNoteNumber;
		
		Random random = new Random();
		
		Stage currentStage = ProgressionManager.getInstance().getCurrentStage();
		Collection<Note> notes = StateFactory.getInstance().getStateByStage(
			currentStage, 
			CompositionManager.getInstance().getKey(), 
			CompositionManager.getInstance().getMode()
		).getNotes();
		
		// Obtengo la nota.
		Note randomNote = 
			notes.toArray(new Note[] {})[random.nextInt(notes.size())];
		
		this.chosenNote = randomNote;
		// Sorteo la octava según las posibilidades del instrumento.
		int randomOctave = this.chosenOctaveNumber;
		
		// Sorteo si cambio la octava.
		if (random.nextBoolean() && random.nextBoolean()) {
			randomOctave = 
				random.nextInt(
					((this.instrument.getTessitura().getMax() - this.instrument.getTessitura().getMin()) / 12) - 1
				);
			
//			this.chosenOctave = (this.instrument.getTessitura().getMin() / 12);
			this.chosenOctave = (this.instrument.getTessitura().getMin() / 12) + randomOctave;
			this.chosenOctaveNumber = randomOctave;
		}
				
		// Calculo el número de nota a partir de la nota y la octava.
		result = NoteMapper.getInstance().getMapping(randomNote, randomOctave, this.instrument.getTessitura().getMin());
		
//		System.out.println(chosenOctave + " " + chosenOctaveNumber + " " + result);
		
		return result;
	}
	
	protected Value chooseValue() {
		return Value.Quarter;
	}
	
	protected boolean rest() {
		return false;
	}
}