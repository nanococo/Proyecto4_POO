package App;

import Messages.AuctionsInfo;
import Messaging.IMessage;
import Observer.IObserver;
import Observer.ISubject;

import java.util.ArrayList;
import java.util.UUID;

public class Auction implements ISubject {
    
    private final Auctioneer auctioneer;
    private final Product product;
    private int tope;
    private Buyer highestBidder;
    private final ArrayList<Buyer> buyers = new ArrayList<>();
    private final AuctionStatus auctionStatus;
    private final String id = UUID.randomUUID().toString();

    public Auction(Auctioneer auctioneer, Product product) {
        this.auctioneer = auctioneer;
        this.product = product;
        this.tope = product.getPrice();
        this.auctionStatus = AuctionStatus.ACTIVE;
    }
    
    public void enviarResultadoDeSubasta(){
        //Notify
    }

    public void actualizarSubasta(){
        //Notify
    }

    public void addBuyer(Buyer buyer){
        buyers.add(buyer);
    }

    public String getId() {
        return id;
    }

    public String getAuthor(){
        return auctioneer.getName();
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    public void setHighestBidder(Buyer highestBidder) {
        this.highestBidder = highestBidder;
    }

    public void aceptarOferta(int idCliente,int cantidad){
        for (Buyer ofer: buyers){
            if(ofer.id == idCliente)
                setHighestBidder(ofer);
        }
        setTope(cantidad);
        //ActualizarSubasta Notifica
    }
    
    public AuctionsInfo getAuctionInfo(){
        return new AuctionsInfo(product, tope, auctionStatus.toString(), auctioneer.getName(), id);
    }

    @Override
    public void addObserver(IObserver observer) {

    }

    @Override
    public void notifyAllSubs(IMessage message) {

    }
}
