package App.Accounts;

import App.Post;
import Messaging.IMessage;
import Networking.Messages.GenericMessage;
import Networking.Messages.MessageKeys;
import Observer.IObserver;
import Observer.ISubject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Celebrity implements ISubject {

    private final ArrayList<Post> posts = new ArrayList<>();
    private final ArrayList<Follower> followers = new ArrayList<>();
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    private final String name;

    public Celebrity(ObjectInputStream inputStream, ObjectOutputStream outputStream, String name){
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void darseDeBaja(){

    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addFollower(Follower follower){
        this.followers.add(follower);
        try {
            outputStream.writeObject(new GenericMessage(MessageKeys.UPDATE_FOLLOWERS_COUNT, String.valueOf(followers.size())));
            if(reachedXFollowers())
                notifyAllSubs(new GenericMessage(MessageKeys.SEND_ALERT, "true",MessageKeys.TARGET_FOLLOWER, this.name+" has reached "+this.followers.size()+" followers!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasFollower(Follower follower){
        return followers.contains(follower);
    }

    public boolean reachedXFollowers(){
        return followers.size()%10 == 0;
    }


    @Override
    public void addObserver(IObserver observer) {
        //this.seguidores.add(observer);
    }

    @Override
    public void notifyAllSubs(IMessage message) {
        for (IObserver observer: followers){
            observer.update(message);//Se avisa a todos los observers y estos usan el servidor para enviar el tipo de notificacion
        }
    }
}
