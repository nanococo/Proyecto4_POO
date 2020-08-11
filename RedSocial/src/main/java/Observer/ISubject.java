package Observer;

import Messaging.IMessage;

public interface ISubject {

    void addObserver(IObserver observer);

    void notifyAllSubs(IMessage message);

}
