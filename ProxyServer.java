import java.io.*;
import java.net.*;

public class ProxyServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveur proxy démarré. En attente de connexions...");

            // Attendre la connexion du premier client
            Socket clientSocket1 = serverSocket.accept();
            System.out.println("Client 1 connecté depuis " + clientSocket1.getInetAddress());

            // Attendre la connexion du deuxième client
            Socket clientSocket2 = serverSocket.accept();
            System.out.println("Client 2 connecté depuis " + clientSocket2.getInetAddress());

            // Créer des threads pour gérer les transferts de données entre les clients
            Thread clientThread1 = new Thread(() -> transferData(clientSocket1, clientSocket2));
            Thread clientThread2 = new Thread(() -> transferData(clientSocket2, clientSocket1));
            clientThread1.start();
            clientThread2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void transferData(Socket fromSocket, Socket toSocket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(fromSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(toSocket.getOutputStream(), true);
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line); // Transférer les données du premier client au deuxième client
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
