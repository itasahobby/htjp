
package Client;

import Server.RequestProcessor;
import Server.ServerHTJP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHTJP {
    private final String message;
    private final int port;
    private Socket socket;
    private InetAddress address;
    
    public void start(){
        
        try{
            // Create the socket and stablish the connection with the server
            socket = new Socket(address, port);
            // Stablish a timeout of 3000 seconds
            System.out.println("CLIENTE: Conexion establecida con "+ address.toString() + " al puerto " + port);
            // Declare the input channel
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Declare the output channel
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("CLIENTE: Enviando " + message);
            // Send the message to the server
            output.println(message);
            // Get the response from the server
            String recibido = input.readLine();
            System.out.println("CLIENTE: Recibido " + recibido);
            // We close the channels to free the connection
            output.close();
            input.close();
        } catch (SocketTimeoutException e) {
        System.err.println("300 seconds without receiving anything");
        }catch (IOException | NumberFormatException e) {
        System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if(socket != null)
                    // if the connection was made then we close it
                    socket.close();
            } catch (IOException e) {
            }
        }
        
    }
    
    public ClientHTJP(String addr,int port,String message){
        try {
            this.address = InetAddress.getByName(addr);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientHTJP.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port = port;
        this.message = message;
    }
    
}
