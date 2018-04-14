package Framework;

import Framework.HelperClasses.CommandHandler;
import Framework.HelperClasses.CommandHandlerListener;
import Framework.Model.MenuModel;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An object that handles the connection with the server.
 */
public class ServerManager {

    private final CommandHandler commandHandler;
    private Socket socket;

    private String host;
    private int port;
    private boolean isConnected = false;

    /**
     * A server connection.
     *
     *
     */
    public ServerManager() {
        this.commandHandler = new CommandHandler();
    }

    /**
     * Connects to the server.
     * @param host The hostname of the server
     * @param port The port of the connection
     */
    public void connect(String host, int port) {
        this.host = host;
        this.port = port;

        openSocket();
        if (isConnected) {
            //starts a listener for messages from the server.
            ExecutorService executor = Executors.newFixedThreadPool(1);
            executor.execute(new MessageListener(this, this.commandHandler));
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


    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public void addCommandHandlerListener(CommandHandlerListener listener) {
        commandHandler.addListener(listener);
    }

    /**
     * A listener for server messages.
     */
    public class MessageListener implements Runnable {
        ServerManager serverManager;
        InputStream is;
        BufferedReader br;
        CommandHandler handler;

        /**
         * Creates a listener for server messages.
         *
         * @param serverManager The server to listen to.
         */
        public MessageListener(ServerManager serverManager, CommandHandler handler) {
            this.serverManager = serverManager;
            this.handler = handler;

            try {
                is = serverManager.getSocket().getInputStream();
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
                        serverManager.disconnect();
                        run = false;
                    }

                    this.handler.handle(line);

                } catch (IOException exception) {
                    serverManager.disconnect();
                    run = false;
                }
            } while (run);
        }
    }



}
