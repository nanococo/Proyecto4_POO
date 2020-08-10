package App;

import App.Accounts.Celebrity;
import App.Notifications.NotificacionesRedSocial;

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
        return likes%2 == 0;
    }

    public void incLike(){
        likes++;
        if(reachedXLikes()) author.sendNotification(NotificacionesRedSocial.POSTLIKES);
    }

    public String getAuthorName() {
        return this.author.getName();
    }

    public int getLikes(){return  this.likes;}

    public String getContent(){return this.content;}
    
    public boolean isAutor(Celebrity celebrity){
        return author == celebrity;
    }
}
