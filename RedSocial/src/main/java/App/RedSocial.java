package App;


import App.Accounts.Celebrity;
import App.Accounts.Follower;
import App.Notifications.NewPostNotification;
import App.Notifications.NotificacionSocial;
import App.Notifications.NotificacionesRedSocial;
import App.Notifications.NotificationManager;
import App.Notifications.PostNotification;
import java.util.ArrayList;

    public class RedSocial {

        ArrayList<Celebrity> celebridades;
        ArrayList<Post> posts;//
        ArrayList<Follower> seguidores;
        int postConsecutive = 1;//Si es 0 es que aun no esta asignado
        int seguidorConsecutive = 1;
        int celebridadConsecutive = 1;


    public Post crearPost(NotificacionSocial notificacion){
        NewPostNotification not = (NewPostNotification) notificacion;
        Celebrity celebrity = searchCelebrrity(not.getCelebId());
        Post post = new Post(celebrity,not.getContenido(),postConsecutive++);
        celebrity.addPost(post);
        posts.add(post);
        return post;
    }

    public Celebrity agregarCelebridad(String nombre){//Se envia el id que asigne esta clase o el servidor
        Celebrity celebrity = new Celebrity(nombre, celebridadConsecutive++);
        celebridades.add(celebrity);
        return celebrity;
    }
    
    public Follower agregarSeguidor(Celebrity celebrity, int id){//Se puede asignar un id como nulo hasta que sea asignado por el servidor
        Follower seg = searchSeguidor(id);
        if (seg.equals(null)) {
            seg = new Follower();//Aca es la asignacion del id
        }
        celebrity.addFollower(seg);
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
        Celebrity celebrity = searchCelebrrity(not.getPostId());
        agregarSeguidor(celebrity,not.getClientId());
    }
    
    public Follower searchSeguidor(int id){
        for (Follower seguidore : seguidores) {
            if(seguidore.getId() == id)
                return seguidore;
        }
        return null;
    }
    
    public Celebrity searchCelebrrity(int id){
        Post post = searchPost(id);
        for (Celebrity celebridade : celebridades) {
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
