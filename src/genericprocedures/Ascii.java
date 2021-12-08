package genericprocedures;

public class Ascii {
	
	public static int[] asciiStringValue (String tcStringToConvert) {
		int nStringLength 		= tcStringToConvert.length();
		int [] lnAsciiArray 	= new int[nStringLength];
		char charChar 			= ' ';
		int asciiValue 			= 0;
		
		for (int lencnt = 0; lencnt < nStringLength;lencnt++) {
			lnAsciiArray[lencnt] = asciiCharValue(tcStringToConvert.charAt(lencnt));
			
			// Makes capital and lowercase letters sort equally
			if (lnAsciiArray[lencnt] >= 97 && lnAsciiArray[lencnt] <= 122) {
				lnAsciiArray[lencnt] = lnAsciiArray[lencnt] - 32;
			}
		}
		
		return lnAsciiArray;
	}
	
	public static int asciiCharValue (char tcCharToConvert) {
		int lnAsciiInt			= 0;

		lnAsciiInt = (int) tcCharToConvert;
		
		return lnAsciiInt;
	}
	
	public static int asciiOneCharStringValue (String tcStringToConvert) {
		int lnAsciiInt			= 0;

		lnAsciiInt = (int) tcStringToConvert.charAt(1);
		
		return lnAsciiInt;
	}
}
