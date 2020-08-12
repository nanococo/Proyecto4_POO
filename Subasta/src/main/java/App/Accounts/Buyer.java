package App.Accounts;

import App.Auction;
import Messaging.IMessage;
import Networking.ServerSide.Server;
import Observer.IObserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Buyer implements IObserver, Serializable {
    
    private final ArrayList<Auction> auctions = new ArrayList<>();
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    private final Server server;
    private final String id;

    public Buyer(ObjectInputStream inputStream, ObjectOutputStream outputStream, Server server, String id) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.server = server;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addAuction(Auction auction){
        this.auctions.add(auction);
    }

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }

    @Override
    public void update(IMessage message) {

    }

    public void sendMessage(IMessage iMessage) {
        try {
            outputStream.writeObject(iMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
