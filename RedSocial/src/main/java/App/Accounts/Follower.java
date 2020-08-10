package App.Accounts;

import App.Notifications.*;
import App.Post;
import Networking.Messages.GenericMessage;
import Networking.Messages.MessageKeys;
import Networking.ServerSide.Server;
import Observer.IObserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Follower implements IObserver {

    private int currentPostIndex = 0;

    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private final Server server;

    public Follower(ObjectInputStream inputStream, ObjectOutputStream outputStream, Server server){
        this.server = server;
        this.outputStream = outputStream;
        this.inputStream = inputStream;

        System.out.println("New Follower Added");

        if(server.getPosts().size()!=0){

            Post post = server.getPosts().get(0);

            try {
                outputStream.writeObject(new GenericMessage(MessageKeys.SET_FOLLOWER_POST, post.getContent(), post.getAuthorName(), String.valueOf(post.getLikes())));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public int getCurrentPostIndex(){
        return currentPostIndex;
    }

    public void incCurrentIndex(){
        currentPostIndex++;
        if(currentPostIndex>=server.getPosts().size()){
            currentPostIndex = 0;
        }
    }

    public void decCurrentIndex(){
        currentPostIndex--;
        if(currentPostIndex<0){
            currentPostIndex = server.getPosts().size()-1;
        }
    }

    public void update(NotificacionesRedSocial tipo) {
        switch(tipo){
            case VIPFOLLOWERS:
                NotificationManager.sendNotification(new VipFollowersNotification());
                break;
            case POSTLIKES:
                NotificationManager.sendNotification(new PostLikesNotification());
        }
    }
}
