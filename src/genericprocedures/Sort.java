package genericprocedures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Sort {
	
	/*
	Sorting for a one-dimensional ArrayList <String> based on ascii values of characters in the string
	tcArrayList	- ArrayList passed to be sorted
	tiCol		- Column to be sorted on, should contain numeric values in String format
	 */
	public static ArrayList<ArrayList<String>> arrayListAsciiSort (ArrayList<ArrayList<String>> tcArrayList, int tiCol){
    	ArrayList<ArrayList<String>> lcArrayList 			= tcArrayList;
    	ArrayList<ArrayList<String>> lcSortArrayList 		= new ArrayList<ArrayList<String>>();
    	ArrayList<ArrayList<Integer>> lnAscii 				= new ArrayList<ArrayList<Integer>>();
    	ArrayList<ArrayList<Integer>> lnAsciiSorted			= new ArrayList<ArrayList<Integer>>();
    	String testString;
    	char testChar;
    	boolean isLineSort = true;
    	
    	if (lcArrayList.isEmpty())
    		return lcArrayList;
    	
    	for (int iRow = 0; iRow < lcArrayList.size(); iRow++) {
    		lnAscii.add(new ArrayList<Integer>());
    		for (int iCol = 0; iCol < lcArrayList.get(iRow).get(tiCol).length(); iCol++) {
    			testString = lcArrayList.get(iRow).get(tiCol);
    			testChar = testString.charAt(iCol);
    			lnAscii.get(iRow).add(iCol, Ascii.asciiCharValue(testChar));
    		}
    	}
    	
    	for (int iRow = 0; iRow < tcArrayList.size(); iRow++) {
    		lcSortArrayList.add(new ArrayList<String>());
    	}
    	
    	int lnSizeA, lnSizeB, lnShortest;
    	int lnAsciiA, lnAsciiB, lnAsciiHighRow = 0;
    	while(isLineSort) {	
    		for (int iRowPass = 0; iRowPass < lnAscii.size() - 1; iRowPass++) {
    			isLineSort = false;
    			lnSizeA = lnAscii.get(iRowPass).size();
    			lnSizeB = lnAscii.get(iRowPass+1).size();
    			if (lnSizeA <= lnSizeB) {
    				lnShortest = lnSizeA;
    			} else {
    				lnShortest = lnSizeB;
    			}
    			
    			int liStartArrraySize = lnAscii.size();
    			int liSetArrayLocation = liStartArrraySize-1, iRowPassTest = iRowPass+1;
    			for (int iColPass = 0; iColPass < liStartArrraySize; iColPass++) {
    				
    				int nSize = lnAscii.size()-1;
    				for (int testx = 0; testx < nSize; testx++) {
    					iRowPass = bubbleSortLoop (lnAscii, iRowPass, iRowPassTest, 0, lnShortest);
    					iRowPassTest = iRowPassTest + 1;
    				}
    				

    				if (liSetArrayLocation >= 0) { 
    					lcSortArrayList.get(liSetArrayLocation).add(lcArrayList.get(iRowPass).get(0));
    					lcArrayList.remove(iRowPass);
    					lnAscii.remove(iRowPass);
	    				liSetArrayLocation = liSetArrayLocation - 1;
    				}
    				iRowPass = 0;
    				iRowPassTest = 1;
    			}
    		}
    	}
    	
    	
    	if (lcSortArrayList.size() > 0)
    		lcArrayList = lcSortArrayList;
    	
    	return lcArrayList;
    }
	
	public static int bubbleSortLoop (ArrayList<ArrayList<Integer>> tiArrayToSort, int tiRowA, int tiRowB, int tiColumn, int tiShortest) {
		int lnSizeA = tiArrayToSort.get(tiRowA).size(), lnSizeB = tiArrayToSort.get(tiRowB).size();
		int liRowNext, liColumnNext;
    	int lnAsciiA, lnAsciiB, lnAsciiHighRow = tiRowA;
    	int liShortest;	// = tiShortest;
    	if (lnSizeA <= lnSizeB) {
			liShortest = lnSizeA;
		} else {
			liShortest = lnSizeB;
		}    	
    	
		lnAsciiA = tiArrayToSort.get(tiRowA).get(tiColumn);
		lnAsciiB = tiArrayToSort.get(tiRowB).get(tiColumn);
		
		// Check if earlier Row is the highest
		if (lnAsciiA > lnAsciiB) {
			lnAsciiHighRow = tiRowA;
		} else {
			// Checks if later Row is highest
			if (lnAsciiA < lnAsciiB) {
				lnAsciiHighRow = tiRowB;
				
			} else {
				// If Rows are equal move to next ascii value test
				liColumnNext = tiColumn + 1;
				if (liColumnNext < liShortest) {
					lnAsciiHighRow = bubbleSortLoop(tiArrayToSort, tiRowA, tiRowB, liColumnNext, liShortest);
				} else {
					// If same length, keep current High Row value
					if (lnSizeA != lnSizeB) {
						// If the strings are the same to this point and one of the rows are longer than the other 
						// continue with that as the one to test against
						if (lnSizeA > lnSizeB) {
							lnAsciiHighRow = tiRowA;
						} else {
							lnAsciiHighRow = tiRowB;
						}
					} else {
						//lnAsciiHighRow = tiRowB;
					}
				}					
			}
		}

		return lnAsciiHighRow;
	}
	
	/*
	Sorting for a two-dimensional ArrayList <String>
	tcArrayList	- ArrayList passed to be sorted
	tiCol		- Column to be sorted on, should contain numeric values in String format
	 */
	public static ArrayList<ArrayList<String>> arrayListSort (ArrayList<ArrayList<String>> tcArrayList, int tiCol){
		
		ArrayList<ArrayList<String>> lcReturnList 	= new ArrayList<ArrayList<String>>();
		ArrayList<Integer> lnColumn 				= new ArrayList<Integer>();
		ArrayList<String> lcColumn 					= new ArrayList<String>();
		// Takes string column made up of numerics and puts them in array to be ordered by numeric value
		for (int nColumnNo = 1; nColumnNo < tcArrayList.size(); nColumnNo++) {
			lnColumn.add(Integer.valueOf(tcArrayList.get(nColumnNo).get(tiCol)));
		}
		Collections.sort(lnColumn);
		// Puts sorted numeric values in an array in same order with string equivalent
		for (int nColumnNo = 0; nColumnNo < lnColumn.size(); nColumnNo++) {
			lcColumn.add(String.valueOf(lnColumn.get(nColumnNo)));
			lcReturnList.add(new ArrayList<String>());
		}
		lcReturnList.add(new ArrayList<String>());
		int liFoundRow;
		for (int nRowX = 0; nRowX < tcArrayList.size(); nRowX++) {
			liFoundRow = -1;
			
			if (nRowX == 39) {
				int test = 1;
			}
			
			for (int nColY = 0; nColY < tcArrayList.get(nRowX).size(); nColY++) {

				// First row populates with headings
				if (nRowX == 0) {
					lcReturnList.get(nRowX).add(nColY, tcArrayList.get(nRowX).get(nColY));
				} else {
					// Sets the column we are ordering to the value of the row in the proper order 
					if (nColY == tiCol) {
						lcReturnList.get(nRowX).add(nColY,String.valueOf(lcColumn.get(nRowX-1)));
					} else {
						// Searches the new ArrayList for where ID's match to get ArrayList<ArrayList> sorted
						liFoundRow = Search.searchArrayList(lcColumn, tcArrayList.get(nRowX).get(0));
						if (liFoundRow >= 0) {
							lcReturnList.get(nRowX).add(nColY, tcArrayList.get(liFoundRow+1).get(nColY));
						}
					}
				}
			}
		}
		
		return lcReturnList;
	}
	
	public static String [] sortArray (String [] tcaArrayToSort){
		String [] lcaArrayToSort = tcaArrayToSort;
		Arrays.sort(lcaArrayToSort);
		
		return tcaArrayToSort;
	}
	
	public static int [] sortArray (int [] tnaArrayToSort){
		int [] lnaArrayToSort = tnaArrayToSort;
		Arrays.sort(lnaArrayToSort);
		
		return lnaArrayToSort;
	}
}
