
package Launcher;

import Client.ClientHTJP;
import Server.ServerHTJP;
import Server.SimpleProcessor;

public class Launcher {
        
    public static void main(String[] argv){
        
        if (argv.length != 3) {
            // Change error log
            System.err.println("Server syntaxis: Server.Main <server ip> <port> <message>");
            System.err.println("Client syntaxis: Server.Main -server <server ip> <port>");
            System.exit(-1);
        }
        
        if(argv[0].toLowerCase().compareTo("-server") == 0){
            ServerHTJP server = new ServerHTJP(argv[0],Integer.parseInt(argv[1]), new SimpleProcessor());
            server.start();
        }
        
        else{
            ClientHTJP client = new ClientHTJP(argv[0],Integer.parseInt(argv[1]),argv[2]);
            client.start();
        }
    }
}
