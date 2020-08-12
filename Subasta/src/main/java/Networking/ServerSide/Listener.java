package Networking.ServerSide;

import App.Auction;
import App.Auctioneer;
import App.Buyer;
import App.Product;
import Messages.AuctionsContainer;
import Messages.GenericMessage;
import Messages.MessageKeys;
import Messaging.IMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Listener extends Thread {

    private final Server server;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private Auctioneer auctioneer;
    private Buyer buyer;

    public Listener(Server server, Socket clientSocket) throws IOException {
        this.server = server;
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        while (true){
            String target = "";
            try {
                IMessage message = (IMessage) inputStream.readObject();
                GenericMessage genericMessage;
                Product product;
                Auction auction;


                switch (message.getKey()) {
                    case MessageKeys.SET_CLIENT_TYPE:
                        genericMessage = (GenericMessage) message;

                        if(genericMessage.getParams()[0].equals(MessageKeys.TARGET_BUYER)){
                            this.buyer = new Buyer(inputStream, outputStream, server);
                            server.getBuyers().add(buyer);
                        } else if(genericMessage.getParams()[0].equals(MessageKeys.TARGET_AUCTIONEER)){
                            System.out.println("New Auctioneer: "+genericMessage.getParams()[1]);
                            this.auctioneer = new Auctioneer(inputStream, outputStream, genericMessage.getParams()[1]);
                            server.getAuctioneers().add(auctioneer);
                        }
                        break;

                    case MessageKeys.PRODUCT:
                        product = (Product) message;
                        target = MessageKeys.TARGET_AUCTIONEER;
                        Auctioneer auctioneer = server.searchAuctioneer(product.getOwnerName());
                        auction = new Auction(auctioneer, product);
                        auctioneer.getAuctions().add(auction);
                        server.getAuctions().add(auction);

                        outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "Auction Created Successfully"));
                        break;

                    case MessageKeys.GET_AUCTIONS:
                        outputStream.writeObject(new AuctionsContainer(server.getAuctions()));
                        break;

                    case MessageKeys.FOLLOW_AUCTION:
                        target = MessageKeys.TARGET_BUYER;
                        genericMessage = (GenericMessage) message;
                        auction = server.findAuction(genericMessage.getParams()[0]);
                        auction.addBuyer(buyer);
                        outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "You are now following "+auction.getAuthor()+" auction!"));
                        break;
                }



            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}
