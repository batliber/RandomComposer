package instrument;

public class InstrumentFactory {

	private static InstrumentFactory instance = null;
	
	private InstrumentFactory() {
		
	}
	
	public static InstrumentFactory getInstance() {
		if (instance == null) {
			instance = new InstrumentFactory();
		}
		
		return instance;
	}
	
	public Instrument buildInstrument(InstrumentType instrumentType) {
		Instrument result = null;
		
		switch (instrumentType) {
		case DoubleBass:
			result = new DoubleBass();
			
			break;
		case ElectricBass:
			result = new ElectricBass();
			
			break;
		case Guitar:
			result = new Guitar();
			
			break;
		case Piano:
			result = new Piano();
			
			break;
		case PianoAccompanimentBass:
			result = new PianoAccompanimentBass();
			
			break;
		case PianoAccompanimentHigh:
			result = new PianoAccompanimentHigh();
			
			break;
		case PianoAccompanimentMedium:
			result = new PianoAccompanimentMedium();
			
			break;
		case PianoLeadingMedium:
			result = new PianoLeadingMedium();
			
			break;
		case Tick:
			result = new Tick();
			
			break;
		case Viola:
			result = new Viola();
			
			break;
		case Violin:
			result = new Violin();
			
			break;
		case Violoncello:
			result = new Violoncello();
			
			break;
		}
		
		return result;
	}
}