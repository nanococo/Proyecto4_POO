package Networking.ServerSide;

import App.Auction;
import App.Accounts.Auctioneer;
import App.Accounts.Buyer;
import App.Product;
import Messages.AuctioneerAuctionsContainer;
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
                Auctioneer auctioneer;



                switch (message.getKey()) {
                    case MessageKeys.SET_CLIENT_TYPE:
                        genericMessage = (GenericMessage) message;

                        if(genericMessage.getParams()[0].equals(MessageKeys.TARGET_BUYER)){
                            this.buyer = new Buyer(inputStream, outputStream, server, genericMessage.getParams()[1]);
                            server.getBuyers().add(buyer);
                        } else if(genericMessage.getParams()[0].equals(MessageKeys.TARGET_AUCTIONEER)){
                            System.out.println("New Auctioneer: "+genericMessage.getParams()[1]);
                            this.auctioneer = new Auctioneer(inputStream, outputStream, genericMessage.getParams()[1]);
                            server.getAuctioneers().add(this.auctioneer);
                        }
                        break;

                    case MessageKeys.PRODUCT:
                        product = (Product) message;
                        target = MessageKeys.TARGET_AUCTIONEER;
                        auctioneer = server.searchAuctioneer(product.getOwnerName());
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
                        buyer.addAuction(auction);
                        outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "You are now following "+auction.getAuthor()+" auction!"));
                        break;

                    case MessageKeys.ADD_BID:
                        target = MessageKeys.TARGET_BUYER;
                        genericMessage = (GenericMessage) message;
                        auction = server.findAuction(genericMessage.getParams()[0]);

                        if(auction.isBuyerInAuction(genericMessage.getParams()[2])){

                            if(auction.validateBidAmount(genericMessage.getParams()[1])){
                                auction.getAuctioneer().showOfferToAuctioneer(genericMessage.getParams()[0], genericMessage.getParams()[1]);
                                outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "Bid sent successfully..."));
                            } else {
                                outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "Your amount is lower than the current top!"));
                            }

                        } else {
                            outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "You have to join the auction first!!"));
                        }
                        break;

                    case MessageKeys.APPROVE_BID:
                        target = MessageKeys.TARGET_BUYER;
                        genericMessage = (GenericMessage) message;
                        auction = server.findAuction(genericMessage.getParams()[0]);
                        auction.acceptBid(genericMessage.getParams()[1]);
                        auction.notifyAllSubs(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "New highest bid for auction on: "+genericMessage.getParams()[1]));
                        break;

                    case MessageKeys.GET_AUCTIONEER_AUCTIONS:
                        genericMessage = (GenericMessage) message;
                        auctioneer = server.searchAuctioneer(genericMessage.getParams()[0]);
                        outputStream.writeObject(new AuctioneerAuctionsContainer(auctioneer.getAuctions()));
                        break;

                    case MessageKeys.GET_BUYER_AUCTIONS:
                        outputStream.writeObject(new AuctionsContainer(buyer.getAuctions()));
                        break;
                }



            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}
