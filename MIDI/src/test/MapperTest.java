package test;

import instrument.Violoncello;
import instrument.Instrument;
import instrument.Viola;
import instrument.Violin;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import media.MediaManager;
import theroy.Value;
import timing.ProgressionManager;

public class MapperTest {

	private Map<Value, Long> valueMappings;
	
	private MediaManager mediaManager = MediaManager.getInstance();
	private ProgressionManager timeManager = ProgressionManager.getInstance();
	
	private Collection<Voice> voices = new LinkedList<Voice>();
	
	public MapperTest() {
//		ValueMapper valueMapper = new ValueMapper(120);
		
//		this.valueMappings = valueMapper.getMappings();
		
		int voiceCount = 2;
		for (int i=0; i < voiceCount; i++) {
			Instrument instrument = new Violin();
//			State state = StateFactory.getInstance().getStateByStage(this.timeManager.getCurrentStage());
//			state.setInstrument(instrument);
//			instrument.setState(state);
			
			Voice voiceThread = new Voice(instrument, i);
			
			voices.add(voiceThread);
			
			voiceThread.start();
		}
		
		Instrument instrument = new Violin();
//		State state = StateFactory.getInstance().getStateByStage(this.timeManager.getCurrentStage());
//		state.setInstrument(instrument);
//		instrument.setState(state);
		
		Voice voiceThread = new Voice(instrument, 0);
		
		voices.add(voiceThread);
		
		voiceThread.start();
		
		instrument = new Viola();
//		state = StateFactory.getInstance().getStateByStage(this.timeManager.getCurrentStage());
//		state.setInstrument(instrument);
//		instrument.setState(state);
		
		voiceThread = new Voice(instrument, 1);
		
		voices.add(voiceThread);
		
		voiceThread.start();
		
		instrument = new Violoncello();
//		state = StateFactory.getInstance().getStateByStage(this.timeManager.getCurrentStage());
//		state.setInstrument(instrument);
//		instrument.setState(state);
		
		voiceThread = new Voice(instrument, 2);
		
		voices.add(voiceThread);
		
		voiceThread.start();
		
		Percussion percussionThread = new Percussion();
		
		percussionThread.start();
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Voice voice : voices) {
			voice.interrupt();
		}
		
//		percussionThread.interrupt();
	}
	
	public static void main(String[] args) {
		new MapperTest();
	}

	private class Voice extends Thread {
//		private Instrument instrument;
		private LinkedList<Integer> noteMappings;
		private int channelNumber;
		
		public Voice(Instrument instrument, int channelNumber) {
//			this.instrument = instrument;
			this.channelNumber = channelNumber;
			
//			this.noteMappings = this.instrument.getNoteMapper().getMappings();
		}
		
		public void updateInstrumentState() {
//			theroy.State state = StateFactory.getInstance().getStateByStage(timeManager.getCurrentStage());
//			state.setInstrument(this.instrument);
//			this.instrument.setState(state);
//			this.noteMappings = this.instrument.getNoteMapper().getMappings();
		}
		
		public void run() {
			Random random = new Random();
			
			boolean randomSilence = false;
			int randomNoteIndex = 0;
			int randomNoteNumber = 0;
			int randomValueIndex = 0;
			long randomValueDuration = 0;
			
			do {
				randomSilence = false; //random.nextBoolean();
				randomNoteIndex = 
					random.nextInt(this.noteMappings.size() / voices.size()) 
						+ this.noteMappings.size() * this.channelNumber / voices.size();
//				randomNoteIndex = random.nextInt(this.noteMappings.size());
				randomNoteNumber = this.noteMappings.get(randomNoteIndex);
				
//				System.out.print(this.instrument.getNoteMapper().getReverseMapping(randomNoteNumber) + "" + this.channelNumber + " - ");
				
				randomValueIndex = random.nextInt(Value.values().length - 6) + 1;
				randomValueDuration = valueMappings.get(Value.values()[randomValueIndex]);
				
				if (!randomSilence) {
					mediaManager.noteOn(randomNoteNumber, 10, this.channelNumber);
				}
				
				try {
					Thread.sleep(randomValueDuration);
					
					if (!randomSilence) {
						mediaManager.noteOff(randomNoteNumber, this.channelNumber);
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			} while (true);
			
			if (randomSilence) {
				mediaManager.noteOff(randomNoteNumber, this.channelNumber);
			}
		}
	}
	
	private class Percussion extends Thread {
		private int time = 0;
		private int measures = 0;
		
		public void run() {
			int noteNumber = 75;
			do {
				mediaManager.noteOn(noteNumber, 100, 9);
				
				try {
					Thread.sleep(valueMappings.get(Value.Quarter));
					
					mediaManager.noteOff(noteNumber, 9);
					
					this.time = (this.time + 1) % 3;
					if (this.time == 0) {
						System.out.println();
						
						this.measures = (this.measures + 1) % 2;
						
						if (this.measures == 0) {
							timeManager.goNextStage();
							
							for (Voice voice : voices) {
								voice.updateInstrumentState();
							}
						}
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			} while (true);
			
			mediaManager.noteOff(noteNumber, 9);
		}
	}
}