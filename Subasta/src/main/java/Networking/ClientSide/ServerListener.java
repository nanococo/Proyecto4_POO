package Networking.ClientSide;

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

            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }


}
