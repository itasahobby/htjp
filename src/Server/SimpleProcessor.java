
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleProcessor implements  RequestProcessor{
    
    private void errorResponse(){
        //create error message and stop the communication
    }
    
    private void processState(String stateLine){
        Boolean error = false; 
        String[] splitted = stateLine.split(stateLine);
        if(splitted[0].compareTo("HTTP/1.1") != 0){
            error = true;
        }
        if(splitted[1].compareTo("200") != 0){
            error = true;
        }
        
        if(error)
            errorResponse();
    }
    
    private void processHeader(String headLine){
        String[] splitted = headLine.split(" ",2);
        switch(splitted[0]){
            case "Date:":
                // Comprobar que sea la misma hora con margen de un par de segundos
            case "Server":
                // Open to filter by Server, not implemented in this simple processor
            case "Content-Length":
                // This needs to match the length of the body
            case "Content-Type":
                // This needs to match the content of the body
                break;
            default:
                errorResponse();
        }
    } 
    
    private void processBody(String bodyLine){
        //process body here
    }
    
    @Override
    public void process(BufferedReader in) {
        try {
            String stateLine = in.readLine();
            processState(stateLine);
            
            String headLine;
            do{
                headLine = in.readLine();
                processHeader(headLine);
            }while(!headLine.isEmpty());
            
            in.readLine();
            String bodyLine;
            do{
                bodyLine = in.readLine();
                processBody(bodyLine);
            }while(!headLine.isEmpty());
        } catch (IOException ex) {
            Logger.getLogger(SimpleProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
