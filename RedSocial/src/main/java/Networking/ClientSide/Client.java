package Networking.ClientSide;

import App.AccountTypes;
import Networking.Messages.GenericMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Client(String ip, int port, AccountTypes accountType){
        try {
            this.socket = new Socket(ip, port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());

            new ServerListener(outputStream, inputStream).start();

            switch (accountType){
                case FOLLOWER:
                    outputStream.writeObject(new GenericMessage("setClientType", "follower"));
                    break;
                case CELEBRITY:
                    outputStream.writeObject(new GenericMessage("setClientType", "celebrity"));
                    break;
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendHelloToClient(){

    }

    public void follow(){

    }

}
