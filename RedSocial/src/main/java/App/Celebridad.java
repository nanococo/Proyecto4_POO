package App;

import App.Notifications.NotificacionesRedSocial;
import Observer.IObserver;
import Observer.ISubject;

import java.util.ArrayList;

public class Celebridad implements ISubject{

    ArrayList<Post> posts;
    ArrayList<IObserver> seguidores;
    final int NOTIFICAR = 2;
    String nombre;
    int id;//Con el cual es buscado por el servidor

    Celebridad(String nombre,int id){
        this.nombre = nombre;
        this.posts = new ArrayList<Post>();
        this.seguidores = new ArrayList<IObserver>();
        this.id = id;
    }

    public void addPost(Post post){
        posts.add(post);
    }

    public void darseDeBaja(){

    }

    public void addFollower(IObserver observer){
        this.seguidores.add(observer);
        if(reachedXFollowers())
            notifyAllSubs(NotificacionesRedSocial.VIPFOLLOWERS);
    }

    public boolean reachedXFollowers(){
        return seguidores.size()%2 == 0;
    }


    public void addObserver(IObserver observer) {
        this.seguidores.add(observer);
    }

    public void notifyAllSubs(NotificacionesRedSocial tipo) {
        for (IObserver observer:seguidores){
            observer.update(tipo);//Se avisa a todos los observers y estos usan el servidor para enviar el tipo de notificacion
        }
    }

    
    public void sendNotification(NotificacionesRedSocial tipo){
        notifyAllSubs(tipo);
    }

}
