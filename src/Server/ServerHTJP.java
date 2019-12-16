
package Server;

import java.io.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerHTJP {
    
    private InetAddress address;
    private ServerSocket socket;
    private int port;
    private RequestProcessor procc;
    
    public void start(){
        while(true){
            // Accept client connections
            Socket client;
            try {
                client = socket.accept();
                // Declaramos las lecturas del buffer de entrada y salida del socket
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                procc.process(in);
            } catch (IOException ex) {
                Logger.getLogger(ServerHTJP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ServerHTJP(int port,RequestProcessor procc){
        try {
                this.port = port;
                this.socket = new ServerSocket(port);
                this.procc = procc;

        } catch (IOException ex){
                Logger.getLogger(ServerHTJP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Constructor to bind the server on an specified ip
    public ServerHTJP(int port,RequestProcessor procc,String ip){
        this(port,procc);
        try {
            this.socket.bind(new InetSocketAddress(this.address, this.port));
        } catch (IOException ex) {
            Logger.getLogger(ServerHTJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
