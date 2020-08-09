package App;

import App.Notifications.NotificacionesRedSocial;

public class Post {

    private int id;
    private Celebridad autor;
    private String contenido;
    private int likes;
    final int NOTIFICAR = 2;
    



    Post(Celebridad autor,String contenido,int id){
        this.contenido = contenido;
        this.likes = 0;
        this.id = id;
        this.autor = autor;
    }

    public boolean reachedXLikes(){
        return likes%2 == 0;
    }

    public void sumarLike(){
        likes++;
        if (reachedXLikes());
            autor.sendNotification(NotificacionesRedSocial.POSTLIKES);
    }

    public String getNombreAutor() {
        return this.autor.nombre;
    }

    public int getLikes(){return  this.likes;}

    public String getContenido(){return this.contenido;}

    public int getId() {
        return this.id;
    }
    
    public boolean isAutor(Celebridad celebridad){
        return autor == celebridad;
    }
}
