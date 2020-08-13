package Messages;

import App.Auction;
import Messaging.BaseMessage;

public class AuctioneerUpdateContent extends BaseMessage {
    private final AuctionsInfo auctionsInfo;


    public AuctioneerUpdateContent(Auction auction) {
        super(MessageKeys.AUCTIONEER_UPDATE_CURRENT);
        this.auctionsInfo = auction.getAuctionInfo();
    }

    public AuctionsInfo getAuctionsInfo() {
        return auctionsInfo;
    }
}
