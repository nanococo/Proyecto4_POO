package Networking.ServerSide;

import App.Accounts.Follower;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;

    private ArrayList<Follower> followers = new ArrayList<>();

    public Server(int port){
        try {
            this.serverSocket = new ServerSocket(port);
            new Connector(this).start();



        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ArrayList<Follower> getFollowers() {
        return followers;
    }
}
