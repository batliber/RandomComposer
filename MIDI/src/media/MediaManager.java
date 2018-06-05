package media;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MediaManager {

	private static MediaManager instance = null;
	
	private Synthesizer synthesizer = null;

	private MediaManager() {
		try {
			this.synthesizer = MidiSystem.getSynthesizer();
			
			this.synthesizer.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public static MediaManager getInstance() {
		if (instance == null) {
			instance = new MediaManager();
		}
		return instance;
	}

	public void noteOn(int noteNumber, int velocity) {
		this.synthesizer.getChannels()[0].noteOn(noteNumber, velocity + 100);
	}
	
	public void noteOn(int noteNumber, int velocity, int channelNumber) {
		this.synthesizer.getChannels()[channelNumber].noteOn(noteNumber, velocity + 100);
	}
	
	public void noteOff(int noteNumber, int channelNumber) {
		this.synthesizer.getChannels()[channelNumber].noteOff(noteNumber);
	}

	public void allNotesOff(int channel) {
		this.synthesizer.getChannels()[channel].allNotesOff();
	}

	public Collection<Integer> listChannels() {
		Collection<Integer> result = new LinkedList<Integer>();
		
		for (int i=0; i<this.synthesizer.getChannels().length; i++) {
			result.add(i);
		}
		
		return result;
	}
	
	public Map<Integer, String> listInstruments() {
		Map<Integer, String> result = new HashMap<Integer, String>();
		
		for (int i=0; i < this.synthesizer.getAvailableInstruments().length; i++) {
			result.put(i, this.synthesizer.getAvailableInstruments()[i].getName());
		}
		
		return result;
	}

	public Integer getChannelInstrument(int channelNumber) {
		MidiChannel channel = this.synthesizer.getChannels()[channelNumber];
		
		return channel.getProgram();
	}
	
	public void setChannelInstrument(int channelNumber, int instrumentNumber) {
		MidiChannel channel = this.synthesizer.getChannels()[channelNumber];
		
		channel.programChange(this.synthesizer.getAvailableInstruments()[instrumentNumber].getPatch().getProgram());
	}
}