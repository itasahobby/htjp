package Client;

public class Main {
    public static void main(String[] args){
        ClientHTJP server = new ClientHTJP(9999);
        server.start();
    }
}
