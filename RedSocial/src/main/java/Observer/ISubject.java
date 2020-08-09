package Observer;

public interface ISubject {

    public void addObserver();

    public void notifyAllSubs();

    public void notifySub(IObserver observer);

}
