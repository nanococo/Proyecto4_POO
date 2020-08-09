package RedSocial;

import Observer.IObserver;
import Observer.ISubject;

import java.util.Observer;

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

    @Override
    public void addObserver() {

    }

    @Override
    public void notifyAllSubs() {
        autor.update();
    }

    @Override
    public void notifySub(IObserver observer) {
        observer.notify();
    }

    public boolean reachedXLikes(){
        return likes%2 == 0;
    }

    public void sumarLike(){
        likes++;
        if (reachedXLikes());
            notifyAll();
    }
}
