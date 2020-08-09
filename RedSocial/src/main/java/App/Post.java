package App;

import Observer.IObserver;
import Observer.ISubject;

public class Post {

    int id;
    private Celebridad autor;
    String contenido;
    int likes;
    final int NOTIFICAR = 2;



    Post(String contenido){
        this.contenido = contenido;
        this.likes = 0;
    }



    public boolean reachedXLikes(){
        return likes%2 == 0;
    }

    public void sumarLike(){
        likes++;
        if (reachedXLikes());
            autor.
    }

    public String getNombreAutor() {
        return this.autor.nombre;
    }
}
