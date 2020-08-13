package App;

import App.Accounts.Auctioneer;
import App.Accounts.Buyer;
import Messages.AuctionsInfo;
import Messages.GenericMessage;
import Messages.MessageKeys;
import Messaging.IMessage;
import Observer.IObserver;
import Observer.ISubject;

import java.util.ArrayList;
import java.util.UUID;

public class Auction implements ISubject {
    
    private final Auctioneer auctioneer;
    private final Product product;
    private int topBid;
    private Buyer highestBidder;
    private final ArrayList<Buyer> buyers = new ArrayList<>();
    private AuctionStatus auctionStatus;
    private final String id = UUID.randomUUID().toString();

    public Auction(Auctioneer auctioneer, Product product) {
        this.auctioneer = auctioneer;
        this.product = product;
        this.topBid = product.getPrice();
        this.auctionStatus = AuctionStatus.ACTIVE;
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public void addBuyer(Buyer buyer){
        buyers.add(buyer);
    }

    public String getId() {
        return id;
    }

    public Auctioneer getAuctioneer() {
        return auctioneer;
    }

    public String getAuthor(){
        return auctioneer.getName();
    }

    public void setTopBid(int topBid) {
        this.topBid = topBid;
    }

    public void setHighestBidder(Buyer highestBidder) {
        this.highestBidder = highestBidder;
    }

    public void acceptBid(String bid, Buyer buyer){
        highestBidder = buyer;
        setTopBid(Integer.parseInt(bid));
    }

    public boolean validateBidAmount(String amount){
        return Integer.parseInt(amount)> topBid;
    }
    
    public AuctionsInfo getAuctionInfo(){
        if(highestBidder!=null){
            return new AuctionsInfo(product, topBid, auctionStatus.toString(), auctioneer.getName(), id, highestBidder.getId());
        } else {
            return new AuctionsInfo(product, topBid, auctionStatus.toString(), auctioneer.getName(), id, "");
        }
    }

    @Override
    public void addObserver(IObserver observer) {

    }

    @Override
    public void notifyAllSubs(IMessage message) {
        for (Buyer buyer : buyers) {
            buyer.sendMessage(message);
        }
    }

    public boolean isBuyerInAuction(String buyerId) {
        for (Buyer buyer : buyers) {
            if(buyer.getId().equals(buyerId)){
                return true;
            }
        }
        return false;
    }

    public void cancel() {
        this.auctionStatus = AuctionStatus.CANCELLED;
        notifyAllSubs(new GenericMessage(MessageKeys.SEND_ALERT, "true",MessageKeys.TARGET_BUYER, "Auction: "+getId()+" has been canceled"));
    }

    public void close() {
        this.auctionStatus = AuctionStatus.CLOSED;
        notifyAllSubs(new GenericMessage(MessageKeys.SEND_ALERT, "true",MessageKeys.TARGET_BUYER, "Auction: "+getId()+" is now closed!"));
    }
}
