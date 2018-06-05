package theroy;

public class StateFactory {

	private static StateFactory instance = null;
	
	private StateFactory() {
		
	}
	
	public static StateFactory getInstance() {
		if (instance == null) {
			instance = new StateFactory();
		}
		return instance;
	}
	
	public State getStateByStage(Stage stage, Note key, Mode mode) {
		State result = null;
		
		switch (stage) {
		case Dominant:
			result = new Dominant(key, mode);
			
			break;
		case DominantDominant:
			result = new DominantDominant(key, mode);
			
			break;
		case DominantMinorSeventh:
			result = new DominantMinorSeventh(key, mode);
			
			break;
		case DominantSus4:
			result = new DominantSus4(key, mode);
			
			break;
		case Mediant:
			result = new Mediant(key, mode);
			
			break;
		case MediantDominant:
			result = new MediantDominant(key, mode);
			
			break;
		case Sensible:
			result = new Sensible(key, mode);
			
			break;
		case SensibleDominant:
			result = new SensibleDominant(key, mode);
			
			break;
		case SubDominant:
			result = new SubDominant(key, mode);
			
			break;
		case SubDominantDominant:
			result = new SubDominantDominant(key, mode);
			
			break;
		case SuperDominant:
			result = new SuperDominant(key, mode);
			
			break;
		case SuperDominantDominant:
			result = new SuperDominantDominant(key, mode);
			
			break;
		case SuperTonic:
			result = new SuperTonic(key, mode);
			
			break;
		case SuperTonicDominant:
			result = new SuperTonicDominant(key, mode);
			
			break;
		case Tonic:
			result = new Tonic(key, mode);
			
			break;
		case TonicDominantSeventh:
			result = new TonicDominantSeventh(key, mode);
			
			break;
		case Suspended:
			result = new Tonic(key, mode);
			
			break;
		case Modulation:
			result = new Modulation(key, mode);
			
			break;
		}
		
		return result;
	}
}