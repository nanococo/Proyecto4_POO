package Networking.ServerSide;

import App.Accounts.Celebrity;
import App.Accounts.Follower;
import App.Post;
import Messaging.IMessage;
import Networking.Messages.GenericMessage;
import Networking.Messages.MessageKeys;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Listener extends Thread {

    private final Server server;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private Follower follower;
    private Celebrity celebrity;

    public Listener(Server server, Socket clientSocket) throws IOException {
        this.server = server;
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        while (true){
            String target = "";
            try{

                IMessage message = (IMessage) inputStream.readObject();
                GenericMessage genericMessage;
                Post post;

                switch (message.getKey()){
                    case MessageKeys.SET_CLIENT_TYPE:
                        genericMessage = (GenericMessage) message;

                        if(genericMessage.getParams()[0].equals(MessageKeys.TARGET_FOLLOWER)){
                            this.follower = new Follower(inputStream, outputStream, server);
                            server.getFollowers().add(follower);
                        } else if(genericMessage.getParams()[0].equals(MessageKeys.TARGET_CELEBRITY)){
                            System.out.println("New Celebrity: "+genericMessage.getParams()[1]);
                            this.celebrity = new Celebrity(inputStream, outputStream, genericMessage.getParams()[1]);
                            server.getCelebrities().add(celebrity);
                        }
                        break;

                    case MessageKeys.POST:
                        target = MessageKeys.TARGET_CELEBRITY;
                        genericMessage = (GenericMessage) message;
                        post = new Post(server.searchCelebrity(genericMessage.getParams()[1]), genericMessage.getParams()[0]);
                        server.getPosts().add(post);
                        celebrity.getPosts().add(post);
                        outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "Post created successfully!"));
                        celebrity.notifyAllSubs(new GenericMessage(MessageKeys.SEND_ALERT, "true",MessageKeys.TARGET_FOLLOWER, celebrity.getName()+" has made a new post!"));
                        break;

                    case MessageKeys.FOLLOW:
                        if(server.getPosts().size()>0){
                            target = MessageKeys.TARGET_FOLLOWER;
                            genericMessage = (GenericMessage) message;
                            Celebrity celebrity = server.searchCelebrity(genericMessage.getParams()[0]);
                            if(!celebrity.hasFollower(follower)){
                                celebrity.addFollower(follower);
                                outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "Followed Celebrity Successfully"));
                            } else {
                                outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "You already follow this celebrity!"));
                            }

                        }
                        break;

                    case MessageKeys.NEXT_POST:
                        if(server.getPosts().size()>0){
                            target = MessageKeys.TARGET_FOLLOWER;
                            follower.incCurrentIndex();
                            post = server.getPosts().get(follower.getCurrentPostIndex());
                            outputStream.writeObject(new GenericMessage(MessageKeys.SET_FOLLOWER_POST, post.getContent(), post.getAuthorName(), String.valueOf(post.getLikes())));
                        }
                        break;

                    case MessageKeys.PREV_POST:
                        if(server.getPosts().size()>0){
                            target = MessageKeys.TARGET_FOLLOWER;
                            follower.decCurrentIndex();
                            post = server.getPosts().get(follower.getCurrentPostIndex());
                            outputStream.writeObject(new GenericMessage(MessageKeys.SET_FOLLOWER_POST, post.getContent(), post.getAuthorName(), String.valueOf(post.getLikes())));
                        }
                        break;

                    case MessageKeys.LIKE:
                        if(server.getPosts().size()>0){
                            target = MessageKeys.TARGET_FOLLOWER;
                            post = server.getPosts().get(follower.getCurrentPostIndex());
                            post.incLike();
                        }
                        break;

                    case MessageKeys.GO_DOWN:
                        target = MessageKeys.TARGET_CELEBRITY;
                        celebrity.darseDeBaja();
                        outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "true",target, "You have gone down :c"));
                        break;

                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception f){
                try {
                    f.printStackTrace();
                    outputStream.writeObject(new GenericMessage(MessageKeys.SEND_ALERT, "false", target, "Failed to proccess request"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
