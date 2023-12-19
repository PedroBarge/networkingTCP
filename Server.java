import java.net.*;
import java.io.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();

        printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        printWriter.println("Say hello server");

        String greeting = bufferedReader.readLine();
        var ip = getIp();
        var portClient = getPortClient();

        if (greeting.contains("hello server")) {
            System.out.println(ip);
            System.out.println(portClient);
            printWriter.println("hello client");
        } else {
            System.out.println(ip);
            System.out.println(portClient);
            printWriter.println("unrecognised greeting");
        }
    }

    private int getPortClient() {
        return clientSocket.getPort();
    }

    private InetAddress getIp() {
        return clientSocket.getInetAddress();
    }


    public void stop() throws IOException {
        bufferedReader.close();
        printWriter.close();
        clientSocket.close();
        serverSocket.close();
    }
}
