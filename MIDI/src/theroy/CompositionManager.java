package theroy;

import java.util.Collection;
import java.util.LinkedList;

import math.ValueMapper;

public class CompositionManager {

	private static CompositionManager instance = null;
	
	private Note key = Note.C;
	private Mode mode = Mode.Ionian;
	private int bpm = 100;
	private int numerador = 4;
	private int denominador = 4;
	private int measuresPerStage = 1;
	
	private Collection<CompositionTonalityChangeListener> compositionTonalityChangeListeners = new LinkedList<CompositionTonalityChangeListener>();
	
	private CompositionManager() {
		
	}
	
	public static CompositionManager getInstance() {
		if (instance == null) {
			instance = new CompositionManager();
		}
		return instance;
	}
	
	public Note getKey() {
		return this.key;
	}

	public void setKey(Note key) {
		if (!key.equals(this.key)) {
			this.key = key;
			
			this.notifyTonalityChange();
		}
	}
	
	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		if (!mode.equals(this.mode)) {
			this.mode = mode;
			
			this.notifyTonalityChange();
		}
	}
	
	public int getBpm() {
		return this.bpm;
	}
	
	public void setBpm(int bpm) {
		this.bpm = bpm;
		ValueMapper.getInstance().setBpm(this.bpm);
	}

	public int getNumerador() {
		return this.numerador;
	}

	public void setNumerador(int numerador) {
		this.numerador = numerador;
	}

	public int getDenominador() {
		return this.denominador;
	}

	public void setDenominador(int denominador) {
		this.denominador = denominador;
	}

	public int getMeasuresPerStage() {
		return measuresPerStage;
	}

	public void setMeasuresPerStage(int measuresPerStage) {
		this.measuresPerStage = measuresPerStage;
	}

	public void addTonalityChangeListener(CompositionTonalityChangeListener compositionTonalityChangeListener) {
		this.compositionTonalityChangeListeners.add(compositionTonalityChangeListener);
	}
	
	public void notifyTonalityChange() {
		for (CompositionTonalityChangeListener compositionTonalityChangeListener : this.compositionTonalityChangeListeners) {
			compositionTonalityChangeListener.tonalityChanged(new CompositionEventData());
		}
	}
}