package test;

import theroy.Mode;
import theroy.Note;
import theroy.Stage;
import theroy.State;
import theroy.StateFactory;

public class StateFactoryTest {

	public StateFactoryTest() {
		State state = StateFactory.getInstance().getStateByStage(Stage.DominantSus4, Note.F, Mode.Ionian);
		
		String notes = "";
		for (Note note : state.getNotes()) {
			notes +=
				note + " ";
		}
		
		System.out.println(notes);
	}
	
	public static void main(String[] args) {
		new StateFactoryTest();
	}
}