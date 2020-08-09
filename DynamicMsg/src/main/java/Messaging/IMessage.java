package Messaging;

import java.io.Serializable;

public interface IMessage extends Serializable {
    String getKey();
}
