
package Server;

public class Main {
    public static void main(String[] argv){
        
        if (argv.length != 1) {
            System.err.println("Syntaxis: Server.Main -server <server ip> <port>");
            System.exit(-1);
        }
        
        ServerHTJP server = new ServerHTJP(Integer.parseInt(argv[0]), new SimpleProcessor());
        server.start();
    }
}
