import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) {
        try {
            // Load port from properties file
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("config.properties");
            prop.load(fis);
            int port = Integer.parseInt(prop.getProperty("port"));

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: "
                        + socket.getInetAddress().getHostAddress());

                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);

                Thread t = new Thread(handler);
                t.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Broadcast message to all clients
    public static void broadcast(String message, ClientHandler sender) {
        Enumeration<ClientHandler> en = clients.elements();
        while (en.hasMoreElements()) {
            ClientHandler client = en.nextElement();
            if (client != sender) {
                client.sendMessage(message);
            }
        }

        // Save chat to file
        try {
            FileWriter fw = new FileWriter("chat_history.txt", true);
            fw.write(message + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Client Handler Class
class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                Server.broadcast(message, this);
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }

    public void sendMessage(String msg) {
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}