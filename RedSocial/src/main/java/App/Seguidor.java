package App;

public class Seguidor {
    
    Post actualPost;
    
    public Post askForNext(){//Puedo cambiarlo por un paquete que tenga el contenido, el nombre del autor, los likes y el codigo del post
        //El servidor retorna el codigo siguiente hacerlo circular
        return null;
    }
    
    public Post askForPrev(){
        return null;
    }
    
    public void sendLike(){
        
    }
    
    public void sendFollow(){
        //La celebridad es un subcriptor del server, el server recibe el id y hace un notify a la celebridad y esta se suma un subscriptor
        //de igual manera hace con el like
        //Envia al server el server busca el id del post y le suma un follower al array
        //
    }
}
