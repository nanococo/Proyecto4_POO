package App.Accounts;


import App.Auction;
import App.Product;
import Messages.GenericMessage;
import Messages.MessageKeys;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Auctioneer {

    private final ArrayList<Auction> auctions = new ArrayList<>(); //Enviados al subastador
    private final String name;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public Auctioneer(ObjectInputStream inputStream, ObjectOutputStream outputStream, String name) {
        this.name = name;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }

    public String getName() {
        return name;
    }

    public void aceptarOferta(int idSubasta,int tope,int clientId){
//        for (Auction auction : auctions){
//            if(auction.getId() == idSubasta)
//                auction.aceptarOferta(clientId,tope);
//        }
    }

    public void subastar(Product product){
        //Auction sub = new Auction(this, product,CasaDeSubastas.getCurrentSubastaId());
//        auctions.add(sub);
//        CasaDeSubastas.addSubasta(sub);
    }

    public void cerrarSubasta(){

    }

    public void cancelarSubasta(){

    }

    public void showOfferToAuctioneer(String id, String amount, String buyerId){
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.SHOW_AUCTION_PROPOSAL, id, amount, buyerId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
