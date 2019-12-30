
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
            // Creamos el socket y establecemos la conexión con el servidor
            socket = new Socket(address, port);
            // Establecemos un timeout de 300 segs
            System.out.println("CLIENTE: Conexion establecida con "+ address.toString() + " al puerto " + port);
            // Establecemos el canal de entrada
            BufferedReader sEntrada = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
            // Establecemos el canal de salida
            PrintWriter sSalida = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("CLIENTE: Enviando " + message);
            // Enviamos el mensaje al servidor
            sSalida.println(message);
            // Recibimos la respuesta del servidor
            String recibido = sEntrada.readLine();
            System.out.println("CLIENTE: Recibido " + recibido);
            // Cerramos los flujos y el socket para liberar la conexión
            sSalida.close();
            sEntrada.close();
        } catch (SocketTimeoutException e) {
        System.err.println("300 segs sin recibir nada");
        }catch (IOException | NumberFormatException e) {
        System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if(socket != null)
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
