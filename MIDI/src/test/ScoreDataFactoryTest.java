package test;

import java.util.Collection;
import java.util.LinkedList;

import musicxml.DataMeasure;
import musicxml.DataNote;
import musicxml.ScoreDataFactory;
import theroy.Clef;
import theroy.Mode;
import theroy.Note;
import theroy.Value;

public class ScoreDataFactoryTest {

	public ScoreDataFactoryTest() {
		DataMeasure measure =
			ScoreDataFactory.getInstance().buildFullDataMeasure(
				new Long(1), 4, 2, Clef.G, Note.C, Mode.Ionian, 4, 4, 100
			);
		
		Collection<DataNote> notes = new LinkedList<DataNote>();
		
		notes.add(ScoreDataFactory.getInstance().buildDataNoteRest(Value.Quarter));
		notes.add(ScoreDataFactory.getInstance().buildDataNoteRest(Value.Quarter));
		notes.add(ScoreDataFactory.getInstance().buildDataNote(Value.Sixteenth, Note.D, 3));
		notes.add(ScoreDataFactory.getInstance().buildDataNote(Value.QuarterWithPoint, Note.F, 3));
		notes.add(ScoreDataFactory.getInstance().buildDataNote(Value.Sixteenth, Note.C, 3));
		
		measure.setNotes(notes);
		
		System.out.println(measure);
	}
	
	public static void main(String[] args) {
		new ScoreDataFactoryTest();
	}
}