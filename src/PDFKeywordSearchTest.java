import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
/**                                                                                                                                                                                            
 * The test class PDFKeywordSearchTest -- it tests the PDFKeywordSearch class using JUnit                                                                                                      
 *                                                                                                                                                                                             
 * @author Bonan Yan                                                                                                                                                                           
 * @version CS56, Winter 2014                                                                                                                                                                  
 * @see PDFTextParser                                                                                                                                                                          
 */

public class PDFKeywordSearchTest
{
    /**                                                                                                                                                                                        
     *  Test a basic "Hello World!" pdf                                                                                                                                                        
     */


    @Test public void testKeywordNotFound()
    {
        PDFKeywordSearch myTester = new PDFKeywordSearch();
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        assertEquals(expected,myTester.keywordFoundInPage("There is no such word", "pdfs/testPDF.pdf"));
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
