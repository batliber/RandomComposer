package timing;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import theroy.CompositionManager;
import theroy.Mode;
import theroy.Note;
import theroy.Stage;

public class ProgressionManager {

	private static ProgressionManager instance = null;
	
	private Queue<Stage> stages = new LinkedList<Stage>(); 
	private Stage currentStage = Stage.Suspended;
	
	private ProgressionManager() {
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.SubDominant);
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.Supertonic);
		
		// Pop progression I
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.Supertonic);
//		this.stages.add(Stage.SubDominant);
		
		// Pop progression II
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.Mediant);
//		this.stages.add(Stage.Sensible);
//		this.stages.add(Stage.SubDominant);
		
		// Pachabell's canon in D major progresion
		this.stages.add(Stage.Tonic);
		this.stages.add(Stage.Dominant);
		this.stages.add(Stage.SuperDominant);
		this.stages.add(Stage.Mediant);
		this.stages.add(Stage.SubDominant);
		this.stages.add(Stage.Tonic);
		this.stages.add(Stage.SubDominant);
		this.stages.add(Stage.Dominant);
		
		// "La Bamba" progression
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.SubDominant);
//		this.stages.add(Stage.Dominant);
		
		// Test progression
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.TonicDominantSeventh);
//		this.stages.add(Stage.SubDominant);
//		this.stages.add(Stage.SubDominant);
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.Dominant);
		
//		this.stages.add(Stage.Tonic);
//		this.stages.add(Stage.SuperTonic);
//		this.stages.add(Stage.Mediant);
//		this.stages.add(Stage.SubDominant);
//		this.stages.add(Stage.Dominant);
//		this.stages.add(Stage.SuperDominant);
//		this.stages.add(Stage.Sensible);
//		this.stages.add(Stage.Tonic);
	}
	
	public static ProgressionManager getInstance() {
		if (instance == null) {
			instance = new ProgressionManager();
		}
		return instance;
	}
	
	public Stage getCurrentStage() {
		return currentStage;
	}
	
	public void setCurrentStage(Stage stage) {
		this.currentStage = stage;
	}
	
	public Queue<Stage> listStages() {
		return stages;
	}

	public void addStage(Stage stage) {
		this.stages.add(stage);
	}
	
	public void removeStage(Integer index) {
		for (int i=0; i<stages.size()+1; i++) {
			Stage stage = stages.poll();
			
			if (!index.equals(i)) {
				stages.add(stage);
			}
		}
	}
	
	public Stage goNextStage() {
		this.currentStage = this.stages.poll();
		
		if (this.currentStage.equals(Stage.Modulation)) {
			Random random = new Random();
			
			Note newKey = Note.values()[random.nextInt(Note.values().length)];
			Mode newMode = Mode.values()[random.nextInt(Mode.values().length)];
			
			CompositionManager.getInstance().setKey(newKey);
			CompositionManager.getInstance().setMode(newMode);
			
			this.currentStage = this.stages.poll();
			
			this.stages.add(Stage.Modulation);
		}
		
		this.stages.add(this.currentStage);
		
		return this.currentStage;
	}
}