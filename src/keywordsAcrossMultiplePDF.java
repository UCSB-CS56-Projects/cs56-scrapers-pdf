import java.io.*;
import java.util.*;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * The class keywordsAcrossMultiplePDF -- it takes a file of keywords and a path that contains multiple PDF files
 * and returns the PDF file names that contains these keywords
 * 
 * class PDFTextParser adapted from http://thottingal.in/blog/2009/06/24/pdfbox-extract-text-from-pdf/ 
 *
 * @author Bonan Yan
 * @version CS56, Winter 2014
 * 
 */
 


public class keywordsAcrossMultiplePDF{
    
    /** default constructor, does nothing
     */

    public keywordsAcrossMultiplePDF(){
    }

    /** The main method for running this program
     */
    public static void  main(String[] argv){

	String keywordFileName = argv[0];
	String pdfDirName = argv[1];
	ArrayList<String> keywordList = new ArrayList<String>();//this list contains multiple keywords
	ArrayList<String> fileNameList = new ArrayList<String>();//this list contains multiple file names
	keywordFileName = "../" + keywordFileName; //file path handling
	//code below read keywords from file
	File file = new File(keywordFileName);
	if (!file.isFile()){
	    System.err.println("File " + keywordFileName + " does not found");
	    return;
	}
	try{
	    FileInputStream fis = new FileInputStream(keywordFileName);
	    Scanner scanner = new Scanner(fis);
	    while(scanner.hasNextLine()){
		String buffer = scanner.nextLine();
		keywordList.add(buffer);
		
	    }
	    //for (String a :keywordList){
	    //System.out.println(a);
	    //}
	    //Comments above are used for testing
	}
	catch(IOException ex){
	    System.err.println("unable to open" + ex.getMessage());
	    return;
	}
	
	//get file names into list
	String path = "../" + pdfDirName; 
 
	String files;
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles(); 
 
	for (int i = 0; i < listOfFiles.length; i++) {
 
		if (listOfFiles[i].isFile()) {
			files = listOfFiles[i].getName();
			if (files.endsWith(".pdf") || files.endsWith(".PDF")){
			    fileNameList.add(files);
	
			}
			
		}

	}

	//for (String a : fileNameList){
	//  System.out.println(a);
	//}
	//Comments above are for testing


	//main manipulation
	for (String fileName: fileNameList){
	    PDFTextParser parser = new PDFTextParser();
	    String content = parser.pdftoText("../" + pdfDirName + "/" + fileName);
	    for (String keyword: keywordList){ //get every keyword from the list
		boolean found = false;
		found = content.contains(keyword);
		if (found){	 // if the keyword in the pdf then print it out
		    System.out.println(fileName + "," + keyword);
		}
		
	    }
	    
	}

    }

}
