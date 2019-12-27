
package Server;

public class Main {
    public static void main(String[] args){
        ServerHTJP server = new ServerHTJP(9999, new SimpleProcessor());
        server.start();
    }
}
