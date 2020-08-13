package Networking.ClientSide;

import GUI.PantallaOferente;
import GUI.PantallaSubastador;
import Messages.*;
import Messaging.IMessage;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerListener extends Thread {

    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private final JFrame window;

    public ServerListener(ObjectOutputStream outputStream, ObjectInputStream inputStream, JFrame window) {
        this.window = window;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {

        while (true){
            try {
                IMessage message = (IMessage) inputStream.readObject();
                GenericMessage genericMessage;
                PantallaSubastador pantallaSubastador;
                PantallaOferente pantallaOferente;


                switch (message.getKey()){
                    case MessageKeys.SEND_ALERT:
                        genericMessage = (GenericMessage) message;

                        if(genericMessage.getParams()[1].equals(MessageKeys.TARGET_AUCTIONEER)){
                            pantallaSubastador = (PantallaSubastador) window;

                            if(genericMessage.getParams()[0].equals("true")){
                                pantallaSubastador.showNotification(genericMessage.getParams()[2]);
                            } else {
                                 pantallaSubastador.showNotification(genericMessage.getParams()[3]);
                            }
                        } else {
                            pantallaOferente = (PantallaOferente) window;

                            if(genericMessage.getParams()[0].equals("true")){
                                pantallaOferente.showNotification(genericMessage.getParams()[2]);
                            } else {
                                pantallaOferente.showNotification(genericMessage.getParams()[3]);
                            }
                        }
                        break;
                    case MessageKeys.AUCTION_CONTAINER:
                        assert window instanceof PantallaOferente;
                        assert message instanceof AuctionsContainer;

                        System.out.println("Got Auctions Info");
                        ((PantallaOferente) window).setAuctionsInfos(((AuctionsContainer) message).getAuctionsInfo());
                        break;

                    case MessageKeys.SHOW_AUCTION_PROPOSAL:
                        genericMessage = (GenericMessage) message;
                        ((PantallaSubastador) window).getNotificationFromNewBid(genericMessage.getParams()[1], genericMessage.getParams()[0], genericMessage.getParams()[2]);
                        break;

                    case MessageKeys.AUCTIONEER_AUCTION_CONTAINER:
                        assert window instanceof PantallaSubastador;
                        assert message instanceof AuctioneerAuctionsContainer;

                        ((PantallaSubastador) window).setAuctionsInfos(((AuctioneerAuctionsContainer) message).getAuctionsInfo());
                        break;

                    case MessageKeys.BUYER_UPDATE_CURRENT:
                        assert window instanceof PantallaOferente;
                        assert message instanceof BuyerUpdateCurrent;

                        pantallaOferente = (PantallaOferente) window;
                        BuyerUpdateCurrent buyerUpdateCurrent = (BuyerUpdateCurrent) message;

                        if(pantallaOferente.getCurrentAuctionInfo()!=null){
                            if(pantallaOferente.getCurrentAuctionInfo().getId().equals(buyerUpdateCurrent.getAuctionsInfo().getId())){
                                pantallaOferente.showAuction(buyerUpdateCurrent.getAuctionsInfo());
                            }
                        }

                        break;

                    case MessageKeys.AUCTIONEER_UPDATE_CURRENT:
                        assert window instanceof PantallaSubastador;
                        assert message instanceof AuctioneerUpdateContent;

                        pantallaSubastador = (PantallaSubastador) window;
                        AuctioneerUpdateContent auctioneerUpdateContent = (AuctioneerUpdateContent) message;


                        if(pantallaSubastador.getCurrentAuctionInfo()!=null){
                            if(pantallaSubastador.getCurrentAuctionInfo().getId().equals(auctioneerUpdateContent.getAuctionsInfo().getId())){
                                pantallaSubastador.showAuction(auctioneerUpdateContent.getAuctionsInfo());
                            }
                        }


                        break;
                }

            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }


}
