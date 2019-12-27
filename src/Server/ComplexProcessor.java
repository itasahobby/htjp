
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ComplexProcessor implements  RequestProcessor{
    
    private String errorResponse(){
        //create error message and stop the communication
        return "Error";
    }
    
    private String processState(String stateLine){
        String error;
        String[] splitted = stateLine.split(stateLine);
        if(splitted[0].compareTo("HTTP/1.1") != 0){
            error = "";
        }
        if(splitted[1].compareTo("200") != 0){
            error = "";
        }
        return errorResponse();
    }
    
    private String processHeader(String headLine){
        String[] splitted = headLine.split(" ",2);
        switch(splitted[0]){
            case "Date:":
                // Comprobar que sea la misma hora con margen de un par de segundos
                return "";
            case "Server":
                // Open to filter by Server, not implemented in this simple processor
                return "";
            case "Content-Length":
                // This needs to match the length of the body
                return "";
            case "Content-Type":
                // This needs to match the content of the body
                return "";
            default:
               return errorResponse();
        }
    } 
    
    private void processBody(String bodyLine){
        //process body here
    }
    
    @Override
    public String process(BufferedReader in) {
        String response;
        try {
            String stateLine = in.readLine();
            if(stateLine.compareTo(errorResponse()) == 0){
                
            }
            
            String headLine;
            do{
                headLine = in.readLine();
                if(processHeader(headLine).compareTo(errorResponse()) == 0){
                }
            }while(!headLine.isEmpty());
            
            in.readLine();
            String bodyLine;
            do{
                bodyLine = in.readLine();
                processBody(bodyLine);
            }while(!headLine.isEmpty());
            
            return "GET /index.html HTTP/1.1";
            
        } catch (IOException ex) {
            Logger.getLogger(ComplexProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "POST /index.html HTTP/1.1";
        
    }
    
}
