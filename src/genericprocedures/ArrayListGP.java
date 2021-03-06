package genericprocedures;

import java.util.ArrayList;

//import org.graalvm.compiler.nodes.java.ArrayLengthNode;

import fileprocessor.delimitedFileProcessor;

public class ArrayListGP {

	public static String[] dropDownAL(String tcDropDownPathFile, int tiSelectedIndex, int tiLoadID) throws Exception {

		ArrayList<ArrayList<String>> importedDropDown = delimitedFileProcessor.delimitedFileReader(tcDropDownPathFile, ",", "\"");
		String[] lcTempDropList = new String[importedDropDown.get(tiLoadID).size() - 1];
		String[] lcDropList;
		// Create array for drop down using a given row from an ArrayList
		int testsize = importedDropDown.get(tiLoadID).size() - 1;
		int numLoop = importedDropDown.get(tiLoadID).size() - 1;
		for (int dropx = 0; dropx < testsize; dropx++) {
			if (importedDropDown.get(tiLoadID).get(dropx + 1).length() > 1) {
				lcTempDropList[dropx] = importedDropDown.get(tiLoadID).get(dropx + 1);
			} else {
				numLoop = numLoop - 1;
			}
		}
		lcDropList = new String[numLoop];
		// Populate with non blank drop down options only
		for (int dropy = 0; dropy < numLoop; dropy++) {
			lcDropList[dropy] = lcTempDropList[dropy];
		}
		
		return lcDropList;
	}
	
	/*
	 * Calls displayLineAL to set up a display of a file with default start row and end row
	 * tcDisplayPathFile		- File to have value displayed
	 * tiLoadID					- The order to display the fields in file
	 */
	public static String[] displayLinesAL(String tcDisplayPathFile, int[] tiLoadID) throws Exception {
		String[] lcDisplayList;		// = new String[1]; 
		
		lcDisplayList = displayLinesAL(tcDisplayPathFile, tiLoadID, 1, -1);
		
		return lcDisplayList;
	}
	
	/*
	 * Calls displayLineAL to set up a display of a file with default start row and end row
	 * talImportedDisplayFile	- ArrayList to have value displayed
	 * tiLoadID					- The order to display the fields in file
	 */
	public static String[] displayLinesAL(ArrayList<ArrayList<String>> talImportedDisplayFile, int[] tiLoadID) throws Exception {
		String[] lcDisplayList;
		
		lcDisplayList = displayLinesAL(talImportedDisplayFile, tiLoadID, 1, -1);
		
		return lcDisplayList;
	}
	
	/*
	 * Create an Array from a file of the data in a format for displaying on screen
	 * tcDisplayPathFile	- File to have value displayed
	 * tiLoadID				- The order to display the fields in file
	 * tiStartRow			- The row of the file to start displaying the data from
	 * tiEndRow				- The row of the file to end displaying the data
	 */
	public static String[] displayLinesAL(String tcDisplayPathFile, int[] tiLoadID, int tiStartRow, int tiEndRow) throws Exception {
		ArrayList<ArrayList<String>> importedDisplayFile = delimitedFileProcessor.delimitedFileReader(tcDisplayPathFile, ",", "\"");
		int liDisplayFileSize 	= importedDisplayFile.size();
		if (tiEndRow < tiStartRow) {
			tiEndRow = liDisplayFileSize;
		}
		
		String[] lcDisplayList = new String[tiEndRow-tiStartRow];
		lcDisplayList = displayLinesAL(importedDisplayFile, tiLoadID, 1, -1);
		
		return lcDisplayList;
	}
	
	/*
	 * Create an Array from an ArrayList of the data in a format for displaying on screen
	 * talImportedDisplayFile	- ArrayList of the values to display
	 * tiLoadID					- The order to display the fields in file
	 * tiStartRow				- The row of the file to start displaying the data from
	 * tiEndRow					- The row of the file to end displaying the data
	 */
	public static String[] displayLinesAL(ArrayList<ArrayList<String>> talImportedDisplayFile, int[] tiLoadID, int tiStartRow, int tiEndRow) throws Exception {

		int liDisplayFileSize 	= talImportedDisplayFile.size();
		int liLoadIDLength 		= tiLoadID.length;
		if (tiEndRow < tiStartRow) {
			tiEndRow = liDisplayFileSize;
		}
		
		String[] lcDisplayList = new String[tiEndRow-tiStartRow];
		for (int nDisplayLine = tiStartRow; nDisplayLine < tiEndRow; nDisplayLine++) {
			for (int nload = 0; nload < liLoadIDLength; nload++) {
				int tiLID = tiLoadID[nload];
				int longStringLen = longestArrayListStringLength(talImportedDisplayFile,tiLID);
				if (nload < talImportedDisplayFile.get(nDisplayLine).size()) {
					if (lcDisplayList[nDisplayLine-tiStartRow] == null)
						lcDisplayList[nDisplayLine-tiStartRow] = StringHandling.padr(talImportedDisplayFile.get(nDisplayLine).get(tiLID),longStringLen," ");
					else
						lcDisplayList[nDisplayLine-tiStartRow] = lcDisplayList[nDisplayLine-tiStartRow] + " " + StringHandling.padr(talImportedDisplayFile.get(nDisplayLine).get(tiLID),longStringLen," ");
				} else 
				{
					lcDisplayList[nDisplayLine-tiStartRow] = lcDisplayList[nDisplayLine-tiStartRow] + " " + StringHandling.padr("",longStringLen," ");
				}
			}
		}
		
		return lcDisplayList;
	}
	
	public static ArrayList<ArrayList<String>> appendTwoALIncreaseID (ArrayList<ArrayList<String>> satFirst, ArrayList<ArrayList<String>> satSecond, int[] naIDList){
		ArrayList<ArrayList<String>> salCombined 	= satFirst;	// Set start of combine to first passed array
		ArrayList<ArrayList<String>> salSecond 		= satSecond;
		int lnIDArrayLength 						= naIDList.length;
		int lnCombinedALLength 						= salCombined.size();
		int lnSecondALLength 						= salSecond.size();
	
		for (int ALx = 0; ALx < lnSecondALLength; ALx++) {
			
			for (int aIDx = 0; aIDx < lnIDArrayLength; aIDx++) {
				String lcTestString = salSecond.get(ALx).get(aIDx);
				if (!lcTestString.trim().isEmpty())
					salSecond.get(ALx).set(aIDx,  salSecond.get(ALx).get(aIDx) + lnCombinedALLength);
			}
		}
		
		salCombined.addAll(salSecond);
		
		return salCombined;
	}
	
	public static int longestArrayListStringLength (ArrayList<ArrayList<String>> salScanArrayList, int tnColumn) {
		int lcStringLength = 0;
		
		lcStringLength = longestArrayListStringLength(salScanArrayList, tnColumn, 1);
		
		return lcStringLength;
	}
	
	public static int longestArrayListStringLength (ArrayList<ArrayList<String>> salScanArrayList, int tnColumn, int startRow) {
		int lcStringLength = 0;
		int arrayStringLength = 0;
		
		for (int x = startRow; x < salScanArrayList.size(); x++) {
			if (salScanArrayList.get(x).size() > startRow) {
				arrayStringLength = salScanArrayList.get(x).get(tnColumn).trim().length();
				if (lcStringLength < arrayStringLength) {
					lcStringLength = arrayStringLength;
				}
			}
		}
		
		return lcStringLength;
		
	}
}
