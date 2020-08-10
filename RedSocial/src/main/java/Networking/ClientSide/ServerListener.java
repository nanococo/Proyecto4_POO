package Networking.ClientSide;

import GUI.PantallaCelebridad;
import GUI.PantallaSeguidor;
import Messaging.IMessage;
import Networking.Messages.GenericMessage;
import Networking.Messages.MessageKeys;

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
                PantallaCelebridad pantallaCelebridad;
                PantallaSeguidor pantallaSeguidor;

                switch (message.getKey()){
                    case MessageKeys.SET_FOLLOWER_POST:
                        genericMessage = (GenericMessage) message;
                        pantallaSeguidor = (PantallaSeguidor) window;
                        pantallaSeguidor.setPost(genericMessage.getParams()[0],genericMessage.getParams()[1],genericMessage.getParams()[2]);
                        break;
                    case MessageKeys.SEND_ALERT:
                        genericMessage = (GenericMessage) message;

                        if(genericMessage.getParams()[1].equals(MessageKeys.TARGET_CELEBRITY)){
                            pantallaCelebridad = (PantallaCelebridad) window;

                            if(genericMessage.getParams()[0].equals("true")){
                                pantallaCelebridad.createAlert(genericMessage.getParams()[2]);
                            } else {
                                pantallaCelebridad.createAlert(genericMessage.getParams()[3]);
                            }
                        } else {
                            pantallaSeguidor = (PantallaSeguidor) window;

                            if(genericMessage.getParams()[0].equals("true")){
                                pantallaSeguidor.createAlert(genericMessage.getParams()[2]);
                            } else {
                                pantallaSeguidor.createAlert(genericMessage.getParams()[3]);
                            }
                        }

                        break;
                    case MessageKeys.UPDATE_FOLLOWERS_COUNT:
                        genericMessage = (GenericMessage) message;
                        pantallaCelebridad = (PantallaCelebridad) window;
                        pantallaCelebridad.updateSubs(genericMessage.getParams()[0]);
                        break;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}
