/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Notifications;

/**
 *
 * @author Fernando Alvarez
 */
public class PostNotification extends NotificacionSocial{
    
    private int postId;
    private int clientId;

    public PostNotification(int postId, int clientId) {
        this.postId = postId;
        this.clientId = clientId;
    }
    
    public int getPostId(){
        return postId;
    }
    
    public int getClientId(){
        return clientId;
    }
    
}
