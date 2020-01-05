package Server;


import Server.RequestProcessor;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// This processor it's just for debugging and testing purposes, it returns the 
// response no matter what the request is
public class SimpleProcessor implements  RequestProcessor{
    
    @Override
    public String process(BufferedReader in) {
        return "GET /index.html HTTP/1.1";
    }
    
}
