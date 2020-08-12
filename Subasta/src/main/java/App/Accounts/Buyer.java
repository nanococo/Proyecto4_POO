package App.Accounts;

import App.Auction;
import Messaging.IMessage;
import Networking.ServerSide.Server;
import Observer.IObserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Buyer implements IObserver, Serializable {
    
    private final ArrayList<Auction> auctions = new ArrayList<>();
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    private final Server server;
    public int id;

    public Buyer(ObjectInputStream inputStream, ObjectOutputStream outputStream, Server server) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.server = server;
    }

    @Override
    public void update(IMessage message) {

    }
}
