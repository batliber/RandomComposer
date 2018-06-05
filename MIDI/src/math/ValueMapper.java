package math;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import theroy.Value;

/**
 * Clase que realiza el mapeo entre duraciones de notas y milisegundos según el tempo definido.
 * 
 * @author lbatalla
 */
public class ValueMapper {

	private static ValueMapper instance = null;
	
	private int bpm = 100;
	private ConcurrentMap<Value, Long> mappings = new ConcurrentHashMap<Value, Long>();
	private ConcurrentMap<Long, Value> reverseMappings = new ConcurrentHashMap<Long, Value>();
	
	private ValueMapper() {
		int beatDuration = ((60 * 1000) / this.bpm);
		
		this.mappings.put(Value.Whole, new Long(beatDuration * 4));
		this.mappings.put(Value.WholeWithPoint, new Long(beatDuration * 4) + new Long(beatDuration) * 2);
		this.mappings.put(Value.Half, new Long(beatDuration * 2));
		this.mappings.put(Value.HalfWithPoint, new Long(beatDuration * 2) + new Long(beatDuration));
		this.mappings.put(Value.Quarter, new Long(beatDuration));
		this.mappings.put(Value.QuarterWithPoint, new Long(beatDuration) + new Long(beatDuration) / 2);
		this.mappings.put(Value.Eighth, new Long(beatDuration / 2));
		this.mappings.put(Value.EighthWithPoint, new Long(beatDuration / 2) + new Long(beatDuration / 4));
		this.mappings.put(Value.Sixteenth, new Long(beatDuration / 4));
		this.mappings.put(Value.SixteenthWithPoint, new Long(beatDuration / 4) + new Long(beatDuration / 8));
		this.mappings.put(Value.ThirtySecond, new Long(beatDuration / 8));
		this.mappings.put(Value.ThirtySecondWithPoint, new Long(beatDuration / 8) + new Long(beatDuration / 16));
		this.mappings.put(Value.SixtyFourth, new Long(beatDuration / 16));
		this.mappings.put(Value.SixtyFourthWithPoint, new Long(beatDuration / 16) + new Long(beatDuration / 32));
		this.mappings.put(Value.HundredTwentyEighth, new Long(beatDuration / 32));
		this.mappings.put(Value.HundredTwentyEighthWithPoint, new Long(beatDuration / 32) + new Long(beatDuration / 64));
		
		this.reverseMappings.put(new Long(beatDuration * 4), Value.Whole);
		this.reverseMappings.put(new Long(beatDuration * 4) + new Long(beatDuration) * 2, Value.WholeWithPoint);
		this.reverseMappings.put(new Long(beatDuration * 2), Value.Half);
		this.reverseMappings.put(new Long(beatDuration * 2) + new Long(beatDuration), Value.HalfWithPoint);
		this.reverseMappings.put(new Long(beatDuration), Value.Quarter);
		this.reverseMappings.put(new Long(beatDuration) + new Long(beatDuration / 2), Value.QuarterWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 2), Value.Eighth);
		this.reverseMappings.put(new Long(beatDuration / 2) + new Long(beatDuration / 4), Value.EighthWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 4), Value.Sixteenth);
		this.reverseMappings.put(new Long(beatDuration / 4) + new Long(beatDuration / 8), Value.SixteenthWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 8), Value.ThirtySecond);
		this.reverseMappings.put(new Long(beatDuration / 8) + new Long(beatDuration / 16), Value.ThirtySecondWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 16), Value.SixtyFourth);
		this.reverseMappings.put(new Long(beatDuration / 16) + new Long(beatDuration / 32), Value.SixtyFourthWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 32), Value.HundredTwentyEighth);
		this.reverseMappings.put(new Long(beatDuration / 32) + new Long(beatDuration / 64), Value.HundredTwentyEighthWithPoint);
	}
	
	public static ValueMapper getInstance() {
		if (instance == null) {
			instance = new ValueMapper();
		}
		return instance;
	}
	
	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
		
		int beatDuration = ((60 * 1000) / this.bpm);
		
		this.mappings.put(Value.Whole, new Long(beatDuration * 4));
		this.mappings.put(Value.WholeWithPoint, new Long(beatDuration * 4) + new Long(beatDuration) * 2);
		this.mappings.put(Value.Half, new Long(beatDuration * 2));
		this.mappings.put(Value.HalfWithPoint, new Long(beatDuration * 2) + new Long(beatDuration));
		this.mappings.put(Value.Quarter, new Long(beatDuration));
		this.mappings.put(Value.QuarterWithPoint, new Long(beatDuration) + new Long(beatDuration) / 2);
		this.mappings.put(Value.Eighth, new Long(beatDuration / 2));
		this.mappings.put(Value.EighthWithPoint, new Long(beatDuration / 2) + new Long(beatDuration / 4));
		this.mappings.put(Value.Sixteenth, new Long(beatDuration / 4));
		this.mappings.put(Value.SixteenthWithPoint, new Long(beatDuration / 4) + new Long(beatDuration / 8));
		this.mappings.put(Value.ThirtySecond, new Long(beatDuration / 8));
		this.mappings.put(Value.ThirtySecondWithPoint, new Long(beatDuration / 8) + new Long(beatDuration / 16));
		this.mappings.put(Value.SixtyFourth, new Long(beatDuration / 16));
		this.mappings.put(Value.SixtyFourthWithPoint, new Long(beatDuration / 16) + new Long(beatDuration / 32));
		this.mappings.put(Value.HundredTwentyEighth, new Long(beatDuration / 32));
		this.mappings.put(Value.HundredTwentyEighthWithPoint, new Long(beatDuration / 32) + new Long(beatDuration / 64));
		
		this.reverseMappings.put(new Long(beatDuration * 4), Value.Whole);
		this.reverseMappings.put(new Long(beatDuration * 4) + new Long(beatDuration) * 2, Value.WholeWithPoint);
		this.reverseMappings.put(new Long(beatDuration * 2), Value.Half);
		this.reverseMappings.put(new Long(beatDuration * 2) + new Long(beatDuration), Value.HalfWithPoint);
		this.reverseMappings.put(new Long(beatDuration), Value.Quarter);
		this.reverseMappings.put(new Long(beatDuration) + new Long(beatDuration / 2), Value.QuarterWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 2), Value.Eighth);
		this.reverseMappings.put(new Long(beatDuration / 2) + new Long(beatDuration / 4), Value.EighthWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 4), Value.Sixteenth);
		this.reverseMappings.put(new Long(beatDuration / 4) + new Long(beatDuration / 8), Value.SixteenthWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 8), Value.ThirtySecond);
		this.reverseMappings.put(new Long(beatDuration / 8) + new Long(beatDuration / 16), Value.ThirtySecondWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 16), Value.SixtyFourth);
		this.reverseMappings.put(new Long(beatDuration / 16) + new Long(beatDuration / 32), Value.SixtyFourthWithPoint);
		this.reverseMappings.put(new Long(beatDuration / 32), Value.HundredTwentyEighth);
		this.reverseMappings.put(new Long(beatDuration / 32) + new Long(beatDuration / 64), Value.HundredTwentyEighthWithPoint);
	}

	public Long getMapping(Value value) {
		return this.mappings.get(value);
	}

	public Value getReverseMapping(Long duration) {
		return this.reverseMappings.get(duration);
	}
	
	public Map<Value, Long> getMappings() {
		return this.mappings;
	}
}