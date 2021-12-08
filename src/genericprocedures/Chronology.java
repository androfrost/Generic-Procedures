package genericprocedures;

public class Chronology {
	public static String completeDateInteger(int tnDay, int tnMonth, int tnYear, int tnDateType) {
		String lcDate = "";
		if (tnDateType == 0) {
			lcDate = StringHandling.padl(String.valueOf(tnDay),2,"0") + "/" + StringHandling.padl(String.valueOf(tnMonth),2,"0");
		} else {
			lcDate = StringHandling.padl(String.valueOf(tnMonth),2,"0") + "/" + StringHandling.padl(String.valueOf(tnDay),2,"0");
		}
		lcDate = lcDate + "/" + String.valueOf(tnYear);
		
		System.out.println(lcDate);
		
		return lcDate;
	}
	
	public static String completeDateString(String tcDay, String tcMonth, String tcYear, int tnDateType) {
		String lcDate = "";
		if (tnDateType == 0) {
			lcDate = StringHandling.padl(tcDay,2,"0") + "/" + StringHandling.padl(tcMonth,2,"0");
		} else {
			lcDate = StringHandling.padl(tcMonth,2,"0") + "/" + StringHandling.padl(tcDay,2,"0");
		}
		lcDate = lcDate + "/" + tcYear;
		
		System.out.println(lcDate);
		
		return lcDate;
	}
	
	public static boolean isDateFormat(String lcDateToTest) {
		boolean lIsDate = false, lIsMonth = false, lIsDay = false, lIsYear = false, lIsFormat = false;
		int liMonth, liDay, liYear;
		String lcSlash1, lcSlash2;
		int lnSlash1 = 0, lnSlash2 = 0;
		String tcDateToTest = lcDateToTest;
		
		lnSlash1 = StringHandling.charOccurrence(tcDateToTest, "/", 1);
		lnSlash2 = StringHandling.charOccurrence(tcDateToTest, "/", 2);
		if (lnSlash1 > 0 && lnSlash2 > 0) {
			liMonth = Integer.valueOf(Character.toString(tcDateToTest.charAt(0))+Character.toString(tcDateToTest.charAt(1)));
			lcSlash1 = Character.toString(tcDateToTest.charAt(2));
			liDay = Integer.valueOf(Character.toString(tcDateToTest.charAt(3))+Character.toString(tcDateToTest.charAt(4)));
			lcSlash2 = Character.toString(tcDateToTest.charAt(5));
			liYear = Integer.valueOf(Character.toString(tcDateToTest.charAt(6))+Character.toString(tcDateToTest.charAt(7))+Character.toString(tcDateToTest.charAt(8))+Character.toString(tcDateToTest.charAt(9)));
			
			if ((lcSlash1.contentEquals("/")) && (lcSlash2.contentEquals("/"))){
				lIsFormat = true;
			}
			
			lIsMonth 	= isMonth(liMonth);
			lIsDay		= isDay(liDay, liMonth, liYear);
			lIsYear		= isYear(liYear);
			
			if (lIsMonth && lIsDay && lIsYear && lIsFormat) {
				lIsDate = true;
			}
		}
		return lIsDate;
	}
	
	public static boolean isMonth(int tiMonth) {
		boolean isMonth = false;
		
		if (tiMonth >= 1 && tiMonth <= 12) {
			isMonth = true;
		}
		
		return isMonth;
	}
	
	public static boolean isDay(int tiDay, int tiMonth, int tiYear) {
		boolean isDay = false;
		
		if ((tiMonth == 1 || tiMonth == 3 || tiMonth == 5 || tiMonth == 7 || tiMonth == 10 || tiMonth == 12) &&
				tiDay <= 31) {
			isDay = true;
		}
		if ((tiMonth == 4 || tiMonth == 6 || tiMonth == 8 || tiMonth == 9 || tiMonth == 11) && tiDay <= 30) {
			isDay = true;
		}
		if (tiMonth == 2 && tiDay <= 29) {
			isDay = true;
		}
		
		return isDay;
	}
	
	// Determines if a given  value is a legal year, although any number should be a legal year so just return true
	public static boolean isYear(int tiYear) {
		boolean isYear = false;
		
		isYear = true;
		
		return isYear;
	}
}
	