import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame {

    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    private BufferedReader serverReader;
    private BufferedWriter serverWriter;

    public Client() {

        setTitle("Chat Application");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Message Field
        messageField = new JTextField();

        // Send Button
        sendButton = new JButton("Send");

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        connectToServer();

        // Send Button Click
        sendButton.addActionListener(e -> sendMessage());

        // Press Enter to Send
        messageField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    private void connectToServer() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            Socket socket = new Socket(ip, 5000);

            serverReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            serverWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            // Thread to receive messages
            Thread receiveThread = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = serverReader.readLine()) != null) {
                        chatArea.append(msg + "\n");
                    }
                } catch (IOException e) {
                    chatArea.append("Disconnected from server.\n");
                }
            });

            receiveThread.start();

        } catch (Exception e) {
            chatArea.append("Connection Error.\n");
        }
    }

    private void sendMessage() {
        try {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                serverWriter.write(message);
                serverWriter.newLine();
                serverWriter.flush();
                messageField.setText("");
            }
        } catch (IOException e) {
            chatArea.append("Failed to send message.\n");
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}