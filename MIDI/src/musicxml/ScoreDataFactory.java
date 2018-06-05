package musicxml;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import theroy.Clef;
import theroy.Mode;
import theroy.Note;
import theroy.Value;

public class ScoreDataFactory {

	private static ScoreDataFactory instance = null;
	
	private ScoreDataFactory() {
		
	}
	
	public static ScoreDataFactory getInstance() {
		if (instance == null) {
			instance = new ScoreDataFactory();
		}
		
		return instance;
	}

	public DataPart buildDataPart(String id, String name) {
		DataPart result = new DataPart();
		result.setId(id);
		result.setMeasures(new Stack<DataMeasure>());
		result.setName(name);
		
		return result;
	}
	
	public DataMeasure buildFullDataMeasure(
		Long number, Integer divisions, Integer line, Clef clef, Note key, Mode mode, Integer beats, Integer beatType, Integer tempo
	) {
		DataMeasure result = new DataMeasure();
		
		result.setMeasureAttributes(buildDataMeasureAttributes(divisions, line, clef, key, mode, beats, beatType));
		result.setNotes(new LinkedList<DataNote>());
		result.setNumber(number);
		result.setTempo(buildDataTempo(tempo));
		
		return result;
	}
	
	public DataMeasure buildSimpleDataMeasure(Long number) {
		DataMeasure result = new DataMeasure();
		
		result.setNotes(new LinkedList<DataNote>());
		result.setNumber(number);
		
		return result;
	}
	
	public DataMeasureAttributes buildDataMeasureAttributes(
		Integer divisions, Integer line, Clef clef, Note key, Mode mode, Integer beats, Integer beatType
	) {
		DataMeasureAttributes result = new DataMeasureAttributes();
		
		result.setClef(buildDataClef(line, clef));
		result.setDivisions(divisions);
		result.setKey(buildDataKey(key, mode));
		result.setTime(buildDataTime(beats, beatType));
		
		return result;
	}
	
	public DataClef buildDataClef(Integer line, Clef clef) {
		DataClef result = new DataClef();
		result.setLine(line);
		
		switch (clef) {
		case C:
			result.setSign("C");
			
			break;
		case F:
			result.setSign("F");
			
			break;
		case G:
			result.setSign("G");
			
			break;
		}
		
		return result;
	}
	
	public DataKey buildDataKey(Note key, Mode mode) {
		DataKey result = new DataKey();
		
		switch (mode) {
		case Aeolian:
			result.setMode(DataMode.Aeolian);
			
			break;
		case Dorian:
			result.setMode(DataMode.Dorian);
			
			break;
		case Ionian:
			result.setMode(DataMode.Ionian);
			
			break;
		case Locrian:
			result.setMode(DataMode.Locrian);
			
			break;
		case Lydian:
			result.setMode(DataMode.Lydian);
			
			break;
		case Mixolydian:
			result.setMode(DataMode.Mixolydian);
			
			break;
		case Phrygian:
			result.setMode(DataMode.Phrygian);
			
			break;
		}
		
		switch (key) {
		case C:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(-3);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(0);
				
				break;
			}
			
			break;
		case CSharp:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(4);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(-5);
				
				break;
			}
			
			break;
		case D:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(-1);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(2);
				
