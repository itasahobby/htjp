
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ComplexProcessor implements  RequestProcessor{
    
    // In HTJP actually shouldn't send anything with an incorrect request
    private final String err = "GET /error.html HTTP/1.1";
    private final String response = "POST /secretpath.html HTTP/1.1";
    private Integer bodyLength;
    
    private Boolean processState(String stateLine){
        // Return false if the state line doesn't fit the following
        // syntax: HTTP/1.1 200 Ok
        try{    
            String[] splitted = stateLine.split(stateLine);
            if(splitted[0].compareTo("HTTP/1.1") != 0){
                return false;
            }
            if(splitted[1].compareTo("200") != 0){
                return false;
            }
            if(splitted[2].compareTo("Ok") != 0){
                return false;
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            return false;
        }
        return true;
    }
    
    private Boolean processHeader(String headLine){
        String[] splitted = headLine.split(" ",2);
        switch(splitted[0]){
            case "Date:":
            case "Server":
            case "Content-Length":
                // Check error for when you don't give a decimal number into Content-Length
               this.bodyLength = Integer.decode(splitted[1]);
            case "Content-Type":
                // This needs to match the content of the body
                return true;
            default:
               return false;
        }
    } 
    
    private void processBody(String bodyLine){
        //process body here
    }
    
    @Override
    public String process(BufferedReader in) {
        try {
            // Processing the State
            String stateLine = in.readLine();
            if(!processState(stateLine)){
                return this.err;
            }
            
            // Porcessing the Header
            String headLine;
            do{
                headLine = in.readLine();
                if(processHeader(headLine)){
                    return this.err;
                }
            }while(!headLine.isEmpty());
            
            // Reading the blank line that is needed between the header lines 
            // and the body
            in.readLine();
            // Porcessing the Body
            String bodyLine;
            do{
                bodyLine = in.readLine();
                processBody(bodyLine);
            }while(!headLine.isEmpty());
            
            return this.response;
            
        } catch (IOException ex) {
            Logger.getLogger(ComplexProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.err;
        
    }
    
}
