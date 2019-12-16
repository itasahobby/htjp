
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleProcessor implements  RequestProcessor{
    
    private void errorResponse(){
        
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
    
    @Override
    public void process(BufferedReader in) {
        try {
            String stateLine = in.readLine();
            processState(stateLine);
        } catch (IOException ex) {
            Logger.getLogger(SimpleProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
