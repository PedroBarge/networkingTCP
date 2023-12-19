import java.net.*;
import java.io.*;
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        boolean isRuning = true;
        clientSocket = serverSocket.accept();
        while (isRuning) {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Write something");
            String greeting = in.readLine().toLowerCase();
            if ("hello server".equals(greeting)) {
                out.println("hello client");
            }
            if (greeting.equals("quit")) {
                isRuning = false;
            }
            out.println("Listening...");
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}