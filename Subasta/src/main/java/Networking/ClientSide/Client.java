package Networking.ClientSide;

import App.Articulo;
import App.Notification.AccountTypes;
import Messages.GenericMessage;
import Messages.MessageKeys;

import javax.swing.*;
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
                    //PantallaCelebridad celebrityScreen = (PantallaCelebridad) window;
                    outputStream.writeObject(new GenericMessage(MessageKeys.SET_CLIENT_TYPE, MessageKeys.TARGET_AUCTIONEER));
                    //outputStream.writeObject(new GenericMessage(MessageKeys.SET_CLIENT_TYPE, "celebrity", celebrityScreen.getNickName()));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAuction(Articulo articulo) {

    }
}
