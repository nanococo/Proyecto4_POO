package App;

import App.Accounts.Celebrity;
import Networking.Messages.GenericMessage;
import Networking.Messages.MessageKeys;

public class Post {

    private final Celebrity author;
    private final String content;
    private int likes;
    



    public Post(Celebrity author, String content){
        this.content = content;
        this.likes = 0;
        this.author = author;
        System.out.println("Post created successfully");
    }

    public boolean reachedXLikes(){
        return likes%10 == 0;
    }

    public void incLike(){
        likes++;
        if(reachedXLikes()){
            System.out.println("LIKES REACHED");
            author.notifyAllSubs(new GenericMessage(MessageKeys.SEND_ALERT, "true",MessageKeys.TARGET_FOLLOWER, author.getName()+" post has reached "+this.likes+" likes!"));
        }
    }

    public String getAuthorName() {
        return this.author.getName();
    }

    public int getLikes(){return  this.likes;}

    public String getContent(){return this.content;}
}
