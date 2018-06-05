package test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import musicxml.DataClef;
import musicxml.DataIdentification;
import musicxml.DataKey;
import musicxml.DataMeasure;
import musicxml.DataMeasureAttributes;
import musicxml.DataNote;
import musicxml.DataPart;
import musicxml.DataPitch;
import musicxml.DataRest;
import musicxml.DataScore;
import musicxml.DataSound;
import musicxml.DataTime;
import musicxml.DataWork;

public class DataScoreTest {

	public DataScoreTest() {
		DataScore dataScore = new DataScore();
		
		DataIdentification identification = new DataIdentification();
		identification.setComposer("Composer");
		
		dataScore.setIdentification(identification);
		
		ConcurrentMap<String, DataPart> parts = new ConcurrentHashMap<String, DataPart>();
		
		DataPart part = new DataPart();
		part.setId("P0");
		
		Stack<DataMeasure> measures = new Stack<DataMeasure>();
		
		// Measure 1
		DataMeasure measure = new DataMeasure();
		
		DataMeasureAttributes measureAttributes = new DataMeasureAttributes();
		
		DataClef clef = new DataClef();
		clef.setLine(4);
		clef.setSign("G");
		
		measureAttributes.setClef(clef);
		
		measureAttributes.setDivisions(4);

		DataKey key = new DataKey();
		key.setFifths(-4);
		
		measureAttributes.setKey(key);
		
		DataTime time = new DataTime();
		time.setBeats(4);
		time.setBeatType(4);
		
		measureAttributes.setTime(time);
		
		measure.setMeasureAttributes(measureAttributes);
		
		Collection<DataNote> notes = new LinkedList<DataNote>();
		
		DataNote note = new DataNote();
		note.setDuration(2);
		
		DataPitch noteTypePitch = new DataPitch();
		noteTypePitch.setAlter(-1);
		noteTypePitch.setOctave(4);
		noteTypePitch.setStep("C");
		
		note.setNoteType(noteTypePitch);
		
		note.setType("quarter");
		
		notes.add(note);
		
		note = new DataNote();
		note.setDuration(4);
		
		DataRest noteTypeRest = new DataRest();
		noteTypeRest.setMeasure("yes");
		
		note.setNoteType(noteTypeRest);
		
		note.setType("half");
		
		notes.add(note);
		
		measure.setNotes(notes);
		
		measure.setNumber(new Long(1));
		
		DataSound tempo = new DataSound();
		tempo.setTempo(150);
		
		measure.setTempo(tempo);
		
		measures.add(measure);
		
		// Measure 2
		measure = new DataMeasure();
		
		notes = new LinkedList<DataNote>();
		
		note = new DataNote();
		note.setDuration(2);
		
		noteTypePitch = new DataPitch();
		noteTypePitch.setAlter(1);
		noteTypePitch.setOctave(4);
		noteTypePitch.setStep("F");
		
		note.setNoteType(noteTypePitch);
		
		note.setType("quarter");
		
		notes.add(note);
		
		note = new DataNote();
		note.setDuration(4);
		
		noteTypeRest = new DataRest();
		noteTypeRest.setMeasure("yes");
		
		note.setNoteType(noteTypeRest);
		
		note.setType("eighth");
		
		notes.add(note);
		
		measure.setNotes(notes);
		
		measure.setNumber(new Long(2));
		
		tempo = new DataSound();
		tempo.setTempo(150);
		
		measure.setTempo(tempo);
		
		measures.add(measure);
		
		part.setMeasures(measures);
		
		part.setName("Part name");
		
		parts.put(part.getId(), part);
		
		dataScore.setParts(parts);
		
		DataWork work = new DataWork();
		work.setWorkTitle("Work title");
		
		dataScore.setWork(work);
		
		System.out.println(dataScore.toString());
	}
	
	public static void main(String[] args) {
		new DataScoreTest();
	}
}