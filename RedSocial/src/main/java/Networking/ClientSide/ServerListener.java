package Networking.ClientSide;

import Messaging.IMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerListener extends Thread {

    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public ServerListener(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }


    @Override
    public void run() {

        while (true){

            try {
                IMessage message = (IMessage) inputStream.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}
