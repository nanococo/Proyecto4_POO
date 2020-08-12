package Networking.ClientSide;

import App.Product;
import App.Notification.AccountTypes;
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
                    outputStream.writeObject(new GenericMessage(MessageKeys.SET_CLIENT_TYPE, MessageKeys.TARGET_BUYER));
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
}
