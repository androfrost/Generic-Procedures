package genericprocedures;

import java.util.ArrayList;

public class GenericRun {
	
	public static ID testID = new ID();		// = new ID("c:/test/", "IDTest", ".csv", "ID1,ID2,ID3,ID4");
	
	public static void main(String[] args) throws Exception {
		int[] testArray = Ascii.asciiStringValue("123456789abczABCZ");
		for (int xa = 0; xa < testArray.length; xa++) {
			
		}
		
		int[] testArrayInt2 = Ascii.asciiStringValue("16zA789a2345bcBCZ");
		Integer[] testArray2 = new Integer[testArrayInt2.length];
		for (int xa = 0; xa < testArray.length; xa++) {
			testArray2[xa] = Integer.valueOf(testArrayInt2[xa]);
		}
		testArray2 = Sort.sortArray(testArray2);
		for (int xa = 0; xa < testArray2.length; xa++) {
			System.out.println(testArray2[xa]);
		}
		System.out.println("Tested");
		
		ArrayList<ArrayList<String>> testArrayList = new ArrayList<ArrayList<String>>();
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(0).add("Football");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(1).add("Basketball");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(2).add("Soccer");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(3).add("Baseball");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(4).add("Ping Pong");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(5).add("Tennis");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(6).add("A Sport");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(7).add("Baskerball");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(8).add("Baseballz");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(9).add("Baseballg");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(10).add("Baseballq");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(11).add("Baseballg");
		testArrayList.add(new ArrayList<String>());
		testArrayList.get(12).add("Baseg");
		
		testArrayList = Sort.arrayListAsciiSort(testArrayList,0);
		for (int xa = 0; xa < testArrayList.size(); xa++) {
			System.out.println(testArrayList.get(xa));
		}
		System.out.println("Tested 2");
		
		ArrayList<Double> liaTestArray = new ArrayList<Double> ();
		liaTestArray = MathCalc.equalPortionArrayList(253.16, 12);
		liaTestArray = MathCalc.equalPortionArrayList(1000, 28);
		
		Chronology.completeDateInteger(1,2,2000,1);
		
		EncryptFile.asciiEncryptFile("c:/GUIBuilder/testGUI.csv",false);
		EncryptFile.asciiEncryptFile("c:/GUIBuilder/_test.csv",true);
		
		int[] nColumnNo = new int[5];
		nColumnNo[0] = 0;
		nColumnNo[1] = 1;
		nColumnNo[2] = 2;
		nColumnNo[3] = 3;
		nColumnNo[4] = 1;
		
		// Test different lengths and size limit sorts
		insertionSortTest(10, 10000);
		insertionSortTest(50, 100);
		insertionSortTest(1000, 1000);
		
		System.out.println("displayLinesALStandard");
		String[] standard = ArrayListGP.displayLinesAL("C:/ImportFiles/exportcsv3.csv", nColumnNo);
		for (int x = 0; x < standard.length; x++) {
			System.out.println(standard[x]);
		}			
		System.out.println("\n"+"displayLinesAL");
		String[] nonStandard = ArrayListGP.displayLinesAL("C:/ImportFiles/exportcsv3.csv", nColumnNo,0,6);
		for (int x = 0; x < nonStandard.length; x++) {
			System.out.println(nonStandard[x]);
		}	
		
		setupIDTestFile();
		
		mathCalcTest();
	}
	
	
	public static void setupIDTestFile() throws Exception {
		
		testID.setupIDFile("c:/test/", "IDTest", ".csv", "ID1,ID2,ID3,ID4");
		testID.getIDUpdate(1);
		testID.getIDUpdate(2);
		testID.getIDUpdate(2);
		testID.getIDUpdate("ID4");
		testID.getIDUpdate("ID5");
		
	}
	
	public static void mathCalcTest() {
		double rectarea = MathCalc.rectangleArea(7.54,13.44);
		System.out.println(rectarea);
		
		double triarea = MathCalc.rightTriangleArea(7.54,13.44);
		System.out.println(triarea);
		
		double clength = MathCalc.pythagorian(12,16);
		System.out.println(clength);
	}
	
	// Call insert sort algorithm with a random group of numbers
	public static void insertionSortTest(int tArrayLen, int randomNumberSizeLimit) {
		int arrayLen = tArrayLen;
		ArrayList<Integer> arrayToSort = new ArrayList<Integer>();
		for (int arrLoc = 0; arrLoc < arrayLen; arrLoc++) {
			arrayToSort.add((int) (Math.random()*(randomNumberSizeLimit)));
		}
		
		System.out.println(arrayToSort);
		
		ArrayList<Integer> arraySorted = Sort.sortArrayInsertion(arrayToSort);
		
		System.out.println(arraySorted);
	}
}
