package App;

import App.Accounts.Celebrity;
import App.Notifications.NotificacionesRedSocial;

public class Post {

    private final int id;
    private final Celebrity author;
    private final String content;
    private int likes;
    final int NOTIFICAR = 2;
    



    Post(Celebrity author, String contenido, int id){
        this.content = contenido;
        this.likes = 0;
        this.id = id;
        this.author = author;
    }

    public boolean reachedXLikes(){
        return likes%2 == 0;
    }

    public void sumarLike(){
        likes++;
        if (reachedXLikes());
            author.sendNotification(NotificacionesRedSocial.POSTLIKES);
    }

    public String getNombreAutor() {
        return this.author.nombre;
    }

    public int getLikes(){return  this.likes;}

    public String getContent(){return this.content;}

    public int getId() {
        return this.id;
    }
    
    public boolean isAutor(Celebrity celebrity){
        return author == celebrity;
    }
}
