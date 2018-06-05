package playback;

import io.FileOutputManager;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

import musicxml.ScoreDataFactory;
import musicxml.ScoreManager;
import theroy.CompositionManager;
import theroy.Stage;
import timing.FlowManager;
import timing.ProgressionManager;
import composition.BaseVoice;

public class PlaybackManager {

	private static PlaybackManager instance = null;
	
	private ConcurrentMap<String, BaseVoice> voices = new ConcurrentHashMap<String, BaseVoice>();
	
	private Collection<PlaybackEventListener> playbackEventListeners = new LinkedList<PlaybackEventListener>();
	
	private PlaybackManager() {
		
	}
	
	public static PlaybackManager getInstance() {
		if (instance == null) {
			instance = new PlaybackManager();
		}
		return instance;
	}
	
	public void init() {
		FlowManager.getInstance().init();
	}

	public void addPlaybackStartedEventListener(PlaybackEventListener playbackEventListener) {
		this.playbackEventListeners.add(playbackEventListener);
	}
	
	public void notifyPlaybackStarted() {
		for (PlaybackEventListener playbackEventListener : this.playbackEventListeners) {
			playbackEventListener.playbackStarted(new PlaybackEventData());
		}
	}
	
	public void notifyPlaybackStopped() {
		for (PlaybackEventListener playbackEventListener : this.playbackEventListeners) {
			playbackEventListener.playbackStopped(new PlaybackEventData());
		}
	}
	
	public void notifyVoiceRemoved(String voiceId) {
		PlaybackEventData playbackEventData = new PlaybackEventData();
		playbackEventData.setVoiceId(voiceId);
		
		for (PlaybackEventListener playbackEventListener : this.playbackEventListeners) {
			playbackEventListener.voiceRemoved(playbackEventData);
		}
	}
	
	public void addVoice(BaseVoice voice) {
		ScoreManager.getInstance().addPart(
			voice.getVoiceId(), 
			ScoreDataFactory.getInstance().buildDataPart(voice.getVoiceId(), voice.getVoiceName())
		);
		
		ScoreManager.getInstance().addMeasureToPart(
			voice.getVoiceId(), 
			ScoreDataFactory.getInstance().buildFullDataMeasure(
				new Long(1), 
				4, 
				voice.getInstrument().getClefLine(), 
				voice.getInstrument().getClef(), 
				CompositionManager.getInstance().getKey(),
				CompositionManager.getInstance().getMode(),
				CompositionManager.getInstance().getNumerador(), 
				CompositionManager.getInstance().getDenominador(),
				CompositionManager.getInstance().getBpm()
			)
		);
		
		this.voices.put(voice.getVoiceId(), voice);
	}
	
	public void removeVoice(String voiceId) {
		BaseVoice voice = this.voices.get(voiceId);
		if (voice != null) {
			this.voices.remove(voiceId);
			
			this.notifyVoiceRemoved(voiceId);
			
			this.playbackEventListeners.remove(voice);
			
			ScoreManager.getInstance().removePart(voiceId);
		}
	}
	
	public void play() {
		this.notifyPlaybackStarted();
		
		ProgressionManager.getInstance().setCurrentStage(Stage.Tonic);
	}
	
	public void stop() {
		ProgressionManager.getInstance().setCurrentStage(Stage.Suspended);
		
		this.notifyPlaybackStopped();
	
		FileOutputManager.getInstance().saveExternalBufferToFile(ScoreManager.getInstance().getScoreString());
		
		ScoreManager.getInstance().flushParts();
		
		voices.forEach(new BiConsumer<String, BaseVoice>() {
			public void accept(String voiceId, BaseVoice voice) {
				ScoreManager.getInstance().addMeasureToPart(
					voice.getVoiceId(), 
					ScoreDataFactory.getInstance().buildFullDataMeasure(
						new Long(1), 
						4, 
						voice.getInstrument().getClefLine(), 
						voice.getInstrument().getClef(), 
						CompositionManager.getInstance().getKey(),
						CompositionManager.getInstance().getMode(),
						CompositionManager.getInstance().getNumerador(), 
						CompositionManager.getInstance().getDenominador(),
						CompositionManager.getInstance().getBpm()
					)
				);
			}			
		});
	}
}