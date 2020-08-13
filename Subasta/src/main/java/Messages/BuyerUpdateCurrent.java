package Messages;

import App.Auction;
import Messaging.BaseMessage;

public class BuyerUpdateCurrent extends BaseMessage {
    private final AuctionsInfo auctionsInfo;


    public BuyerUpdateCurrent(Auction auction) {
        super(MessageKeys.BUYER_UPDATE_CURRENT);
        this.auctionsInfo = auction.getAuctionInfo();
    }

    public AuctionsInfo getAuctionsInfo() {
        return auctionsInfo;
    }
}
