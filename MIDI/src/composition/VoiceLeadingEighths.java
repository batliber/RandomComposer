package composition;

import instrument.Instrument;

import java.util.Collection;
import java.util.Random;

import math.NoteMapper;
import theroy.CompositionManager;
import theroy.Note;
import theroy.Stage;
import theroy.StateFactory;
import theroy.Value;
import timing.ProgressionManager;

public class VoiceLeadingEighths extends BaseVoice {
	
	public VoiceLeadingEighths(Instrument instrument, int channelNumber, String id, String name) {
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
		
		this.chosenOctave = (this.instrument.getTessitura().getMin() / 12);
		
		// Calculo el número de nota a partir de la nota y la octava.
		result = NoteMapper.getInstance().getMapping(randomNote, 0, this.instrument.getTessitura().getMin());
		
		return result;
	}
	
	protected Value chooseValue() {
		return Value.Eighth;
	}

	protected boolean rest() {
		return false;
	}
}