package lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents client session
 */
public class ClientHandler {
    private String name;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if(!isClientAuthed()) {
                closeConnection();
            };
        }
    };

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    timer.schedule(timerTask, 120000);
                    authenticate();
                    new Thread(timerTask).start();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }
        }).start();
    }

    public void authenticate() throws IOException {
        System.out.println("Client auth is on going...");

        while (true) {
            String loginInfo = in.readUTF();
            if (loginInfo.startsWith("-auth")) {
                // -auth l1 p1
                String[] splittedLoginInfo = loginInfo.split("\\s");
                AuthenticationService.Client maybeClient = server.getAuthenticationService()
                        .findByLoginAndPassword(
                                splittedLoginInfo[1],
                                splittedLoginInfo[2]
                        );
                if (maybeClient != null) {
                    if (!server.checkLogin(maybeClient.getName())) {
                        sendMessage("status: authok");
                        name = maybeClient.getName();
                        server.broadcast(String.format("%s came in", name));
                        System.out.println("Client auth completed");
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage(String.format("%s already logged in", maybeClient.getName()));
                    }
                } else {
                    sendMessage("Incorrect credentials");
                }
            }
        }
    }

    public void closeConnection() {
        if (isClientAuthed()) {
            server.unsubscribe(this);
            server.broadcast(String.format("%s left", name));
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            if (message.equalsIgnoreCase("-exit")) {
                return;
            }
            if (message.startsWith("/w")) {
                String[] splittedMessage = message.split("\\s");
                String[] splittedMessageByName = message.split(splittedMessage[1] + " ");
                String formatterMessage = String.format("Private message from %s: %s", name, splittedMessageByName[1]);
                System.out.println(formatterMessage);
                server.privateMessage(formatterMessage, splittedMessage[1]);
            } else {
                String formatterMessage = String.format("Message from %s: %s", name, message);
                System.out.println(formatterMessage);
                server.broadcast(formatterMessage);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isClientAuthed() {
        return server.getClientHandlers().contains(this);
    }
}
