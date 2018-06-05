package utils;

import instrument.Piano;

public class KeyboardMapper {

	public static int map(int octave, int keyCode) {
		int result = -1; 
		
		Piano piano = new Piano();
		
		int offset = 12 * octave;
		switch (keyCode) {
			case 9: 
				result = offset + 0;
				break;
			case 49:
				result = offset + 1;
				break;
			case 113:
				result = offset + 2;
				break;
			case 50:
				result = offset + 3;
				break;
			case 119:
				result = offset + 4;
				break;
			case 101:
				result = offset + 5;
				break;
			case 52:
				result = offset + 6;
				break;
			case 114:
				result = offset + 7;
				break;
			case 53:
				result = offset + 8;
				break;
			case 116:
				result = offset + 9;
				break;
			case 54:
				result = offset + 10;
				break;
			case 121:
				result = offset + 11;
				break;
			case 117:
				result = offset + 12;
				break;
			case 56:
				result = offset + 13;
				break;
			case 105:
				result = offset + 14;
				break;
			case 57:
				result = offset + 15;
				break;
			case 111:
				result = offset + 16;
				break;
			case 112:
				result = offset + 17;
				break;
			case 39:
				result = offset + 18;
				break;
			case 0:
				result = offset + 19;
				break;
			case 161:
				result = offset + 20;
				break;
			case 43:
				result = offset + 21;
				break;
				
			case 60:
				result = offset + 24;
				break;
			case 97:
				result = offset + 25;
				break;
			case 122:
				result = offset + 26;
				break;
			case 115:
				result = offset + 27;
				break;
			case 120:
				result = offset + 28;
				break;
			case 99:
				result = offset + 29;
				break;
			case 102:
				result = offset + 30;
				break;
			case 118:
				result = offset + 31;
				break;
			case 103:
				result = offset + 32;
				break;
			case 98:
				result = offset + 33;
				break;
			case 104:
				result = offset + 34;
				break;
			case 110:
				result = offset + 35;
				break;
			case 109:
				result = offset + 36;
				break;
			case 107:
				result = offset + 37;
				break;
			case 44:
				result = offset + 38;
				break;
			case 108:
				result = offset + 39;
				break;
			case 46:
				result = offset + 40;
				break;
			case 45:
				result = offset + 41;
				break;
			
			default:
				break;
		}
		
		result = (result >= 0 ? piano.getTessitura().getMin() + result : -1);
		
		return result;
	}
}