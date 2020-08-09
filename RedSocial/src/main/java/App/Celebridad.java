package RedSocial;

import Observer.IObserver;
import Observer.ISubject;

import java.util.ArrayList;

public class Celebridad implements IObserver, ISubject {

    ArrayList<Post> posts;
    ArrayList<IObserver> seguidores;
    final int NOTIFICAR = 2;

    @Override
    public void update() {
        //Recibe las notificaciones de manera diferente
    }

    @Override
    public void addObserver() {

    }

    @Override
    public void notifyAllSubs() {

    }

    @Override
    public void notifySub(IObserver observer) {

    }

    public void postearMensaje(){

    }

    public void darseDeBaja(){

    }

    public void addFolower(){
        if(reachedXFollowers())
            notifyAllSubs();
    }

    public boolean reachedXFollowers(){
        return seguidores.size()%2 == 0;
    }


}
