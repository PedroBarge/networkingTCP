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
        System.out.println("Client accepted");

        out = new PrintWriter(newSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));

        while (isRuning) {
            out.print("Server: ");
            out.println("Write something");

            String msgIncome = in.readLine();
            InetAddress clientAddress = newSocket.getInetAddress();
            String adressUser = clientAddress.getHostAddress();
            var portUser = newSocket.getLocalPort();

            if ("hello server".equals(msgIncome)) {
                out.print("Server: ");
                out.println("hello client");
            }
            if (msgIncome.contains("quit")) {
                out.println("Exiting...");
                stop();
                isRuning = false;
            }
            if (!msgIncome.contains("hello server") && !msgIncome.contains("quit")) {
                out.print("Server: ");
                out.println("Listening...");
            }
            sout(msgIncome, adressUser, portUser);
        }
    }

    private static void sout(String msgIncome, String adressUser, int portUser) {
        System.out.println("User said: " + msgIncome);
        System.out.println("IP: " + adressUser);
        System.out.println("Port: " + portUser);
        System.out.println();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        newSocket.close();
        serverSocket.close();
    }
}