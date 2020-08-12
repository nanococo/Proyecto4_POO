package App;

import App.Accounts.Auctioneer;
import Messages.AuctionsInfo;
import java.util.ArrayList;

public class CasaDeSubastas {

    static ArrayList<Auction> auctions;//Enviadas a todos los clientes
    ArrayList<Auctioneer> subastadores;
    static int currentSubastaId;

    public static void addSubasta(Auction sub) {
        auctions.add(sub);
    }

    public ArrayList<AuctionsInfo> subastasAEnviar(){
        ArrayList<AuctionsInfo> auctionsInfos = new ArrayList<>();
//        for (Auction auction : auctions) {
//            infoSubastas.add(auction.toInfo());
//        }
        return auctionsInfos;
    }
    
    public Auction getSubasta(AuctionsInfo info){
//        for (Auction auction : auctions) {
//            if(info.getId() == auction.getId()){
//                return auction;
//            }
//        }
        return null;
    }

    public static int getCurrentSubastaId(){
        return currentSubastaId++;
    }
}
