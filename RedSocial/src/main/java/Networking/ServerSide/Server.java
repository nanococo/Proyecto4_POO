package Networking.ServerSide;

import App.Accounts.Celebrity;
import App.Accounts.Follower;
import App.Post;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;

    private final ArrayList<Follower> followers = new ArrayList<>();
    private final ArrayList<Celebrity> celebrities = new ArrayList<>();
    private final ArrayList<Post> posts = new ArrayList<>();

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

    public ArrayList<Celebrity> getCelebrities() {
        return celebrities;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public Celebrity searchCelebrity(String name){
        for (Celebrity celebrity : celebrities) {
            if(celebrity.getName().equals(name)) return celebrity;
        }
        return null;
    }
}
