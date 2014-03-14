import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import java.util.*;

public class PDFKeywordSearch{

    public PDFKeywordSearch(){
    }

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


    public ArrayList<Integer> keywordFoundInPage(String keyword, String fileName){
	//PDFParser parser0; //
	//COSDocument cosDoc = null;
	//parser0 = new PDFParser(new FileInputStream(fileName));
	int pageCount = 0;
	//parser0.parse();
	//cosDoc = parser0.getDocument();
	File file = new File (fileName);
	ArrayList<Integer> pageFoundList = new ArrayList<Integer>(); //this will store the page numbers
	PDDocument doc = null;
	//doc = new PDDocument(cosDoc);
	
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
	    foundInPage = contentOfPage.contains(keyword);
	    if (foundInPage){
		pageFoundList.add(i);
		
	    }

	    
	}

	//for (int i: pageFoundList){
	//    System.out.println(i);
	//}
	//Lines above are used to test the result
	return pageFoundList;










}



}
