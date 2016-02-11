import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

@WebServlet( name = "FileComparer", urlPatterns = { "/servlets/OutputFile" } ) 
public class FileComparer extends HttpServlet { 
    public static Collection<String> list1;
    public static Collection<String> list2;
    public static Collection<String> outputData;
    
    public static boolean isNullOrWhitespace(String s) {
    return s == null || isWhitespace(s);

}
public static boolean isWhitespace(String s) {
    int length = s.length();
    if (length > 0) {
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    return false;
}
    
    
  /*@Override
  protected void doGet(HttpServletRequest request, 
      HttpServletResponse response) throws ServletException, IOException 
  {
    // reading the user input
    String color= request.getParameter("color");    
    PrintWriter out = response.getWriter();
   
   
  }*/  



        

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
    try
    {
     Part file1,file2;
     InputStream file1Stream,file2Stream;
     String isFileMultipleColumnFile1 = request.getParameter("file1");
     String isFileMultipleColumnFile2 = request.getParameter("file2");
        
     if(!isNullOrWhitespace(isFileMultipleColumnFile1) && "fileContains1".equals(isFileMultipleColumnFile1))
     {
         int columnNumber1;
         String separator1;
       file1  = request.getPart("fileContainsChooser1");
       separator1 = request.getParameter("separator1");
       columnNumber1 =  Integer.parseInt(request.getParameter("column1"));
       file1Stream  = file1.getInputStream();
       list1 = GetListOfRSIDsMultiple(file1Stream, separator1, columnNumber1);
       
     }
     else 
     {
       file1  = request.getPart("fileDoesNotContainChoose1");
       file1Stream  = file1.getInputStream();
       list1 = GetListOfRSIDsMultiple(file1Stream);
     }   
     if(!isNullOrWhitespace(isFileMultipleColumnFile2) && "fileContains2".equals(isFileMultipleColumnFile2))
     {
         int columnNumber2;
         String separator2;
       file2  = request.getPart("fileContainsChooser2");
       separator2 = request.getParameter("separator2");
       columnNumber2 =  Integer.parseInt(request.getParameter("column2"));
       file2Stream  = file2.getInputStream();
       list2 = GetListOfRSIDsMultiple(file2Stream, separator2, columnNumber2); 
     }
     else 
     {
       file2  = request.getPart("fileDoesNotContainChoose2");
       file2Stream  = file2.getInputStream();
       list2 = GetListOfRSIDsMultiple(file2Stream);
     }
     Collection<String> common= new TreeSet<>();
     common=list1;
     common.retainAll(list2);
        //outputData = common;
        double n1 = ((double)(common.size()) / (list1.size() - 1)) * 100;
        double n2 = ((double)(common.size()) / (list2.size() - 1)) * 100;
        String v1 = String.format("%.2f", n1);
        String v2 = String.format("%.2f", n2);
            String result = "2.Percentage overlap with respect to "+file1.getSubmittedFileName()+" : " +
                            v1+"%";
            String result1 = "3.Percentage overlap with respect to "+file2.getSubmittedFileName()+" : " +
                             v2+"%";
            //outputData.add("1.Count of common Elements: " + common.size());
            //outputData.add(result);
            //outputData.add(result1);
            
            
        response.setContentType( "text/plain;charset=UTF-8" );
        response.setHeader( "Content-Disposition", "attachment;filename=MyTextFile.csv" );
        PrintWriter out = response.getWriter();

        try {
            out.println("1.Count of common Elements: " + common.size());
            out.println(result);
            out.println(result1);
            
            Iterator commonList = common.iterator();
            while(commonList.hasNext())
            {
                out.println(commonList.next());
            }

        } finally {            
            out.close();
        }
        
        
    }//try
    catch(IOException | ServletException | NumberFormatException ex)
            {
              List<String> out1 =  new Vector<>();
            }
  
  
  }
  
    public static Collection<String> GetListOfRSIDsMultiple(InputStream fileContent, String separator, int columnNumber)
        {
            Collection<String> output = new TreeSet<>();
            if (!isNullOrWhitespace(fileContent.toString()))
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
        List<String> out;
        out =  new ArrayList<>();
        String line;
                try {
                    while ((line = reader.readLine()) != null) 
                    {
                        out = Arrays.asList(line.split(separator, -1));
                        if(out.size()>=columnNumber)
                            {
                                output.add(out.get(columnNumber-1).toUpperCase()) ;
                            }
                    }       
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(FileComparer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return output;
        }

    public static Collection<String> GetListOfRSIDsMultiple(InputStream fileContent)
        {
            
            Collection<String> output = new TreeSet<>();
            if (!isNullOrWhitespace(fileContent.toString()))
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
        String line;
                try 
                {
                    while ((line = reader.readLine()) != null) 
                    {
                        
                       output.add(line.toUpperCase());
                    }       
                } catch (IOException ex) 
                {
                    Logger.getLogger(FileComparer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return output;
        }    
} 