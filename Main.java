import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server TCP");
        Server server = new Server();
        server.start(6666);
        System.out.println("AA");

    }

}