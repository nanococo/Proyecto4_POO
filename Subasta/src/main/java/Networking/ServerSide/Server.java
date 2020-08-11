package Networking.ServerSide;

import App.Auctioneer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private final ArrayList<Auctioneer> auctioneers = new ArrayList<>();


    public Server(int port){
        try {
            this.serverSocket = new ServerSocket(port);
            new Connector(this).start();


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Auctioneer> getAuctioneers() {
        return auctioneers;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
