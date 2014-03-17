import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import java.util.*;

/**
 * The class PDFKeywordSearch -- it returns a list of page numbers that contains keyword
 * 
 * @author Bonan Yan
 * @version CS56, Winter 2014
 * @see PDFKeywordSearchTest
 */

public class PDFKeywordSearch{
	
    /** default constructor, does nothing
     */	
    public PDFKeywordSearch(){
    }


    /** Main method for running this utility
     */
    public static void main (String[] args){
	
	ArrayList<Integer> result = new ArrayList<Integer>();
	PDFKeywordSearch keywordSearch = new PDFKeywordSearch();
	String word = args[0];
	String filePath = "../" + args[1];
	result = keywordSearch.keywordFoundInPage(word, filePath);
	for (Integer i: result){
	    System.out.println(i);
	}
	return;

    }

    /** Returning a list of page numbers that keyword is found in these pages
     * @param keyword - the keyword
     * @param fileName - the name of the PDF file 
     */


    public ArrayList<Integer> keywordFoundInPage(String keyword, String fileName){
	
	int pageCount = 0;
	File file = new File (fileName);
	ArrayList<Integer> pageFoundList = new ArrayList<Integer>(); //this will store the page numbers
	PDDocument doc = null;
	
	//some code to open the file and handle exceptions
	if (!file.isFile()){
	    System.err.println("File " + fileName + "does not found");
	    return null;
	}
	try {
	    doc = PDDocument.load(file);
	    
	}
	catch(IOException e){
	    System.err.println("Unable to open PDF Parser." + e.getMessage());
	    return null;
	}

	pageCount = doc.getNumberOfPages(); //Find out the number of pages of the pdf
	
	PDFTextParser parser = new PDFTextParser();
	for (int i = 1; i <= pageCount; i++){
	    Boolean foundInPage = false;
	    String contentOfPage = new String();
	    contentOfPage = parser.textOfPage(fileName, i);
	    foundInPage = contentOfPage.contains(keyword); //if the keyword is in the page, return true
	    if (foundInPage){ 
		pageFoundList.add(i); //if the keyword is found in page X, then add X to the list
		
	    }

	    
	}
	
	//Lines below are used to test the result
	//for (int i: pageFoundList){
	//    System.out.println(i);
	//}
	
	return pageFoundList;










}



}
