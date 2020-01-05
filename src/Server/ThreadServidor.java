
package Server;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/** Thread que atiende una conexión de un servidor de eco. */
public class ThreadServidor extends Thread {
    Socket socket;
    RequestProcessor procc;
    public ThreadServidor(Socket s,RequestProcessor procc) {
        // Almacenamos el socket de la conexión
        this.socket = s;
        this.procc = procc;
    }
    @SuppressWarnings("override")
    public void run() {
        try {
            // Establecemos el canal de entrada
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Establecemos el canal de salida
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            // We send the response to the client
            String response = procc.process(reader);
            writer.println(response);
            // Cerramos los flujos
            reader.close();
            writer.close();
        } catch (SocketTimeoutException e) {
            System.err.println("300 segs sin recibir nada");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                // Cerramos el socket
                this.socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
