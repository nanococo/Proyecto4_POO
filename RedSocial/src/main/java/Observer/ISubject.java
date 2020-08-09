package Observer;

import App.Notifications.NotificacionesRedSocial;

public interface ISubject {

    public void addObserver(IObserver observer);

    public void notifyAllSubs(NotificacionesRedSocial tipo);

}
