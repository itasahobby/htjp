package Client;

public class Main {
    public static void main(String[] argv){
        
        if (argv.length != 3) {
            System.err.println("Syntaxis: Server.Main <server address> <port> <message>");
            System.exit(-1);
        }
        
        ClientHTJP server = new ClientHTJP(argv[0],Integer.parseInt(argv[1]),argv[2]);
        server.start();
    }
}
