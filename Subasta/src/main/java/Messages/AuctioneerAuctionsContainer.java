package Messages;

import App.Auction;
import Messaging.BaseMessage;

import java.util.ArrayList;

public class AuctioneerAuctionsContainer extends BaseMessage {
    private final ArrayList<AuctionsInfo> auctions = new ArrayList<>();
    //private final String target;

    public AuctioneerAuctionsContainer(ArrayList<Auction> auctions)  {
        super(MessageKeys.AUCTIONEER_AUCTION_CONTAINER);

        for (Auction auction : auctions) {
            this.auctions.add(auction.getAuctionInfo());
        }
    }

    public ArrayList<AuctionsInfo> getAuctionsInfo() {
        return auctions;
    }
}
