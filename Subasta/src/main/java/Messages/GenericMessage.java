package Messages;

import Messaging.BaseMessage;

public class GenericMessage extends BaseMessage {

    private final String[] params;

    public GenericMessage(String key, String... params) {
        super(key);
        this.params = params;
    }


    public String[] getParams(){
        return this.params;
    }

}
