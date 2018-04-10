package Framework.HelperClasses;

import Framework.Model.Model;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    private String host = "localhost";
    private int port = 7789;
    private boolean isConnected = false;


    public Client() {
        establishConnection();
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new MessageReceiver(socket));
    }

    public void establishConnection() {
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


    public void sendToServer(String input){
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

    public void closeSocket() {
        try {
            socket.close();
            isConnected = false;
            System.out.println("Closing server connection.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class MessageReceiver implements Runnable {
        Socket socket;
        private Thread t;
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        CommandHandler commandHandler;
        Model model;

        public MessageReceiver(Socket socket) {
            this.socket = socket;
            try {
                is = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
        }

        @Override
        public void run() {
            System.out.println("A new message receiver is now running.");
            String line;

            do {
                try {
                    line = br.readLine();
                    System.out.println(line);
//                    commandHandler.handle(line);

                } catch (IOException exception) {
                    line = null;
                }
            } while (line != null);
        }
    }



}
