package App;

import Observer.IObserver;
import Observer.ISubject;

public class Post implements ISubject {

    int id;
    IObserver autor;
    String contenido;
    int likes;
    final int NOTIFICAR = 2;



    Post(IObserver observer,String contenido){
        this.autor = observer;
        this.contenido = contenido;
        this.likes = 0;
    }



    public boolean reachedXLikes(){
        return likes%2 == 0;
    }

    public void sumarLike(){
        likes++;
        if (reachedXLikes());
            notifyAll();
    }

    public void addObserver() {

    }

    public void notifyAllSubs() {

    }

    public void notifySub(IObserver observer) {

    }
}
