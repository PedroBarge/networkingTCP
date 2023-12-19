import java.net.*;
import java.io.*;
public class Server {
    private ServerSocket serverSocket;
    private Socket newSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        boolean isRuning = true;
        newSocket = serverSocket.accept();
        out = new PrintWriter(newSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
        while (isRuning) {
            out.print("Server: ");
            out.println("Write something");
            String greeting = in.readLine().toLowerCase();
            if ("hello server".equals(greeting)) {
                out.print("Server: ");
                out.println("hello client");
            }
            if (greeting.contains("quit")) {
                out.println("Exiting...");
                isRuning = false;
            }
            if (!greeting.contains("hello server") && !greeting.contains("quit")) {
                out.print("Server: ");
                out.println("Listening...");
            }
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        newSocket.close();
        serverSocket.close();
    }
}