package musicxml;

import java.util.Collection;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

import theroy.Clef;
import theroy.CompositionManager;

public class ScoreManager {

	private static ScoreManager instance = null;
	
	private DataScore score;
	
	private ScoreManager() {
		score = new DataScore();
		
		DataIdentification identification = new DataIdentification();
		identification.setComposer("Líber Batalla");
		
		score.setIdentification(identification);
		
		ConcurrentMap<String, DataPart> parts = new ConcurrentHashMap<String, DataPart>();
		
		score.setParts(parts);
		
		DataWork work = new DataWork();
		work.setWorkTitle("Random composition");
		
		score.setWork(work);
	}
	
	public static ScoreManager getInstance() {
		if (instance == null) {
			instance = new ScoreManager();
		}
		
		return instance;
	}
	
	public void addPart(String id, DataPart part) {
		score.getParts().put(id, part);
	}
	
	public void removePart(String id) {
		score.getParts().remove(id);
	}
	
	public void addMeasureToPart(String partId, DataMeasure measure) {
		score.getParts().get(partId).getMeasures().push(measure);
	}
	
	public void addNoteToPart(String partId, DataNote note) {
		Stack<DataMeasure> measures = score.getParts().get(partId).getMeasures();
		if (measures.size() <= 0) {
			DataMeasure measure = 
				ScoreDataFactory.getInstance().buildFullDataMeasure(
					new Long(1),
					4,
					4,
					Clef.G,
					CompositionManager.getInstance().getKey(),
					CompositionManager.getInstance().getMode(),
					CompositionManager.getInstance().getNumerador(),
					CompositionManager.getInstance().getDenominador(),
					CompositionManager.getInstance().getBpm()
				);
			
			measures.push(measure);
		}
		
		if (!(note.getNoteType() instanceof DataRest)) {
			// Si la nota no es silencio.
			
			Collection<DataNote> measureNotes = measures.peek().getNotes();
			if (!measureNotes.isEmpty()) {
				// Si hay notas en el compás.
				DataNote previousNote = measureNotes.toArray(new DataNote[] {})[measureNotes.size() - 1];
				
				if (previousNote.getBeam() != null) {
					// Si la nota anterior tenía beam.
					if (previousNote.getDuration().equals(note.getDuration())) {
						// Si la nota anterior tenía la misma duración.
						note.setBeam(
							ScoreDataFactory.getInstance().buildDataBeam(
								previousNote.getBeam().getBeamNumber(), 
								DataBeamType.Continue
							)
						);
					} else {
						// Si la duración es distinta.
						previousNote.setBeam(
							ScoreDataFactory.getInstance().buildDataBeam(
								previousNote.getBeam().getBeamNumber(), 
								DataBeamType.End
							)
						);
						
						note.setBeam(
							ScoreDataFactory.getInstance().buildDataBeam(
								previousNote.getBeam().getBeamNumber() + 1, 
								DataBeamType.Begin
							)
						);
					}
				} else {
					// Si la nota anterior no tenía beam.
					note.setBeam(
						ScoreDataFactory.getInstance().buildDataBeam(
							1, 
							DataBeamType.Begin
						)
					);
				}
			} else {
				// Si no hay notas en el compás.
				DataMeasure currentMeasure = measures.pop();
				
				if (!measures.isEmpty()) {
					// Si hay compases anteriores.
					DataMeasure previousMeasure = measures.peek();
					
					DataNote previousNote = null;
					for (DataNote previousMeasureNote : previousMeasure.getNotes()) {
						previousNote = previousMeasureNote;
					}
					
					if (previousNote.getBeam() != null) {
						// Si la última nota del anterior compás tenía beam.
						previousNote.setBeam(
							ScoreDataFactory.getInstance().buildDataBeam(
								previousNote.getBeam().getBeamNumber(), 
								DataBeamType.End
							)
						);
					}
				}
				
				measures.push(currentMeasure);
				
				note.setBeam(
					ScoreDataFactory.getInstance().buildDataBeam(
						1, 
						DataBeamType.Begin
					)
				);
			}
		} else {
			// Si la nota es silencio.
			Collection<DataNote> measureNotes = measures.peek().getNotes();
			if (!measureNotes.isEmpty()) {
				// Si hay notas en el compás.
				DataNote previousNote = measureNotes.toArray(new DataNote[] {})[measureNotes.size() - 1];
				
				if (previousNote.getBeam() != null) {
					// Si la nota anterior tenía beam.
					previousNote.setBeam(
						ScoreDataFactory.getInstance().buildDataBeam(
							previousNote.getBeam().getBeamNumber(), 
							DataBeamType.End
						)
					);
				}
			} else {
				// Si no hay notas en el compás.
				DataMeasure currentMeasure = measures.pop();
				
				if (!measures.isEmpty()) {
					// Si hay compases anteriores.
					DataMeasure previousMeasure = measures.peek();
					
					DataNote previousNote = null;
					for (DataNote previousMeasureNote : previousMeasure.getNotes()) {
						previousNote = previousMeasureNote;
					}
					
					if (previousNote.getBeam() != null) {
						// Si la última nota del anterior compás tenía beam.
						previousNote.setBeam(
							ScoreDataFactory.getInstance().buildDataBeam(
								previousNote.getBeam().getBeamNumber(), 
								DataBeamType.End
							)
						);
					}
				}
				
				measures.push(currentMeasure);
			}
		}
		
		measures.peek().getNotes().add(note);
	}

	public void flushParts() {
		score.getParts().forEach(new BiConsumer<String, DataPart>() {
			public void accept(String partId, DataPart part) {
				part.getMeasures().clear();
			}
		});
	}
	
	public String getScoreString() {
		return score.toString();
	}
}