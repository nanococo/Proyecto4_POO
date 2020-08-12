package Networking.ClientSide;

import App.Product;
import App.Accounts.AccountTypes;
import GUI.PantallaOferente;
import GUI.PantallaSubastador;
import Messages.GenericMessage;
import Messages.MessageKeys;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Client(String ip, int port, AccountTypes accountType, JFrame window) {
        try {
            Socket socket = new Socket(ip, port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());

            new ServerListener(outputStream, inputStream, window).start();

            switch (accountType){
                case BUYER:
                    outputStream.writeObject(new GenericMessage(MessageKeys.SET_CLIENT_TYPE, MessageKeys.TARGET_BUYER, ((PantallaOferente) window).getId()));
                    break;
                case AUCTIONEER:
                    outputStream.writeObject(new GenericMessage(MessageKeys.SET_CLIENT_TYPE, MessageKeys.TARGET_AUCTIONEER, ((PantallaSubastador) window).getNickName()));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAuction(Product product) {
        try {
            outputStream.writeObject(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllAuctions(){
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.GET_AUCTIONS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribeToAuction(String id) {
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.FOLLOW_AUCTION, id));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBid(String bid, String id, String buyerId) {
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.ADD_BID, id, bid, buyerId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void approveOffer(String id, String newBid) {
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.APPROVE_BID, id, newBid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAuctioneerAuctions(String nickName) {
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.GET_AUCTIONEER_AUCTIONS, nickName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getBuyerAuctions() {
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.GET_BUYER_AUCTIONS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
