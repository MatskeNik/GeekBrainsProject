package lesson6;

import java.io.*;
import java.net.Socket;

public class Client {
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try (Socket socket = new Socket("127.0.0.1", 18443)) {

            System.out.println("Client info: " + socket);

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
