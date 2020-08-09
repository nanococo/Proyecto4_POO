package App.Accounts;

import App.Notifications.NotificacionesRedSocial;
import App.Notifications.NotificationManager;
import App.Notifications.PostLikesNotification;
import App.Notifications.VipFollowersNotification;
import Observer.IObserver;

public class Follower implements IObserver {

    public Follower(){
        System.out.println("New Follower Added");
    }

    int id;

    public int getId(){
        return id;
    }

    public void update(NotificacionesRedSocial tipo) {
        switch(tipo){
            case VIPFOLLOWERS:
                NotificationManager.sendNotification(new VipFollowersNotification());
                break;
            case POSTLIKES:
                NotificationManager.sendNotification(new PostLikesNotification());
        }
    }
}
