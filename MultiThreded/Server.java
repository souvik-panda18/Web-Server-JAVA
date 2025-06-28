import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                toClient.println("Welcome to the server!");
                System.out.println("Sent welcome message to client: " + clientSocket.getRemoteSocketAddress());

                // Close resources
                toClient.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000); // Optional timeout
            System.out.println("Server is listening on port " + port);

            while (true) {
                try {
                    Socket acceptedConnection = serverSocket.accept();
                    System.out.println("Client connected: " + acceptedConnection.getRemoteSocketAddress());

                    Thread thread = new Thread(() -> server.getConsumer().accept(acceptedConnection));
                    thread.start();

                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timed out waiting for client.");
                    break; // exit if you want to stop server after timeout
                }
            }

            serverSocket.close();
            System.out.println("Server socket closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
