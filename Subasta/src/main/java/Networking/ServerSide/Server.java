package Networking.ServerSide;

import App.Auction;
import App.Accounts.Auctioneer;
import App.Accounts.Buyer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private final ArrayList<Auctioneer> auctioneers = new ArrayList<>();
    private final ArrayList<Auction> auctions = new ArrayList<>();
    private final ArrayList<Buyer> buyers = new ArrayList<>();


    public Server(int port){
        try {
            this.serverSocket = new ServerSocket(port);
            new Connector(this).start();


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public ArrayList<Auctioneer> getAuctioneers() {
        return auctioneers;
    }

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Auctioneer searchAuctioneer(String name) {
        for (Auctioneer auctioneer : auctioneers) {
            if(auctioneer.getName().equals(name)) return auctioneer;
        }
        return null;
    }

    public Auction findAuction(String id) {
        for (Auction auction : auctions) {
            if (auction.getId().equals(id)) {
                return auction;
            }
        }
        return null;
    }
}
