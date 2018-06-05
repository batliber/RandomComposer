package timing;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import math.ValueMapper;
import playback.PlaybackEventData;
import playback.PlaybackEventListener;
import playback.PlaybackManager;
import theroy.CompositionManager;
import theroy.Stage;
import theroy.Value;

public class FlowManager extends Thread {

	private static FlowManager instance = null;
	
	private int beat = 0;
	private int measures = 0;
	
	private Collection<FlowStageChangeListener> flowStageChangeListeners = new LinkedList<FlowStageChangeListener>();
	
	private FlowManager() {
		PlaybackManager.getInstance().addPlaybackStartedEventListener(new PEL());
	}
	
	public static FlowManager getInstance() {
		if (instance == null) {
			instance = new FlowManager();
		}
		return instance;
	}

	public void init() {
		
	}
	
	public void run() {
		try {
			if (ProgressionManager.getInstance().getCurrentStage() != Stage.Suspended) {
				if (this.beat == 0) {
					// Si estoy en el primer beat del compás.
					
					if (this.measures == 0) {
						// Si estoy en el primer compás del stage.
						ProgressionManager.getInstance().goNextStage();
						
						this.notifyStageChange();
					}
				}
				
				this.beat = (this.beat + 1) % CompositionManager.getInstance().getNumerador();
				this.measures = (this.measures + 1) % CompositionManager.getInstance().getMeasuresPerStage();
				
				ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
				executor.schedule(this, ValueMapper.getInstance().getMapping(Value.Quarter), TimeUnit.MILLISECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStageChangeListener(FlowStageChangeListener flowStageChangeListener) {
		this.flowStageChangeListeners.add(flowStageChangeListener);
	}
	
	public void notifyStageChange() {
		for (FlowStageChangeListener flowStageChangeListener : this.flowStageChangeListeners) {
			flowStageChangeListener.stageChanged(new FlowEventData());
		}
	}
	
	private class PEL implements PlaybackEventListener {
		public void playbackStarted(PlaybackEventData playbackEventData) {
			ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
			executor.schedule(FlowManager.getInstance(), ValueMapper.getInstance().getMapping(Value.Whole), TimeUnit.MILLISECONDS);
		}

		public void playbackStopped(PlaybackEventData playbackEventData) {
			
		}
	
		public void voiceRemoved(PlaybackEventData playbackEventData) {
			
		}
	}
}