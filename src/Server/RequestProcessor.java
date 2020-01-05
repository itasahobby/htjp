
package Server;

import java.io.BufferedReader;

// This interface should process an Htjp request using the socket
// BufferedReader and return the String that should be sent to the client
public interface RequestProcessor {
    public String process(BufferedReader in);
}
