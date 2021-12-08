package genericprocedures;

import java.util.ArrayList;

public class MathCalc {
	public static ArrayList<Double> equalPortionArrayList(double tiTotalAmount, int tiNumberToParse) {
		ArrayList<Double> liaEqualPortionArray = new ArrayList<Double> ();
		double liPortion, liRemainder; 
		int changeValue = 0;
		double liTotPar = 0;
		
		liPortion = doubleRound(tiTotalAmount / tiNumberToParse, 100);
		liRemainder = doubleRound(tiTotalAmount - (liPortion * tiNumberToParse), 100); //doubleRound(tiTotalAmount % tiNumberToParse, 100);
		
		if (liRemainder < 0)
			changeValue = tiNumberToParse-1;
		
		for (int iNumParsed = 0; iNumParsed < tiNumberToParse; iNumParsed++) {
			liaEqualPortionArray.add(liPortion);
			if (iNumParsed == changeValue) {
				liTotPar = doubleRound(liaEqualPortionArray.get(iNumParsed)+liRemainder, 100);
				liaEqualPortionArray.set(iNumParsed,liTotPar);
			}
			System.out.println(liaEqualPortionArray.get(iNumParsed));
		}
		
		return liaEqualPortionArray;
	}
	
	/*
	 * Rounds a double to a specific number of decimal places and returns the calculated value
	 */
	public static double doubleRound(double tiValue, double tiScale) {
		double ldPortionAmount = 0;
	
		ldPortionAmount = (double) Math.round((tiValue) * tiScale);
		ldPortionAmount = (ldPortionAmount) / tiScale;
		
		return ldPortionAmount;
	}
	
	public static double rectangleArea(double tdVert, double tdHoriz) {
		double ldArea = 0;
		
		ldArea = tdVert * tdHoriz;
		
		return ldArea;
	}
	
	public static double rightTriangleArea(double tdVert, double tdHoriz) {
		double ldArea = 0;
		
		ldArea = rectangleArea(tdVert, tdHoriz) / 2;
		
		return ldArea;
	}
	
	public static double pythagorian(double tdVert, double tdHoriz) {
		double ldCLength = 0;
		
		ldCLength = Math.sqrt((tdVert * tdVert) + (tdHoriz * tdHoriz));
		
		return ldCLength;
	}
}
