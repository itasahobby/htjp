
package Server;

import java.io.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerHTJP {
    
    private InetAddress address;
    private ServerSocket serverSocket;
    private int port;
    private RequestProcessor procc;
    
    public void start(){
        Socket connectionSocket = null;
        try{
            // Set a timeout of 300 secs
            serverSocket.setSoTimeout(300000);
            while(true){
                // Esperamos posibles conexiones
                connectionSocket = serverSocket.accept();
                // Creamos un objeto ThreadServidor, pasándole la nueva conexión
                ThreadServidor handler = new ThreadServidor(connectionSocket,this.procc);
                // Iniciamos su ejecución con el método start()
                handler.run();
            }
        }catch(SocketTimeoutException e){
            System.err.println("Nothing received in 300 secs");
        }catch(IOException | NumberFormatException e){
            System.err.println("Error: " + e.getMessage());
        }finally{
            if(connectionSocket != null){
                try {
                    connectionSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerHTJP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public ServerHTJP(int port,RequestProcessor procc){
        try {
                this.port = port;
                this.serverSocket = new ServerSocket(port);
                this.procc = procc;

        } catch (IOException ex){
                Logger.getLogger(ServerHTJP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Constructor to bind the server on an specified ip
    public ServerHTJP(int port,RequestProcessor procc,String ip){
        this(port,procc);
        try {
            this.serverSocket.bind(new InetSocketAddress(this.address, this.port));
        } catch (IOException ex) {
            Logger.getLogger(ServerHTJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
