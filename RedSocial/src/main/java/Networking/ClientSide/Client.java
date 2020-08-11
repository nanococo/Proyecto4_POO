package Networking.ClientSide;

import App.AccountTypes;
import GUI.PantallaCelebridad;
import Networking.Messages.GenericMessage;
import Networking.Messages.MessageKeys;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Client(String ip, int port, AccountTypes accountType, JFrame window){
        try {
            Socket socket = new Socket(ip, port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());

            new ServerListener(outputStream, inputStream, window).start();

            switch (accountType){
                case FOLLOWER:
                    outputStream.writeObject(new GenericMessage(MessageKeys.SET_CLIENT_TYPE, "follower"));
                    break;
                case CELEBRITY:
                    PantallaCelebridad celebrityScreen = (PantallaCelebridad) window;
                    outputStream.writeObject(new GenericMessage(MessageKeys.SET_CLIENT_TYPE, "celebrity", celebrityScreen.getNickName()));
                    break;
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }


    //CELEBRITY COMMANDS
    public void post(String content, String name){
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.POST, content, name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //FOLLOWER COMMANDS
    public void follow(String celebrityName){
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.FOLLOW, celebrityName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getNextPost() {
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.NEXT_POST));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPrevPost() {
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.PREV_POST));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void like(){
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.LIKE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
