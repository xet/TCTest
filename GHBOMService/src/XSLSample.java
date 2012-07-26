
public class XSLSample 
{
   public static void main(String args[]) throws Exception
   {
      if (args.length < 2)
      {
         System.err.println("Usage: java XSLSample xslFile xmlFile.");
         System.exit(1);
      }

      // Create a new XSLProcessor.
      XSLProcessor processor = new XSLProcessor();
      
      // Register a base URL to resolve relative references
      // processor.setBaseURL(baseURL);
      
      // Or register an org.xml.sax.EntityResolver to resolve 
      // relative references
      // processor.setEntityResolver(myEntityResolver);

      // Register an error log
      // processor.setErrorStream(new FileOutputStream("error.log"));
      
      // Set any global paramters to the processor
      // processor.setParam(namespace, param1, value1);
      // processor.setParam(namespace, param2, value2);

      // resetParam is for multiple XML documents with different parameters

      String xslFile = args[0];
      String xmlFile = args[1];

      // Create a XSLStylesheet
      //  The stylesheet can be created using one of following inputs:
      //
      // XMLDocument xslInput = /* using DOMParser; see below in this code */
      // URL         xslInput = new URL(xslFile);
      // Reader      xslInput = new FileReader(xslFile);
      
      InputStream xslInput = new FileInputStream(xslFile);

      XSLStylesheet stylesheet = processor.newXSLStylesheet(xslInput);

      // Prepare the XML instance document
      //   The XML instance can be given to the processor in one of 
      // following ways:
      //
      // URL         xmlInput = new URL(xmlFile);
      // Reader      xmlInput = new FileReader(xmlFile);
      // InputStream xmlInput = new FileInputStream(xmlFile);
      // Or using DOMParser

      DOMParser parser = new DOMParser();
      parser.retainCDATASection(false);
      parser.setPreserveWhitespace(true);
      parser.parse(xmlFile);
      XMLDocument xmlInput = parser.getDocument();

      // Transform the XML instance
      //   The result of the transformation can be one of the following:
      //
      // 1. Return a XMLDocumentFragment
      // 2. Print the results to a OutputStream
      // 3. Report SAX Events to a ContentHandler

      // 1. Return a XMLDocumentFragment
      XMLDocumentFragment result;
      result = processor.processXSL(stylesheet, xmlInput);
      
      // Print the result to System.out
      result.print(System.out);

      // 2. Print the results to a OutputStream
      // processor.processXSL(stylesheet, xmlInput, System.out);

      // 3. Report SAX Events to a ContentHandler
      // ContentHandler cntHandler = new MyContentHandler();
      // processor.processXSL(stylesheet, xmlInput, cntHandler);

   }
}
