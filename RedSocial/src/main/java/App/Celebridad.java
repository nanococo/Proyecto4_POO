package App;

import Observer.IObserver;
import Observer.ISubject;

import java.util.ArrayList;

public class Celebridad implements IObserver, ISubject {

    ArrayList<Post> posts;
    ArrayList<IObserver> seguidores;
    final int NOTIFICAR = 2;
    String nombre;



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


    public void update() {

    }

    public void addObserver() {

    }

    public void notifyAllSubs() {

    }

    public void notifySub(IObserver observer) {

    }
    
    public void sendNotification(NotificacionesRedSocial tipo){
        
    }
}
