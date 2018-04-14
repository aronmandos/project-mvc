package Framework.HelperClasses;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An object that handles the connection with the server.
 */
public class Server {
    private Socket socket;

    private String host;
    private int port;
    private boolean isConnected = false;

    /**
     * A server connection.
     *
     * @param host The hostname of the server
     * @param port The port of the connection
     */
    public Server(String host, int port) {
        this.host = host;
        this.port = port;

    }

    /**
     * Connects to the server.
     */
    public void connect(CommandHandler handler) {
        openSocket();
        if (isConnected) {
            //starts a listener for messages from the server.
            ExecutorService executor = Executors.newFixedThreadPool(1);
            executor.execute(new MessageListener(this, handler));
        }

    }

    /**
     * Disconnects from the server.
     */
    public void disconnect() {
        if (!isConnected) {
            System.out.println("No connection to disconnect.");
            return;
        }
        System.out.println("Closing server connection.");
        closeSocket();
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    private Socket getSocket() {
        return this.socket;
    }

    /**
     * Opens a TCP socket connection.
     */
    private void openSocket() {
        if (!isConnected) {
            try {
                InetAddress address = InetAddress.getByName(host);
                socket = new Socket(address, port);
                System.out.println("Connection established at " + address);
                isConnected = true;

            } catch (IOException e) {
                System.out.println("Can't connect to server");
            }
        }else{
            System.out.println("already connected");
        }
    }

    /**
     * Closes the TCP socket to the server.
     */
    private void closeSocket() {
        try {
            socket.close();
            isConnected = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message to the server.
     *
     * @param input A message for the server
     */
    public void send(String input){
        if (!this.isConnected()) {
            return;
        }
        try {
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            PrintWriter pwr = new PrintWriter(os, true);
            pwr.println(input);

            System.out.println("Attempting to send to the server: " + input);
        }
        catch (Exception exception){
            System.out.println("Could not send \"" +input +"\" to server");
            exception.printStackTrace();
        }
    }

    /**
     * A listener for server messages.
     */
    public class MessageListener implements Runnable {
        Server server;
        InputStream is;
        BufferedReader br;
        CommandHandler handler;

        /**
         * Creates a listener for server messages.
         *
         * @param server The server to listen to.
         */
        public MessageListener(Server server, CommandHandler handler) {
            this.server = server;
            this.handler = handler;

            try {
                is = server.getSocket().getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            br = new BufferedReader(new InputStreamReader(is));
        }

        /**
         * Listens for messages.
         */
        @Override
        public void run() {
            System.out.println("A new message listener is now running.");
            String line;
            boolean run = true;

            do {
                try {
                    line = br.readLine();
                    System.out.println("Server: " + line);

                    //null means the connection was lost.
                    if (line == null) {
                        System.out.println("connection lost");
                        server.disconnect();
                        run = false;
                    }

                    this.handler.handle(line);

                } catch (IOException exception) {
                    server.disconnect();
                    run = false;
                }
            } while (run);
        }
    }



}
