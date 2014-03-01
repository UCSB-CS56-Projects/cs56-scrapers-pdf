import java.io.*;
import java.util.*;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class keywordsAcrossMultiplePDF{

    public keywordsAcrossMultiplePDF(){
    }

    public static void  main(String[] argv){

	String keywordFileName = argv[0];
	String pdfDirName = argv[1];
	ArrayList<String> keywordList = new ArrayList<String>();
	ArrayList<String> fileNameList = new ArrayList<String>();
        


	
	//code below read keywords from file
	File file = new File(keywordFileName);
	if (!file.isFile()){
	    System.err.println("File " + keywordFileName + "does not found");
	    return;
	}
	try{
	    FileInputStream fis = new FileInputStream(keywordFileName);
	    Scanner scanner = new Scanner(fis);
	    while(scanner.hasNextLine()){
		String buffer = scanner.nextLine();
		System.out.println(buffer);
		keywordList.add(buffer);
		
	    }
	}
	catch(IOException e){
	    System.err.println("unable to open" + e.getMessage());
	   
	    return;
	    
	}
	//get file names
	
	String path = "./" + pdfDirName; 
 
	String files;
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles(); 
 
	for (int i = 0; i < listOfFiles.length; i++) {
 
		if (listOfFiles[i].isFile()) {
			files = listOfFiles[i].getName();
			if (files.endsWith(".pdf") || files.endsWith(".PDF")){
			    System.out.println(files);
			    fileNameList.add(files);
			}
			
		}
	


	}
       

	//main manipulation
	for (String fileName: fileNameList){
	    PDFTextParser parser = new PDFTextParser();
	    String content = parser.pdftoText(pdfDirName + "/" + fileName);
	    for (String keyword: keywordList){
		boolean found = false;
		found = content.contains(keyword);
		if (found){
		    
		    System.out.println(fileName + "," + keyword);
		}
		


	    }
	    

	}



       	
    }


}





