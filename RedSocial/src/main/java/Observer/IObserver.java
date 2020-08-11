package Observer;

import Messaging.IMessage;

public interface IObserver {

    void update(IMessage message);
}
