package composition;

import instrument.Instrument;

public class VoiceFactory {

	private static VoiceFactory instance = null;
	
	private VoiceFactory() {
		
	}
	
	public static VoiceFactory getInstance() {
		if (instance == null) {
			instance = new VoiceFactory();
		}
		
		return instance;
	}

	public BaseVoice buildVoice(VoiceType voiceType, Instrument instrument, int channelNumber, String id, String name) {
		BaseVoice result = null;
		
		switch (voiceType) {
		case VoiceAccompaniment:
			result = new VoiceAccompaniment(
				instrument, 
				channelNumber, 
				id, 
				name
			);
			
			break;
		case VoiceAccompanimentRiff:
			result = new VoiceAccompanimentRiff(
				instrument, 
				channelNumber, 
				id, 
				name
			);
			
			break;
		case VoiceLeading:
			result = new VoiceLeading(
				instrument, 
				channelNumber, 
				id, 
				name
			);
			
			break;
		case VoiceLeadingEights:
			result = new VoiceLeadingEighths(
				instrument, 
				channelNumber, 
				id, 
				name
			);
			
			break;
		case VoiceLeadingQuarters:
			result = new VoiceLeadingQuarters(
				instrument, 
				channelNumber, 
				id, 
				name
			);
			
			break;
		}
		
		return result;
	}
}