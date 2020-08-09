package Networking.ServerSide;

import App.Accounts.Follower;
import Messaging.IMessage;
import Networking.Messages.GenericMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Listener extends Thread {

    private final Server server;
    private final Socket clientSocket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;


    public Listener(Server server, Socket clientSocket) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        while (true){
            try{

                GenericMessage message = (GenericMessage) inputStream.readObject();

                switch (message.getKey()){
                    case "setClientType":
                        if(message.getParams()[0].equals("follower")){
                            server.getFollowers().add(new Follower());
                        } else if(message.getParams()[0].equals("celebrity")){
                            server.getFollowers().add(new Follower());
                        }
                        break;

                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
