import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                int port = 8010;
                try {
                    InetAddress address = InetAddress.getByName("localhost");
                    Socket socket = new Socket(address, port);

                    // Send data to server
                    PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                    toSocket.println("Hello Server, I am the client!");

                    // Read response from server
                    BufferedReader fromBufferedReader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                    );
                    String line = fromBufferedReader.readLine();
                    System.out.println("Server says: " + line);

                    // Close resources
                    fromBufferedReader.close();
                    toSocket.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(client.getRunnable());
            thread.start();

            // Optional: Add a small delay to avoid overwhelming the server
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
