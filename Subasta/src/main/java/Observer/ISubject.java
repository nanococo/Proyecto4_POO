package Observer;

public interface ISubject {
    
    public void notifyAllObservers();
    
    public void notifyOne(IObserver oberver);
    
    public void addObserver();
    
}
