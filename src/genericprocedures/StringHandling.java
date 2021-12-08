package genericprocedures;

public class StringHandling {

	public static String padl(String tcString, int tcLength, String tcPadChar) {
		String lcString			 = tcString;				// Setting first part of padded return string
		int lcStrLength			 = tcString.length();		// Length of given String
		int lcRemainingLength	 = tcLength - lcStrLength;	
		
		for (int appendNumber = 0; appendNumber < lcRemainingLength; appendNumber++) {
			lcString = tcPadChar + lcString;
		}
		
		return lcString;
	}
	
	public static String padr(String tcString, int tcLength, String tcPadChar) {
		String lcString			 = tcString;				// Setting first part of padded return string
		int lcStrLength			 = tcString.length();		// Length of given String
		int lcRemainingLength	 = tcLength - lcStrLength;	
		
		for (int appendNumber = 0; appendNumber < lcRemainingLength; appendNumber++) {
			lcString = lcString + tcPadChar;
		}
		
		return lcString;
	}
	
	// Replaces all occurences of a String in another String with a replacement String value
	public static String strtran(String tcString, String tcChar, String tcReplacement) {
		String lcReturn			="";
		int lcStrLength			= tcString.length();
		
		for (int charNum = 0; charNum < lcStrLength; charNum++) {
			if (!tcChar.contentEquals(Character.toString(tcString.charAt(charNum)))) {
				lcReturn = lcReturn + tcString.charAt(charNum);
			} else {
				lcReturn = lcReturn + tcReplacement;
			}
		}
		
		return lcReturn;
	}
	
	public static int charOccurrence(String tcString, String tcChar, int tiOccurrence) {
		int liReturn 			= 0;
		int tcCharCount			= 0;
		int lcStrLength			= tcString.length();
		
		for (int charNum = 0; charNum < lcStrLength; charNum++) {
			if (tcChar.contentEquals(Character.toString(tcString.charAt(charNum)))) {
				tcCharCount++;
			}
			if (tiOccurrence == tcCharCount) {
				liReturn = charNum;
				break;
			}
		}
		
		return liReturn;
	}
	
	// Function to return a specific value when blank
	// If given a string that is blank, return a "0" instead
	public static String setStringValue(String tcTestString) {
		// String lcReturn = tcTestString.strip();
		//if (tcTestString.contentEquals(" ") || tcTestString.contentEquals("")) {
		//	lcReturn = "0";
		//}
		String lcReturn = tcTestString;
		lcReturn = setStringValueSetMin(lcReturn, "0");
		return lcReturn;
	}

	// If given a string that is blank, return a tcMin value instead
	public static String setStringValueSetMin(String tcTestString, String tcMin) {
		String lcReturn = tcTestString.strip();
		if (tcTestString.equals(" ") || tcTestString.equals("")) {
			lcReturn = tcMin;
		}
		return lcReturn;
	}
	
}
