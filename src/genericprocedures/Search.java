package genericprocedures;

import java.util.ArrayList;

public class Search {
	
	// Searches a two dimension array list containing String values
	public static int searchArrayList(ArrayList<ArrayList<String>> talSearchedList, String tcSearchValue, int tiColumn) {
		
		int liRow 			= -1;
		int liListSize 		= talSearchedList.size();
		
		// If given 2D array list has no size, skip search
		if (liListSize != 0) {
			for (int findx = 0; findx < liListSize; findx++) {			///talSearchedList.get(0).size()
				if (talSearchedList.get(findx).get(tiColumn).equals(tcSearchValue)){
					liRow = findx;
					break;
				}
			}	
		}
		
		return liRow;
	}
	
	// Searches a one dimension array list containing String values
	public static int searchArrayList(ArrayList<String> talSearchedList, String tcSearchValue) {
		
		int liRow = -1;
		
		// If given array list has no size, skip search
		if (talSearchedList.size() != 0) {
			for (int findx = 0; findx < talSearchedList.size(); findx++) {			///talSearchedList.get(0).size()
				if (talSearchedList.get(findx).equals(tcSearchValue)){
					liRow = findx;
					break;
				}
			}	
		}
		
		return liRow;
	}
	
	// Searches for the Highest number in a column, mostly useful for setting up an index
	public static int searchHighestValue (ArrayList<ArrayList<String>> tcArray, int tiColumn) {
		int nRetHighValue			= 0;
		int liLowestRowSize 		= tcArray.size();
		
		int liLowestRow = 0;
		if (liLowestRowSize != 1) {
			liLowestRow = Integer.valueOf(tcArray.get(liLowestRowSize-1).get(tiColumn));
			nRetHighValue = liLowestRow+1;
		}
		else {
			nRetHighValue = 1;
		}
		
		return nRetHighValue;
	}
	
	
	// Array Search
	
	// Searches a two dimension array list
		public static int searchArray(String[][] talSearchedArray, String tcSearchValue, int tiColumn) {
			
			int liRow 			= -1;
			int liListSize 		= talSearchedArray.length;
			
			// If given 2D array has no size, skip search
			if (liListSize != 0) {
				for (int findx = 0; findx < liListSize; findx++) {			///talSearchedList.get(0).size()
					if (talSearchedArray[findx][tiColumn].equals(tcSearchValue)){
						liRow = findx;
						break;
					}
				}	
			}
			
			return liRow;
		}
		
		// Searches a one dimension array
		public static int searchArray(String[] talSearchedArray, String tcSearchValue) {
			
			int liRow = -1;
			int liListSize 		= talSearchedArray.length;
			
			// If given array has no size, skip search
			if (liListSize != 0) {
				for (int findx = 0; findx < liListSize; findx++) {
					if (talSearchedArray[findx].equals(tcSearchValue)){
						liRow = findx;
						break;
					}
				}	
			}
			
			return liRow;
		}
}
