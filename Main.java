import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server TCP");
        Server server = new Server();
        server.startServer(6666);
        server.getIp();
        server.getPortClient();
        server.stop();
    }
    public void tryGetIpAdress(){

    }
}