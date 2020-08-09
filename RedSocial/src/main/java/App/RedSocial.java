package App;

import App.Notifications.NewPostNotification;
import App.Notifications.NotificacionSocial;
import App.Notifications.NotificacionesRedSocial;
import App.Notifications.NotificationManager;
import App.Notifications.PostNotification;
import java.util.ArrayList;

    public class RedSocial {

        ArrayList<Celebridad> celebridades;
        ArrayList<Post> posts;//
        ArrayList<Seguidor> seguidores;
        int postConsecutive = 1;//Si es 0 es que aun no esta asignado
        int seguidorConsecutive = 1;
        int celebridadConsecutive = 1;


    public Post crearPost(NotificacionSocial notificacion){
        NewPostNotification not = (NewPostNotification) notificacion;
        Celebridad celebridad = searchCelebrrity(not.getCelebId());
        Post post = new Post(celebridad,not.getContenido(),postConsecutive++);
        celebridad.addPost(post);
        posts.add(post);
        return post;
    }

    public Celebridad agregarCelebridad(String nombre){//Se envia el id que asigne esta clase o el servidor
        Celebridad celebridad = new Celebridad(nombre, celebridadConsecutive++);
        celebridades.add(celebridad);
        return celebridad;
    }
    
    public Seguidor agregarSeguidor(Celebridad celebridad,int id){//Se puede asignar un id como nulo hasta que sea asignado por el servidor
        Seguidor seg = searchSeguidor(id);
        if (seg.equals(null)) {
            seg = new Seguidor();//Aca es la asignacion del id
        }
        celebridad.addFollower(seg);
        seguidores.add(seg);
        return seg;
    }
    
    public void addLikeToPost(NotificacionSocial notification){
        PostNotification not = (PostNotification)notification;
        Post post = searchPost(not.getPostId());
        post.sumarLike();
    }
    
    public void addFollower(NotificacionSocial notification){
        PostNotification not = (PostNotification)notification;
        Celebridad celebridad = searchCelebrrity(not.getPostId());
        agregarSeguidor(celebridad,not.getClientId());
    }
    
    public Seguidor searchSeguidor(int id){
        for (Seguidor seguidore : seguidores) {
            if(seguidore.getId() == id)
                return seguidore;
        }
        return null;
    }
    
    public Celebridad searchCelebrrity(int id){
        Post post = searchPost(id);
        for (Celebridad celebridade : celebridades) {
            if(post.isAutor(celebridade))
                return celebridade;
        }
        return null;
    }
    
    public Post searchPost(int id){
        for (Post post : posts) {
            if(id == post.getId())
                return post;
        }
        return null;
    }
    
    private void sendPrevPost(NotificacionSocial notification) {
        PostNotification not = (PostNotification) notification;
        int postId = not.getPostId();
        if(postId != 0)
            postId--;
        else
            postId = posts.size();
        NotificationManager.sendNotification(new PostNotification(postId, not.getClientId()));
    }

    private void sendNextPost(NotificacionSocial notification) {
        PostNotification not = (PostNotification) notification;
        int postId = not.getPostId();
        if(postId != posts.size())
            postId--;
        else
            postId = 0;
        NotificationManager.sendNotification(new PostNotification(postId, not.getClientId()));
    }
    
    public void receiveRequest(NotificacionesRedSocial requestType,NotificacionSocial notification){
        switch(requestType){
            case LIKE:
                addLikeToPost(notification);
                break;
            case SUBSCRIPTION:
                addFollower(notification);
                break;
            case POST:
                crearPost(notification);
                break;
            case PREVPOST:
                sendPrevPost(notification);
                break;
            case NEXTPOST:
                sendNextPost(notification);
                break;
        }
    }


}
