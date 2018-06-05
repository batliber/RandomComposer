package test;

import instrument.Instrument;
import instrument.PianoLeadingMedium;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import math.NoteMapper;
import media.MediaManager;
import theroy.Degree;
import theroy.Mode;
import theroy.Note;
import theroy.ScaleFactory;

public class ScaleFactoryTest {

	public ScaleFactoryTest() {
		Instrument instrument = new PianoLeadingMedium();
		
		Collection<Map<Degree, Note>> scales = new LinkedList<Map<Degree, Note>>();
		
		Map<Degree, Note> scale;
//		scale = ScaleFactory.getInstance().getScale(Note.C, Mode.Ionian);
//		scales.add(scale);
		System.out.println("");
		scale = ScaleFactory.getInstance().getScale(Note.D, Mode.Dorian);
//		scale = ScaleFactory.getInstance().getScale(Note.C, Mode.Dorian);
		scales.add(scale);
		System.out.println("");
		scale = ScaleFactory.getInstance().getScale(Note.E, Mode.Phrygian);
//		scale = ScaleFactory.getInstance().getScale(Note.C, Mode.Phrygian);
		scales.add(scale);
		System.out.println("");
		scale = ScaleFactory.getInstance().getScale(Note.F, Mode.Lydian);
//		scale = ScaleFactory.getInstance().getScale(Note.C, Mode.Lydian);
		scales.add(scale);
		System.out.println("");
		scale = ScaleFactory.getInstance().getScale(Note.G, Mode.Mixolydian);
//		scale = ScaleFactory.getInstance().getScale(Note.C, Mode.Mixolydian);
		scales.add(scale);
		System.out.println("");
		scale = ScaleFactory.getInstance().getScale(Note.A, Mode.Aeolian);
//		scale = ScaleFactory.getInstance().getScale(Note.C, Mode.Aeolian);
		scales.add(scale);
		System.out.println("");
		scale = ScaleFactory.getInstance().getScale(Note.B, Mode.Locrian);
//		scale = ScaleFactory.getInstance().getScale(Note.C, Mode.Locrian);
		scales.add(scale);
		
		for (Map<Degree, Note> s : scales) {
			int degreeIMapping = NoteMapper.getInstance().getMapping(s.get(Degree.I), 0, instrument.getTessitura().getMin());
			
			for (Degree degree : Degree.values()) {
				MediaManager.getInstance().allNotesOff(0);
				
				Note note = s.get(degree);
				int mapping = NoteMapper.getInstance().getMapping(note, 0, instrument.getTessitura().getMin());
				
				if (mapping < degreeIMapping) {
					mapping += 12;
				}
				
				MediaManager.getInstance().noteOn(mapping, 10);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new ScaleFactoryTest();
	}
}