import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
/**
 * The test class PDFTextParserTest -- it tests the PDFTextParser class using JUnit
 *
 * @author Alex Mousavi and Kyle Jorgensen
 * @version CS56, Spring 2011
 * @see PDFTextParser
 */

public class PDFTextParserTest
{
    /**
     *  Test a basic "Hello World!" pdf file
     */
    @Test public void testHelloWorldFile()
    {
	PDFTextParser myTester = new PDFTextParser();
	String expected = "Hello World!\n";

	assertEquals(expected,myTester.pdftoText("pdfs/helloWorld.pdf"));
    }
   
    /**
     *  Tests another pdf file
     */
    @Test public void testHoneybadgers()
    {
	PDFTextParser myTester = new PDFTextParser();
	String expected = "Honey Badgers are dangerous animals.\n";

	assertEquals(expected,myTester.pdftoText("pdfs/Honeybadgers.pdf"));
    }

    
    /**
     *  Tests another pdf file with symbols
     */
    @Test public void testSymbols()
    {
	PDFTextParser myTester = new PDFTextParser();
	String expected = "<<TeStInG &&& syMbols and 0DD tYping !! @wesome.\n";

	assertEquals(expected,myTester.pdftoText("pdfs/symbols.pdf"));
    }

    @Test public void testTextOfPage(){ //test the textofpage method
	PDFTextParser myTester = new PDFTextParser();
	String expected = "Honey Badgers are dangerous animals.\n";

	assertEquals(expected,myTester.textOfPage("pdfs/Honeybadgers.pdf", 1));

    }
    @Test public void testKeywordFound()
	{
	    PDFKeywordSearch myTester = new PDFKeywordSearch();
	    ArrayList<Integer> expected = new ArrayList<Integer>();
	    expected.add(1);
	    expected.add(3);
	    assertEquals(expected,myTester.keywordFoundInPage("Hello", "pdfs/testPDF.pdf"));
	}

  

}
