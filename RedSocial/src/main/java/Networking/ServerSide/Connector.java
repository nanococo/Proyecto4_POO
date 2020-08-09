package Networking.ServerSide;

import java.io.IOException;
import java.net.Socket;

public class Connector extends Thread {

    private final Server server;

    public Connector(Server server){
        this.server = server;
    }


    @Override
    public void run() {
        while (true){
            try {
                System.out.println("Waiting for a client ...");
                Socket client = server.getServerSocket().accept();
                System.out.println("Client Accepted. Client info: "+client.getInetAddress().toString());
                new Listener(server, client).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
