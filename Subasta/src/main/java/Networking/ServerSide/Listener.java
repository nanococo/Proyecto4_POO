package Networking.ServerSide;

import App.Auctioneer;
import App.Oferente;
import Messages.GenericMessage;
import Messages.MessageKeys;
import Messaging.IMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Listener extends Thread {

    private final Server server;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private Auctioneer auctioneer;
    private Oferente oferente;

    public Listener(Server server, Socket clientSocket) throws IOException {
        this.server = server;
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        while (true){

            try {
                IMessage message = (IMessage) inputStream.readObject();
                GenericMessage genericMessage;


                switch (message.getKey()) {
                    case MessageKeys.SET_CLIENT_TYPE:
                        genericMessage = (GenericMessage) message;

                        if(genericMessage.getParams()[0].equals(MessageKeys.TARGET_BUYER)){
                            //this.follower = new Follower(inputStream, outputStream, server);
                            //server.getFollowers().add(follower);
                        } else if(genericMessage.getParams()[0].equals(MessageKeys.TARGET_AUCTIONEER)){
                            System.out.println("New Auctioneer: "+genericMessage.getParams()[1]);
                            this.auctioneer = new Auctioneer(inputStream, outputStream, genericMessage.getParams()[1]);
                            server.getAuctioneers().add(auctioneer);
                        }
                        break;

                }



            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}
