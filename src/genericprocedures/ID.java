package genericprocedures;

import java.io.IOException;
import java.util.ArrayList;

import fileprocessor.GeneralFileAction;
import fileprocessor.delimitedFileProcessor;

public class ID {
	
	private static String lcIDPath			= "C:/JavaProjectData/ID/";
	private static String lcIDFile;
	private static String lcIDExt			= ".csv";
	private static String lcIDStartLine;
	private static ArrayList<ArrayList<String>> lacIDFile = new ArrayList<ArrayList<String>>();
	
	public ID() {
		
	}
	
	// Constructor to set up ID file information
	// tcIDPath			- Path of ID file
	// tcIDFile 		- ID file name
	// tcIDExt 			- ID file extension
	// tcIDStartLine 	- Start Line to setup fields of the ID file
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
	// tiAddition		- Amount to add to the Previous ID value to get the new ID
	public static int nextID(int tiPreviousID, int tiAddition) {
		int liNextID = 0;
		
		liNextID = tiPreviousID + tiAddition;
		
		return liNextID;
	}
	
	public static int nextIDStandard(int tiPreviousID) {
		int liNextID = 0;
		int liPreviousID = tiPreviousID;
		
		liNextID = nextID(liPreviousID, 1);
		
		return liNextID;
	}
	
	
	// Determines the next ID from the processed file in the ArrayList and updates it to the file
	// tasIDArray		- ArrayList holding records where the given Column will hold the ID value
	// tiIDRow			- Number designating the existing row where you will be adding the ID value to
	// tiIDColumn		- Number designating the column where the ID will be updated to.
	public static ArrayList<ArrayList<String>> nextIDUpdate (ArrayList<ArrayList<String>> tasIDArray, int tiIDRow, int tiIDColumn, int tiAddition) throws Exception {
		ArrayList<ArrayList<String>> lasReturn = new ArrayList<ArrayList<String>>();
		lasReturn = tasIDArray;
		int liIDColumn = tiIDColumn;
		int liAddition = tiAddition;
		int liNextID;
		String lcNextID;
		String lcIDFileComplete = lcIDPath+lcIDFile+lcIDExt;
		
		liNextID = nextID(Integer.parseInt(tasIDArray.get(tiIDRow).get(liIDColumn)), liAddition);
		lcNextID = String.valueOf(liNextID);
		
		lasReturn.get(tiIDRow).set(tiIDColumn, String.valueOf(lcNextID));
		
		delimitedFileProcessor.commaDelimitedFileWriter(lcIDFileComplete, lasReturn);
		
		return lasReturn;
	}
	
	public static ArrayList<ArrayList<String>> nextIDUpdateStandard (ArrayList<ArrayList<String>> tasIDArray, int tiIDColumn) throws Exception {
		ArrayList<ArrayList<String>> lasReturn = new ArrayList<ArrayList<String>>();
		
		lasReturn = nextIDUpdate(tasIDArray, 1, tiIDColumn, 1);
		
		return lasReturn;
	}
	
	// Updates the ID in the ID tracking file using the standard update of plus 1 in the second row always
	// tiIDColumn		- Column where to update the ID value
	public static String updateIDTrackingFile (int tiIDColumn) throws Exception {
		String lcNewID = "0";
		
		lcNewID = lacIDFile.get(1).get(tiIDColumn);
		lacIDFile = nextIDUpdateStandard(lacIDFile, tiIDColumn);
		
		return lcNewID;
	}
	
	// Setup an ID tracking file based on basic information given from passed variables
	// tcIDPath	- 		Path of ID file
	// tcIDFile -		ID file name
	// tcIDExt - 		ID file extension
	// tcIDStartLine - 	Start Line to setup fields of the ID file
	public void setupIDFile (String tcIDPath, String tcIDFile, String tcIDExt, String tcIDStartLine) throws Exception {
		
		setIDPath(tcIDPath);
		setIDFile(tcIDFile);
		setIDExt(tcIDExt);
		
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
		
		String lcIDReturn = "-1";
		lcIDReturn = getID(tasGivenIDArray, 1, tiIDColumn);
		tasGivenIDArray = nextIDUpdateStandard(tasGivenIDArray, tiIDColumn);
		setIDArrayList(tasGivenIDArray);
		
		return lcIDReturn;
	}
	
	// Updates the ID based off the field column number
	public String getIDUpdate (int tiIDColumn) throws Exception {
		String lcIDReturn = "-1";
		int liIDColumn = tiIDColumn;
		
		if (liIDColumn > 0)
			lcIDReturn = getIDUpdate(lacIDFile, liIDColumn);
		
		return lcIDReturn;
	}
	
	// Updates the ID based off the field name
	public String getIDUpdate (String tcFieldToFind) throws Exception {
		String lcIDReturn 		= "-1";
		String lcFieldToFind 	= tcFieldToFind;
		int lcColumn;
		
		lcColumn = Search.searchArrayList(lacIDFile.get(0), lcFieldToFind);
		lcIDReturn = getIDUpdate(lcColumn);
		
		return lcIDReturn;
	}
	
	// Sets the public ID ArrayList value with a given value
	public static void setIDArrayList(ArrayList<ArrayList<String>> tasGivenIDArray){
		
		lacIDFile = tasGivenIDArray;
	}
	
	public static void setIDPath(String tcIDPath) {
		lcIDPath = tcIDPath;
	}
	public static void setIDFile(String tcIDFile) {
		lcIDFile = tcIDFile;
	}
	public static void setIDExt(String tcIDExt) {
		lcIDExt = tcIDExt;
	}
}
