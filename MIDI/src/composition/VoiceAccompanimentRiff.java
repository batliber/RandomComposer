package composition;

import instrument.Instrument;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import math.NoteMapper;
import math.ValueMapper;
import theroy.CompositionManager;
import theroy.Note;
import theroy.StateFactory;
import theroy.Value;
import timing.ProgressionManager;

public class VoiceAccompanimentRiff extends BaseVoice {

	private Queue<Value> values = new LinkedList<Value>();
	private Queue<Note> notes = new LinkedList<Note>();
	private Queue<Integer> noteNumbers = new LinkedList<Integer>();
	
	public VoiceAccompanimentRiff(Instrument instrument, int channelNumber, String id, String name) {
		super(instrument, channelNumber, id, name);
		
		long riffDurationLeft = 
			(ValueMapper.getInstance().getMapping(Value.Whole) * CompositionManager.getInstance().getNumerador()) / 
				CompositionManager.getInstance().getDenominador();
		
		boolean half = false;
		boolean quarterWithPoint = false;
		boolean quarter = false;
		do {
			Value value = chooseRiffValue();
			
			if (!value.equals(Value.Whole)
				&& (!value.equals(Value.Half) || !half)
				&& (!value.equals(Value.QuarterWithPoint) || (!quarterWithPoint && !quarter))
				&& (!value.equals(Value.Quarter) || (!quarter && !quarterWithPoint))) {
				if (ValueMapper.getInstance().getMapping(value) > riffDurationLeft) {
					value = Value.Sixteenth;
				}
				
				riffDurationLeft -= ValueMapper.getInstance().getMapping(value);
				
				values.add(value);
				
				if (value.equals(Value.Half)) {
					half = true;
				}
				
				if (value.equals(Value.Quarter)) {
					quarter = true;
				}
				
//				int note = this.chooseRiffNote();
//				
//				notes.add(this.chosenNote);
//				noteNumbers.add(note);
			}
		} while (riffDurationLeft > 0);
	}
	
	protected boolean rest() {
		return false;
	}
	
	protected Value chooseValue() {
		Value result = values.poll();
		
		values.add(result);
		
		return result;
	}
	
	private Value chooseRiffValue() {
		Value result = this.chosenValue;
		
		Random random = new Random();
		
		// Sorteo el valor de la nota.
		int randomValueIndex = random.nextInt(Value.values().length);
		randomValueIndex = Math.min(Math.max(randomValueIndex, 3), 8);
//		randomValueIndex = 3;

		Value randomValue = Value.values()[randomValueIndex];
		
		result = randomValue;
		
		return result;
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
	
//	private int chooseRiffNote() {
//		int result = this.chosenNoteNumber;
//		
//		Random random = new Random();
//		
//		// Obtengo las notas que puedo elegir según el estado en el que estoy.
//		Collection<Note> notes = 
//			StateFactory.getInstance().getStateByStage(
//				TimeManager.getInstance().getCurrentStage(),
//				CompositionManager.getInstance().getKey(),
//				CompositionManager.getInstance().getMode()
//			).getNotes();
//		
//		// Sorteo un índice de nota de las posibles dentro del estado actual.
//		int randomNoteIndex = random.nextInt(notes.size());
//		
//		// Obtengo la nota a partir del índice.
//		Note randomNote = notes.toArray(new Note[] {})[randomNoteIndex];
//		this.chosenNote = randomNote;
//		
//		// Sorteo la octava según las posibilidades del instrumento.
//		int randomOctave = 
//			random.nextInt(
//				(this.instrument.getTessitura().getMax() - this.instrument.getTessitura().getMin()) / 12
//			);
//		
//		this.chosenOctave = (this.instrument.getTessitura().getMin() / 12);
//		
//		// Calculo el número de nota a partir de la nota y la octava.
//		result = NoteMapper.getInstance().getMapping(randomNote, randomOctave, this.instrument.getTessitura().getMin());
//		
//		return result;
//	}
	
	protected int chooseNoteFromRiff() {
		int result = this.chosenNoteNumber;
		
		Random random = new Random();
		
		this.chosenNote = notes.poll();
		notes.add(this.chosenNote);
		
		this.chosenNoteNumber = noteNumbers.poll();
		noteNumbers.add(this.chosenNoteNumber);
		
		// Sorteo la octava según las posibilidades del instrumento.
		int randomOctave = 
			random.nextInt(
				(this.instrument.getTessitura().getMax() - this.instrument.getTessitura().getMin()) / 12
			);
		
		this.chosenOctave = (this.instrument.getTessitura().getMin() / 12);
		
		// Calculo el número de nota a partir de la nota y la octava.
		result = NoteMapper.getInstance().getMapping(this.chosenNote, randomOctave, this.instrument.getTessitura().getMin());
		
		return result;
	}
}