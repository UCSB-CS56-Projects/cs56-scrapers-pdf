# cs56-scrapers-pdf

Utilities for scraping PDF files.  Uses PDFBox as a tool.

http://pdfbox.apache.org/

------------------------------

project history
===============
```
 W14 | jstaahl 4pm | bernardyan | (kjorg50) Utilities for scraping PDF files. Uses PDFBox as a tool.
```

What it does
============

In current state, the utility can extract content (single page or all pages of the file) from a PDF file using "PDFTextParser".

More than that, it can search a keyword in a PDF file and get a list of page numbers of the ocurrence of the keyword using "PDFKeywordSearch".

The last function is that the utility can take a list of keywords from file and take a path that contains multiple PDFs and print out how which keyword is in which PDF file using "keywordsAcrossMultiplePDF"


Internal Documentation
======================

This utility is now basically a framework. There are many special cases to be considered and many interfaces between multiple classes to be optimized to make it works more smoothly. Moreover, there are many things to be optimized in order to make it more efficient so it can handle much larger data in the future. (For example, it uses String to handle extracted data. But it may expose some unknown problems if the PDF file is super large.)

For students who works on this project in the future, I suggest you think about the big picture of this utility first so you can draw a clear class hierarchy and reuse many of your code.  


How to run this:

```
ant                                [compile this utility]
ant test                           [some basic tests to make sure your copy works]
ant runKeywordsAcrossMultiPDF      [it will take the hello.txt for keywords and /pdfs for pdf files]
ant ant -DfilePath={filePath} -DdirName={DirName} runKeywordsAcrossMultiPDF   [if you put keyword file and pdf files in the /pdfs, you can run this use your own files]
ant runKeywordSearch               [it searchs a keyword in a PDF file and returns page numbers that contains the word]
ant ant -Dword={word} -DfilePath={filePath} runKeywordsAcrossMultiPDF     [same funcation as above, but you can use your own keyword and file]
```


--------------------------

W14 Changes based on Code Review
================================

Note: Next quarter that this project is assigned, either a curator, or the first team to work on this project
might review this list, and decide which of these needs further work, if any.  For any issue that needs further
attention, make an "issue".  Then this list can be retired from the README file.

1. Rather than including the source files from pdfbox, you should have just included the jar file.
    *  I deleted these source files.
2. In your build.xml file you should make targets for running the code, or a target that provides information on what to write in the command line to run the code held in keywordsAcrossMultiplePDF.
    * I wrote two targets to run both "KeywordsAcrossMultiPDF" and "KeywordSearch". They all have default args and you can use your own args as well; the instructions of using them are in the "how to run this section above"
3. You should include a javadoc comment to explain what this method does and what its input parameters are.
    * I added a lot of javadoc comments, so it should be easier to read now.
4. There is a lot of duplicated code between this method (textOfPage()) and pdfToText(). Perhaps you should consider creating private helper methods that perform the opening and closing of the necessary objects/files from these two methods that end up being duplicate code.
    *  I removed these duplicated code and it turns out I don't actually need a helper function.
5. This test should be written in its own test class file. A single test class file should only correspond to a single class. You should create a new file "PDFKeywordSearchTest.java" and put this test method in there.
    * I've created a new test file for testing PDFKeywordSearch class.
6. For the new functionality that you made, it probably would be good to test even more cases that might arise. For instance, test the scenario when you call testTextOfPage() and the text is not found at all -- what should be returned? In your test, make sure it returns the correct value. Also, test a case where the text is found on multiple pages, and make sure the correct page numbers are returned. Your simple test right now does not cover these cases.
    * I changed this part a little bit. If the keyword is not found in all pages, it will return an ArrayList that contains only one "0" (since there is no 0 page) instead of returning an empty ArrayList
7. You should delete these instead of just commenting them out.
    * All unnecessary comments are deleted, except some testing code that can be used in the future.
8. Don't have two empty lines, this makes the code seem not that cohesive
    * They are deleted, so it should be clean and easy to read.

