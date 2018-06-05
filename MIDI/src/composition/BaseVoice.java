package composition;

import instrument.Instrument;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import math.ValueMapper;
import media.MediaManager;
import musicxml.DataNote;
import musicxml.ScoreDataFactory;
import musicxml.ScoreManager;
import playback.PlaybackEventData;
import playback.PlaybackEventListener;
import playback.PlaybackManager;
import theroy.CompositionManager;
import theroy.Mode;
import theroy.Note;
import theroy.Stage;
import theroy.Value;
import timing.ProgressionManager;

public abstract class BaseVoice extends Thread {

	protected static final int __VELOCITY = 10;
	
	protected Instrument instrument;
	protected int channelNumber;
	protected String voiceId;
	protected String voiceName;
	
	protected int chosenNoteNumber = 0;
	protected Note chosenNote = Note.C;
	protected int chosenOctaveNumber = 0;
	protected int chosenOctave = 0;
	protected Value chosenValue = Value.Quarter;
	protected long durationLeft = 
		(ValueMapper.getInstance().getMapping(Value.Whole) * CompositionManager.getInstance().getNumerador()) / 
			CompositionManager.getInstance().getDenominador();
	protected long measureNumber = 1;
	
	protected Note currentKey = CompositionManager.getInstance().getKey();
	protected Mode currentMode = CompositionManager.getInstance().getMode();
	
	protected boolean removed = false;

	protected BaseVoice(Instrument instrument, int channelNumber, String id, String name) {
		this.instrument = instrument;
		this.channelNumber = channelNumber;
		this.voiceId = id;
		this.voiceName = name;
		
		this.chosenOctave = this.instrument.getTessitura().getMin() / 12;
		this.chosenOctaveNumber = this.instrument.getTessitura().getMin() / 12;
		
		PlaybackManager.getInstance().addPlaybackStartedEventListener(new PEL(this));
	}
	
	public void run() {
		try {
			if (!removed) {
				MediaManager.getInstance().allNotesOff(this.channelNumber);
				
				if (ProgressionManager.getInstance().getCurrentStage() != Stage.Suspended) {
					this.chosenNoteNumber = this.chooseNote();
					this.chosenValue = this.chooseValue();
					
					this.durationLeft -= ValueMapper.getInstance().getMapping(this.chosenValue);
								
					boolean rest = this.rest();
					
					DataNote note = null;
					if (rest) {
						note = 
							ScoreDataFactory.getInstance().buildDataNoteRest(
								this.chosenValue
							);
					} else {
						note = 
							ScoreDataFactory.getInstance().buildDataNote(
								this.chosenValue, 
								this.chosenNote,
								this.chosenOctave
							);
					}
					
					ScoreManager.getInstance().addNoteToPart(this.voiceId, note);
					
					if (this.durationLeft == 0) {
						this.measureNumber++;
						this.durationLeft = 
							(ValueMapper.getInstance().getMapping(Value.Whole) * CompositionManager.getInstance().getNumerador()) / 
								CompositionManager.getInstance().getDenominador();
						
						if (!CompositionManager.getInstance().getKey().equals(currentKey)
							|| !CompositionManager.getInstance().getMode().equals(currentMode)) {
							this.currentKey = CompositionManager.getInstance().getKey();
							this.currentMode = CompositionManager.getInstance().getMode();
							
							ScoreManager.getInstance().addMeasureToPart(
								this.voiceId,
								ScoreDataFactory.getInstance().buildFullDataMeasure(
									measureNumber, 
									4, 
									this.instrument.getClefLine(), 
									this.instrument.getClef(), 
									CompositionManager.getInstance().getKey(),
									CompositionManager.getInstance().getMode(),
									CompositionManager.getInstance().getNumerador(), 
									CompositionManager.getInstance().getDenominador(), 
									CompositionManager.getInstance().getBpm()
								)
							);
						} else {
							ScoreManager.getInstance().addMeasureToPart(
								this.voiceId,
								ScoreDataFactory.getInstance().buildSimpleDataMeasure(
									measureNumber
								)
							);
						}
					}
					
					if (!rest) {
						// Toco la nota.
						MediaManager.getInstance().noteOn(this.chosenNoteNumber, __VELOCITY, this.channelNumber);
					}
					
					ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
			
					executor.schedule(this, ValueMapper.getInstance().getMapping(this.chosenValue), TimeUnit.MILLISECONDS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected boolean rest() {
		boolean result = false;
		
		Random random = new Random();
		
		result = random.nextBoolean() && random.nextBoolean() && random.nextBoolean();
		
		return result;
	}
	
	protected Value chooseValue() {
		Value result = this.chosenValue;
		
		Random random = new Random();
		
		// Sorteo si cambio el valor
		if (random.nextBoolean()) {
			// Sorteo el valor de la nota.
			int randomValueIndex = random.nextInt(Value.values().length);
			randomValueIndex = Math.min(Math.max(randomValueIndex, 3), 7);
//			randomValueIndex = 3;

			Value randomValue = Value.values()[randomValueIndex];
			
			result = randomValue;
		}
		
		if (ValueMapper.getInstance().getMapping(result) > this.durationLeft) {
			result = Value.Sixteenth;
		}
		
		return result;
	}
	
	protected abstract int chooseNote();
	
	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public String getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(String id) {
		this.voiceId = id;
	}

	public String getVoiceName() {
		return voiceName;
	}

	public void setVoiceName(String name) {
		this.voiceName = name;
	}

	private class PEL implements PlaybackEventListener {
		private BaseVoice voice;
		
		public PEL(BaseVoice voice) {
			this.voice = voice;
		}
		
		public void playbackStarted(PlaybackEventData playbackEventData) {
			ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
			executor.schedule(this.voice, ValueMapper.getInstance().getMapping(Value.Half), TimeUnit.MILLISECONDS);
		}

		public void playbackStopped(PlaybackEventData playbackEventData) {
			durationLeft = 
				(ValueMapper.getInstance().getMapping(Value.Whole) * CompositionManager.getInstance().getNumerador()) / 
					CompositionManager.getInstance().getDenominador();
			measureNumber = 1;
		}
	
		public void voiceRemoved(PlaybackEventData playbackEventData) {
			if (voice.getVoiceId().equals(playbackEventData.getVoiceId())) {
				removed = true;
			}
		}
	}
}