				break;
			}
			
			break;
		case DSharp:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(6);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(-3);
				
				break;
			}
			
			break;
		case E:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(1);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(4);
				
				break;
			}
			
			break;
		case F:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(-4);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(-1);
				
				break;
			}
			
			break;
		case FSharp:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(3);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(6);
				
				break;
			}
			
			break;
		case G:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(-2);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(1);
				
				break;
			}
			
			break;
		case GSharp:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(5);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(-4);
				
				break;
			}
			
			break;
		case A:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(0);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(3);
				
				break;
			}
			
			break;
		case ASharp:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(-5);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(-2);
				
				break;
			}
			
			break;
		case B:
			switch (mode) {
			case Aeolian:
			case Dorian:
			case Phrygian:
			case Locrian:
				result.setFifths(2);
				
				break;
			case Ionian:
			case Lydian:
			case Mixolydian:
				result.setFifths(5);
				
				break;
			}
			
			break;
		}
		
		return result;
	}
	
	public DataTime buildDataTime(Integer beats, Integer beatType) {
		DataTime result = new DataTime();
		result.setBeats(beats);
		result.setBeatType(beatType);
		
		return result;
	}
	
	public DataSound buildDataTempo(Integer tempo) {
		DataSound result = new DataSound();
		result.setTempo(tempo);
		
		return result;
	}
	
	public DataNote buildDataNote(Value value, Note note, Integer octave) {
		DataNote result = new DataNote();
		result.setDuration(buildDuration(value));
		
		result.setDot(
			value.equals(Value.EighthWithPoint)
			|| value.equals(Value.HalfWithPoint)
			|| value.equals(Value.HundredTwentyEighthWithPoint)
			|| value.equals(Value.QuarterWithPoint)
			|| value.equals(Value.SixteenthWithPoint)
			|| value.equals(Value.SixtyFourthWithPoint)
			|| value.equals(Value.ThirtySecondWithPoint)
			|| value.equals(Value.WholeWithPoint)
		);
		
		DataPitch noteType = buildDataPitch(note, octave);
		
		result.setNoteType(noteType);
		
		result.setType(buildType(value));
		
		return result;
	}
	
	public DataNote buildDataNoteRest(Value value) {
		DataNote result = new DataNote();
		result.setDuration(buildDuration(value));
		
		result.setDot(
			value.equals(Value.EighthWithPoint)
			|| value.equals(Value.HalfWithPoint)
			|| value.equals(Value.HundredTwentyEighthWithPoint)
			|| value.equals(Value.QuarterWithPoint)
			|| value.equals(Value.SixteenthWithPoint)
			|| value.equals(Value.SixtyFourthWithPoint)
			|| value.equals(Value.ThirtySecondWithPoint)
			|| value.equals(Value.WholeWithPoint)
		);
		
		DataRest noteType = new DataRest(); 
		
		result.setNoteType(noteType);
		result.setType(buildType(value));
		
		return result;
	}
	
	public DataNote buildSummedDataNoteRest(DataNote a, DataNote b) {
		DataNote result = null;
		
		Map<Integer, Value> values = new HashMap<Integer, Value>();
		values.put(buildDuration(Value.Whole), Value.Whole);
		values.put(buildDuration(Value.HalfWithPoint), Value.HalfWithPoint);
		values.put(buildDuration(Value.Half), Value.Half);
		values.put(buildDuration(Value.QuarterWithPoint), Value.QuarterWithPoint);
		values.put(buildDuration(Value.Quarter), Value.Quarter);
		values.put(buildDuration(Value.EighthWithPoint), Value.EighthWithPoint);
		values.put(buildDuration(Value.Eighth), Value.Eighth);
		
		Integer summedDuration = a.getDuration() + b.getDuration();
		if (values.containsKey(summedDuration)) {
			Value summedValue = values.get(summedDuration);
			
			result = new DataNote();
			
			result.setDot(
				summedValue.equals(Value.EighthWithPoint)
				|| summedValue.equals(Value.HalfWithPoint)
				|| summedValue.equals(Value.QuarterWithPoint)
			);
			
			result.setDuration(summedDuration);
			
			DataRest noteType = new DataRest();
			
			result.setNoteType(noteType);
			result.setType(buildType(summedValue));
		}
		
		return result;
	}
	
	public DataPitch buildDataPitch(Note note, Integer octave) {
		DataPitch result = new DataPitch();
		result.setOctave(octave);
		
		switch (note) {
		case C: 
			result.setStep("C");
			result.setAlter(0);
			break;
		case CSharp: 
			result.setStep("C");
			result.setAlter(1);
			break;
		case D: 
			result.setStep("D");
			result.setAlter(0);
			break;
		case DSharp:
			result.setStep("D");
			result.setAlter(1);
			break;
		case E: 
			result.setStep("E");
			result.setAlter(0);
			break;
		case F: 
			result.setStep("F");
			result.setAlter(0);
			break;
		case FSharp: 
			result.setStep("F");
			result.setAlter(1);
			break;
		case G: 
			result.setStep("G");
			result.setAlter(0);
			break;
		case GSharp: 
			result.setStep("G");
			result.setAlter(1);
			break;
		case A: 
			result.setStep("A");
			result.setAlter(0);
			break;
		case ASharp: 
			result.setStep("A");
			result.setAlter(1);
			break;
		case B: 
			result.setStep("B");
			result.setAlter(0);
			break;
		}
		
		return result;
	}

	public Integer buildDuration(Value value) {
		Integer result = 0;
		
		switch (value) {
		case Whole:
			result = 16;
			break;
		case WholeWithPoint:
			result = 24;
			break;
		case Half:
			result = 8;
			break;
		case HalfWithPoint:
			result = 12;
			break;
		case Quarter:
			result = 4;
			break;
		case QuarterWithPoint:
			result = 6;
			break;
		case Eighth:
			result = 2;
			break;
		case EighthWithPoint:
			result = 3;
			break;
		case Sixteenth:
			result = 1;
			break;
		case SixteenthWithPoint:
			result = 1;
			break;
		case ThirtySecond:
			result = 0;
			break;
		case ThirtySecondWithPoint:
			result = 0;
			break;
		case SixtyFourth:
			result = 0;
			break;
		case SixtyFourthWithPoint:
			result = 0;
			break;
		case HundredTwentyEighth:
			result = 0;
			break;
		case HundredTwentyEighthWithPoint:
			result = 0;
			break;
		}
			
		return result;
	}

	public String buildType(Value value) {
		String result = "";
		
		switch (value) {
		case Whole:
			result = "whole";
			break;
		case WholeWithPoint:
			result = "whole";
			break;
		case Half:
			result = "half";
			break;
		case HalfWithPoint:
			result = "half";
			break;
		case Quarter:
			result = "quarter";
			break;
		case QuarterWithPoint:
			result = "quarter";
			break;
		case Eighth:
			result = "eighth";
			break;
		case EighthWithPoint:
			result = "eighth";
			break;
		case Sixteenth:
			result = "16th";
			break;
		case SixteenthWithPoint:
			result = "16th";
			break;
		case ThirtySecond:
			result = "32nd";
			break;
		case ThirtySecondWithPoint:
			result = "32nd";
			break;
		case SixtyFourth:
			result = "64th";
			break;
		case SixtyFourthWithPoint:
			result = "64th";
			break;
		case HundredTwentyEighth:
			result = "128th";
			break;
		case HundredTwentyEighthWithPoint:
			result = "128th";
			break;
		}
		
		return result;
	}

	public DataBeam buildDataBeam(Integer beamNumber, DataBeamType beamType) {
		DataBeam result = new DataBeam();
		result.setBeamNumber(beamNumber);
		result.setBeamType(beamType);
		
		return result;
	}
}