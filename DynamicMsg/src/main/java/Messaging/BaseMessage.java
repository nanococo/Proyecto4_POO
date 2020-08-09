package Messaging;

public abstract class BaseMessage implements IMessage {
    private final String KEY;

    @Override
    public String getKey(){
        return this.KEY;
    }

    public BaseMessage(String key){
        this.KEY = key;
    }
}
