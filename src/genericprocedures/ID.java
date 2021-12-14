package genericprocedures;

import java.io.IOException;
import java.util.ArrayList;

import fileprocessor.GeneralFileAction;
import fileprocessor.delimitedFileProcessor;

public class ID {
	
	static String lcIDPath;
	static String lcIDFile;
	static String lcIDExt;
	String lcIDStartLine;
	public static ArrayList<ArrayList<String>> lacIDFile = new ArrayList<ArrayList<String>>();
	
	public ID() {
		
	}
	
	// Constructor to set up ID file information
	public ID (String tcIDPath, String tcIDFile, String tcIDExt, String tcIDStartLine) throws Exception{
		lcIDPath = tcIDPath;
		lcIDFile = tcIDFile;
		lcIDExt = tcIDExt;
		lcIDStartLine = tcIDStartLine;
		
		try {
			setupIDFile (lcIDPath, lcIDFile, lcIDExt, lcIDStartLine);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	// Receives current ID and returns the next ID that is one higher than the last
	// tiPreviousID		- Previous ID to determine next ID value
	public static int nextID(int tiPreviousID) {
		int liNextID = 0;
		
		liNextID = tiPreviousID + 1;
		
		return liNextID;
	}
	
	// Determines the next ID from the processed file in the ArrayList and updates it to the file
	// tasIDArray		- ArrayList holding records where the given Column will hold the ID value
	// tiIDRow			- Number designating the existing row where you will be adding the ID value to
	// tiIDColumn		- Number designating the column where the ID will be updated to.
	public static ArrayList<ArrayList<String>> nextIDUpdate (ArrayList<ArrayList<String>> tasIDArray, int tiIDRow, int tiIDColumn) throws Exception {
		ArrayList<ArrayList<String>> lasReturn = new ArrayList<ArrayList<String>>();
		lasReturn = tasIDArray;
		int liIDColumn = tiIDColumn;
		int liNextID;
		String lcNextID;
		String lcIDFileComplete = lcIDPath+lcIDFile+lcIDExt;
		
		liNextID = nextID(Integer.parseInt(tasIDArray.get(tiIDRow).get(tiIDColumn)));
		lcNextID = String.valueOf(liNextID);
		
		lasReturn.get(tiIDRow).set(tiIDColumn, String.valueOf(lcNextID));
		
		delimitedFileProcessor.commaDelimitedFileWriter(lcIDFileComplete, lasReturn);
		
		return lasReturn;
	}
	
	public static ArrayList<ArrayList<String>> nextIDUpdateStandard (ArrayList<ArrayList<String>> tasIDArray, int tiIDColumn) throws Exception {
		ArrayList<ArrayList<String>> lasReturn = new ArrayList<ArrayList<String>>();
		
		lasReturn = nextIDUpdate(tasIDArray, 1, tiIDColumn);
		
		return lasReturn;
	}
	
	public static String updateID (int tiIDColumn) throws Exception {
		String lcNewID = "0";
		
		lcNewID = lacIDFile.get(1).get(tiIDColumn);
		lacIDFile = nextIDUpdateStandard(lacIDFile, tiIDColumn);
		
		return lcNewID;
	}
	
	// Setup an ID file based on basic information given from passed variables
	public void setupIDFile (String tcIDPath, String tcIDFile, String tcIDExt, String tcIDStartLine) throws Exception {
		
		String lcIDFileComplete = tcIDPath+tcIDFile+tcIDExt;
		ArrayList<ArrayList<String>> lasID = new ArrayList<ArrayList<String>>();
		int liIDColumns;
		
		GeneralFileAction.createDirectory(tcIDPath);					// Create ID File directory if it does not exist
		GeneralFileAction.createFile(lcIDFileComplete,tcIDStartLine);	// Create ID File if it does not exist
		
		lasID = delimitedFileProcessor.commaDelimitedFileReader(lcIDFileComplete);
		liIDColumns = lasID.get(0).size();
		if (liIDColumns > 0) {
			if (lasID.size() == 1)
				lasID.add(new ArrayList<String>());
		
			for (int iIDRow = 0; iIDRow < liIDColumns; iIDRow++) {
				if (lasID.get(1).size() <= iIDRow) {
					lasID.get(1).add("1");
				}
				if (lasID.get(1).get(iIDRow).equals("")) {
					lasID.get(1).set(iIDRow,"1");
				}
			}
			delimitedFileProcessor.commaDelimitedFileWriter(lcIDFileComplete, lasID);
		}
		setIDArrayList(delimitedFileProcessor.commaDelimitedFileReader(lcIDFileComplete));
	}
	
	// Finds ID in given column and returns the value
	public static String getID (ArrayList<ArrayList<String>> tasGivenIDArray, int tiIDRow, int tiIDColumn) throws Exception {
		String lcIDReturn;
		
		lcIDReturn = tasGivenIDArray.get(tiIDRow).get(tiIDColumn);
		return lcIDReturn;
	}
	
	// Calls getID to get the ID of a certain column
	// Calls nextIDUpdateStandard to update the next ID based ond the standard row of 1
	// Calls setArrayList to update the ArrayList holding the IDs
	public static String getIDUpdate(ArrayList<ArrayList<String>> tasGivenIDArray, int tiIDColumn) throws Exception {
		
		String lcIDReturn;
		lcIDReturn = getID(tasGivenIDArray, 1, tiIDColumn);
		tasGivenIDArray = nextIDUpdateStandard(tasGivenIDArray, tiIDColumn);
		setIDArrayList(tasGivenIDArray);
		
		return lcIDReturn;
	}
	
	// Sets the public ID ArrayList value with a given value
	public static void setIDArrayList(ArrayList<ArrayList<String>> tasGivenIDArray){
		
		lacIDFile = tasGivenIDArray;
	}
}
