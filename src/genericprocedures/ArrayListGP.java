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
	
	public static String[] displayLinesALStandard(String tcDisplayPathFile, int[] tiLoadID) throws Exception {
		String[] lcDisplayList;		// = new String[1]; 
		
		lcDisplayList = displayLinesAL(tcDisplayPathFile, tiLoadID, 1);
		
		return lcDisplayList;
	}	
	
	public static String[] displayLinesAL(String tcDisplayPathFile, int[] tiLoadID, int tiStartRow) throws Exception {
		ArrayList<ArrayList<String>> importedDisplayFile = delimitedFileProcessor.delimitedFileReader(tcDisplayPathFile, ",", "\"");
		
		int liDisplayFileSize 	= importedDisplayFile.size();
		int liLoadIDLength 		= tiLoadID.length;
		
		String[] lcDisplayList = new String[liDisplayFileSize-tiStartRow];
		for (int nDisplayLine = tiStartRow; nDisplayLine < liDisplayFileSize; nDisplayLine++) {
			for (int nload = 0; nload < liLoadIDLength; nload++) {
				int tiLID = tiLoadID[nload];
				if (lcDisplayList[nDisplayLine-tiStartRow] == null)
					lcDisplayList[nDisplayLine-tiStartRow] = importedDisplayFile.get(nDisplayLine).get(tiLID);
				else
					lcDisplayList[nDisplayLine-tiStartRow] = lcDisplayList[nDisplayLine-tiStartRow] + " " + importedDisplayFile.get(nDisplayLine).get(tiLID);
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
}
