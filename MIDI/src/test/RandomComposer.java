package test;

import instrument.Violoncello;
import instrument.Instrument;
import instrument.Piano;
import instrument.Viola;
import instrument.Violin;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import math.NoteMapper;
import math.ValueMapper;
import media.MediaManager;
import theroy.Mode;
import theroy.Note;
import theroy.Stage;
import theroy.StateFactory;
import theroy.Value;
import timing.ProgressionManager;

public class RandomComposer {

	private static final int __BPM = 120;
//	private static final int __NUMERADOR = 4;
	private static final int __DENOMINADOR = 4;
	
	private Note key = Note.C;
	private Mode mode = Mode.Ionian;
	
	private Map<Value, Long> valueMappings;
	
	private MediaManager mediaManager = MediaManager.getInstance();
	private ProgressionManager timeManager = ProgressionManager.getInstance();
	private NoteMapper noteMapper = NoteMapper.getInstance();
	
	private Collection<Voice> voices = new LinkedList<Voice>();
	
	public RandomComposer() {
		System.out.println("Inicializando mapa de valores según tempo: " + __BPM + " BPM.");
		ValueMapper valueMapper = ValueMapper.getInstance();
		valueMapper.setBpm(__BPM);
		
		this.valueMappings = valueMapper.getMappings();
		
		System.out.println("Inicializando voces:");
		
		System.out.println("    - Inicializando piano.");
		Instrument instrument = new Piano();
		Voice voiceThread = new Voice(instrument, 0);
		voices.add(voiceThread);
		
		System.out.println("    - Inicializando violín.");
		instrument = new Violin();
		voiceThread = new Voice(instrument, 1);
		voices.add(voiceThread);
		
		System.out.println("    - Inicializando viola.");
		instrument = new Viola();
		voiceThread = new Voice(instrument, 2);
		voices.add(voiceThread);
		
		System.out.println("    - Inicializando cello.");
		instrument = new Violoncello();
		voiceThread = new Voice(instrument, 3);
		voices.add(voiceThread);
		
		Metronome metronomeThread = new Metronome(9);
		
		// Iniciar metrónomo
		metronomeThread.start();
	}
	
	public static void main(String[] args) {
		new RandomComposer();
	}
	
	private class Voice extends Thread implements Runnable {
		private static final int __VELOCITY = 10;
		
		private Instrument instrument;
		private int channelNumber;
		
		private int chosenNoteNumber = 0;
		private long chosenValueDuration = 0;
		
		public Voice(Instrument instrument, int channelNumber) {
			this.instrument = instrument;
			this.channelNumber = channelNumber;
		}
		
		public void run() {
			mediaManager.allNotesOff(this.channelNumber);
			
			this.chosenNoteNumber = this.instrument.getTessitura().getMin();
			
			chosenValueDuration = this.chooseValue();
			
			boolean silence = this.silence();
			if (!silence) {
				this.chosenNoteNumber = this.chooseNote();
				
//				System.out.println(this.instrument  + " -> " + randomNote + " " + randomOctave + " " + randomValue);
				
				// Toco la nota.
				mediaManager.noteOn(chosenNoteNumber, __VELOCITY, this.channelNumber);
			}
			
			ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

			executor.schedule(this, chosenValueDuration, TimeUnit.MILLISECONDS);
		}
		
		private boolean silence() {
			boolean result = false;
			
//			Random random = new Random();
			
//			result = random.nextBoolean() && random.nextBoolean();
			result = false;
		
			return result;
		}
		
		private long chooseValue() {
			long result = this.chosenValueDuration;
			
			Random random = new Random();
			
			// Sorteo si cambio el valor
			if (random.nextBoolean()) {
				// Sorteo el valor de la nota.
				int randomValueIndex = random.nextInt(Value.values().length);
				randomValueIndex = Math.min(Math.max(randomValueIndex, 2), 3);
//				randomValueIndex = 2;
	
				Value randomValue = Value.values()[randomValueIndex];
				
				// Obtengo la duración en función del valor sorteado.
				result = valueMappings.get(randomValue);
			}
			
			return result;
		}
		
		private int chooseNote() {
			int result = this.chosenNoteNumber;
			
			Random random = new Random();
			
			// Obtengo las notas que puedo elegir según el estado en el que estoy.
			Collection<Note> notes = 
				StateFactory.getInstance().getStateByStage(
					timeManager.getCurrentStage(),
					key,
					mode
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
//			randomOctave = 3;
			
			// Calculo el número de nota a partir de la nota y la octava.
			result = noteMapper.getMapping(randomNote, randomOctave, this.instrument.getTessitura().getMin());
			
			return result;
		}
	}
	
	private class Metronome extends Thread implements Runnable {
//		private static final int __VELOCITY = 100;
		private static final int __MEASURES_PER_STAGE = 2;
//		private final Note __MODULATION_TO = Note.A;
		
//		private Instrument tick = new Tick();
		private int channelNumber;
		
		private Long mappedValue = valueMappings.get(Value.Quarter);
		
		private int beat = 0;
		private int measures = 0;
		private boolean modulacion = false;
		private int introBeats = 8;
		
		public Metronome(int channelNumber) {
			this.channelNumber = channelNumber;
		}
		
		public void run() {
			mediaManager.allNotesOff(this.channelNumber);
			
//			int noteNumber = tick.getTessitura().getMin();
			
			if (introBeats > 0) {
				introBeats--;
			}
			
			if (introBeats == 0) {
				for (Voice voice : voices) {
					voice.start();
				}
				introBeats--;
			}
			
			if (introBeats < 0) {
				if (this.beat == 0) {
					// Si estoy en el primer beat del compás.
//					noteNumber = tick.getTessitura().getMin();
					
					if (this.modulacion) {
						Random random = new Random();
						
						key = Note.values()[random.nextInt(Note.values().length)];
						mode = Mode.values()[random.nextInt(Mode.values().length)];
						
						System.out.println("== Modulación a " + key + " " + mode + " ==");
						
						this.modulacion = false;
					}
					
					if (this.measures == 0) {
						// Si estoy en el primer compás del stage.
						timeManager.goNextStage();
						
						System.out.println(timeManager.getCurrentStage());
						
						if (timeManager.getCurrentStage() == Stage.SuperTonic) {
							this.modulacion = true;
						}
					}
				} else {
					// En el resto de los beats.
//					noteNumber = tick.getTessitura().getMax();
				}
				
				this.beat = (this.beat + 1) % __DENOMINADOR;
				this.measures = (this.measures + 1) % __MEASURES_PER_STAGE;
			}
			
//			mediaManager.noteOn(noteNumber, __VELOCITY, this.channelNumber);
			
			ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

			executor.schedule(this, mappedValue, TimeUnit.MILLISECONDS);
		}
	}
}