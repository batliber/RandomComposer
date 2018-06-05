package theroy;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ScaleFactory {

	private Map<Mode, Collection<Long>> modes = new HashMap<Mode, Collection<Long>>();
	
	private static ScaleFactory instance = null;
	
	private ScaleFactory() {
		// Modos
		Collection<Long> intervals = new LinkedList<Long>();
		
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		
		this.modes.put(Mode.Ionian, intervals);
		
		intervals = new LinkedList<Long>();
		
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		
		this.modes.put(Mode.Dorian, intervals);
		
		intervals = new LinkedList<Long>();
		
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		
		this.modes.put(Mode.Phrygian, intervals);
		
		intervals = new LinkedList<Long>();
		
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		
		this.modes.put(Mode.Lydian, intervals);
		
		intervals = new LinkedList<Long>();
		
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		
		this.modes.put(Mode.Mixolydian, intervals);
		
		intervals = new LinkedList<Long>();
		
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		
		this.modes.put(Mode.Aeolian, intervals);
		
		intervals = new LinkedList<Long>();
		
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		intervals.add(new Long(1));
		intervals.add(new Long(2));
		intervals.add(new Long(2));
		
		this.modes.put(Mode.Locrian, intervals);
	}
	
	public static ScaleFactory getInstance() {
		if (instance == null) {
			instance = new ScaleFactory();
		}
		return instance;
	}
	
	public Map<Degree, Note> getScale(Note root, Mode mode) {
		Map<Degree, Note> result = new HashMap<Degree, Note>();
		
		Note[] notes = Note.values();
		int index = 0;
		while (!notes[index].equals(root)) {
			index++;
		}
		
		Degree[] degrees = new Degree[] {
			Degree.I,
			Degree.II,
			Degree.III,
			Degree.IV,
			Degree.V,
			Degree.VI,
			Degree.VII,
		};
		
		result.put(Degree.I, root);
		
		int i = 1;
		for (Long interval : this.modes.get(mode)) {
			index = new Long((index + interval) % notes.length).intValue();
			result.put(degrees[i], notes[index]);
			
			i++;
		}
		
		return result;
	}
}