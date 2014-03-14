import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * The class PDFTextParser -- it extracts text from PDF documents
 * 
 * class PDFTextParser adapted from http://thottingal.in/blog/2009/06/24/pdfbox-extract-text-from-pdf/ 
 *
 * @author Kyle Jorgensen and Alex Mousavi
 * @version CS56, Spring 2011
 * @see PDFTextParserTest
 */
 
public class PDFTextParser 
{
    /** default constructor, does nothing
     */
    public PDFTextParser()
    {
	
    }

    /** Extract text from PDF Document
     * @param fileName - the name of the PDF file from which we will extract the text
     */
    public static String pdftoText(String fileName) 
    {
	PDFParser parser;  // this object handles the parsing of the PDF document
	String parsedText = null;  // this is the string that will contain the text from the pdf doc

	// these 3 are all objects within PDF box that allow us to manipulate the pdf document
	PDFTextStripper pdfStripper = null;
	PDDocument pdDoc = null;
	COSDocument cosDoc = null;

	File file = new File(fileName);

	if (!file.isFile()) 
	    {
		System.err.println("File " + fileName + " does not exist.");
		return null;
	    }
	// try to create a new PDFParser
	try  {
	    parser = new PDFParser(new FileInputStream(file));
	} 
	catch (IOException e) {
	    System.err.println("Unable to open PDF Parser. " + e.getMessage());
	    return null;
	}
	// try to parse the PDF document
	try {
	    parser.parse();
	    cosDoc = parser.getDocument();

	    pdfStripper = new PDFTextStripper();
	    pdDoc = new PDDocument(cosDoc);

	    //pdfStripper.setStartPage(1);
	    //pdfStripper.setEndPage(5);
	    parsedText = pdfStripper.getText(pdDoc);
	} 
	catch (Exception e) {
	    System.err
		.println("An exception occured in parsing the PDF Document."
			 + e.getMessage());
	} 
	// If the document opened, close the two objects that used it
	finally {
	    try {
		if (cosDoc != null)
		    cosDoc.close();
		if (pdDoc != null)
		    pdDoc.close();
	    } 
	    catch (Exception e) {
		e.printStackTrace();
	    }
	}

	return parsedText;
    }


    /** This method the text on a certain page of a PDF file.
       * @param fileName - The name (path) of a PDF file
       * @param pageNumber - The page number that you want to extract text from
     */
    public static String textOfPage(String fileName, int pageNumber) 
    {
	PDFParser parser;  // this object handles the parsing of the PDF document
	String parsedText = null;  // this is the string that will contain the text from the pdf doc

	// these 3 are all objects within PDF box that allow us to manipulate the pdf document
	PDFTextStripper pdfStripper = null;
	PDDocument pdDoc = null;
	COSDocument cosDoc = null;

	File file = new File(fileName);

	if (!file.isFile()) 
	    {
		System.err.println("File " + fileName + " does not exist.");
		return null;
	    }
	// try to create a new PDFParser
	try  {
	    parser = new PDFParser(new FileInputStream(file));
	} 
	catch (IOException e) {
	    System.err.println("Unable to open PDF Parser. " + e.getMessage());
	    return null;
	}
	// try to parse the PDF document
	try {
	    parser.parse();
	    cosDoc = parser.getDocument();

	    pdfStripper = new PDFTextStripper();
	    pdDoc = new PDDocument(cosDoc);

	    pdfStripper.setStartPage(pageNumber);
	    pdfStripper.setEndPage(pageNumber);
	    parsedText = pdfStripper.getText(pdDoc);
	} 
	catch (Exception e) {
	        System.err
		    .println("An exception occured in parsing the PDF Document."
			     + e.getMessage());
	} 
	// If the document opened, close the two objects that used it
	finally {
	    try {
		if (cosDoc != null)
		    cosDoc.close();
		if (pdDoc != null)
		    pdDoc.close();
	    } 
	    catch (Exception e) {
		e.printStackTrace();
	    }
	}

	return parsedText;
    }
    
}

