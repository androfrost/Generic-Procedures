package genericprocedures;

import java.util.ArrayList;

import fileprocessor.Encryption;
import fileprocessor.delimitedFileProcessor;

public class EncryptFile {
	public static void asciiEncryptFile(String lcFileToEncrypt, boolean tlIsDecrypt) throws Exception {
		int[] naSkeleton = new int[6];
		naSkeleton[0] = 8;
		naSkeleton[1] = -4;
		naSkeleton[2] = -2;
		naSkeleton[3] = 1;
		naSkeleton[4] = 7;
		naSkeleton[5] = 5;
		asciiEncryptFileGeneration(lcFileToEncrypt, tlIsDecrypt, naSkeleton);
	}
	
	public static void asciiEncryptFile(String lcFileToEncrypt, boolean tlIsDecrypt, int[] tnaSkeleton) throws Exception {
		asciiEncryptFileGeneration(lcFileToEncrypt, tlIsDecrypt, tnaSkeleton);
	}
	
	public static void asciiEncryptFileGeneration(String lcFileToEncrypt, boolean tlIsDecrypt, int[] tnaSkeleton) throws Exception {
		ArrayList<ArrayList<String>> importedFile = new ArrayList<ArrayList<String>> ();
		
		importedFile = delimitedFileProcessor.delimitedFileReader(lcFileToEncrypt, ",", "\"");
		
		if (tlIsDecrypt == false) {
			importedFile = Sort.arrayListAsciiSort(importedFile, 0);
			importedFile = Encryption.asciiEncryption(importedFile, tnaSkeleton);
		} else {
			importedFile = Encryption.asciiDecryption(importedFile, tnaSkeleton);
		}
		
		delimitedFileProcessor.delimitedFileWriter("c:/GUIBuilder/_test.csv", importedFile, ",", "\"");
	}
}
