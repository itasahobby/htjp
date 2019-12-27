
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHTJP {
    private int port;
    private ServerSocket socket;
    private InetAddress address;
    
    public void start(){
        
        Socket socket = null;
        try{
            // Obtenemos la dirección IP del servidor
            InetAddress dirServidor = InetAddress.getByName("127.0.0.1");
            // Obtenemos el puerto del servidor
            // Obtenemos el mensaje
            String mensaje = "Hola";
            // Creamos el socket y establecemos la conexión con el servidor
            socket = new Socket(dirServidor, port);
            // Establecemos un timeout de 300 segs
            System.out.println("CLIENTE: Conexion establecida con "+ dirServidor.toString() + " al puerto " + port);
            // Establecemos el canal de entrada
            BufferedReader sEntrada = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
            // Establecemos el canal de salida
            PrintWriter sSalida = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("CLIENTE: Enviando " + mensaje);
            // Enviamos el mensaje al servidor
            sSalida.println(mensaje);
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
    
    public ClientHTJP(int port){
        try {
                this.port = port;
                this.socket = new ServerSocket(port);

        } catch (IOException ex){
                Logger.getLogger(ServerHTJP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Constructor to bind the server on an specified ip
    public ClientHTJP(int port,String ip){
        this(port);
        try {
            this.socket.bind(new InetSocketAddress(this.address, this.port));
        } catch (IOException ex) {
            Logger.getLogger(ServerHTJP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
