package lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        System.out.println("Server is starting up...");
        try (ServerSocket serverSocket = new ServerSocket(18443)) {

            System.out.println("Server waiting for connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(in.readUTF());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();

            while (true) {
                String message = reader.readLine();
                out.writeUTF(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
