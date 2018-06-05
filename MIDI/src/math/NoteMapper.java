package math;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import theroy.Note;

public class NoteMapper {
	
	private static NoteMapper instance = null;
	
	private ConcurrentMap<Note, Integer> mappings = new ConcurrentHashMap<Note, Integer>();
	private ConcurrentMap<Integer, Note> reverseMappings = new ConcurrentHashMap<Integer, Note>();
	
	private NoteMapper() {
		this.mappings.put(Note.C, 0);
		this.mappings.put(Note.CSharp, 1);
		this.mappings.put(Note.D, 2);
		this.mappings.put(Note.DSharp, 3);
		this.mappings.put(Note.E, 4);
		this.mappings.put(Note.F, 5);
		this.mappings.put(Note.FSharp, 6);
		this.mappings.put(Note.G, 7);
		this.mappings.put(Note.GSharp, 8);
		this.mappings.put(Note.A, 9);
		this.mappings.put(Note.ASharp, 10);
		this.mappings.put(Note.B, 11);
		
		for (int i=0; i<128; i++) {
			this.reverseMappings.put(i, Note.values()[i % 12]);
		}
	}
	
	public static NoteMapper getInstance() {
		if (instance == null) {
			instance = new NoteMapper();
		}
		
		return instance;
	}
	
	public int getNoteCount() {
		return this.mappings.size();
	}
	
	// Returns the note number associated with the note.
	public int getMapping(Note note, int octave, int minimumNoteRange) {
		return octave * 12 + minimumNoteRange + this.mappings.get(note);
	}
	
	// Returns the Note associated with the number noteNumber
	public Note getReverseMapping(int noteNumber) {
		return reverseMappings.get(noteNumber);
	}
}