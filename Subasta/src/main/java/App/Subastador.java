package App;

import Observer.IObserver;
import Observer.ISubject;
import Subastas.Subasta;

import java.util.ArrayList;

public class Subastador implements IObserver, ISubject {

    Subasta subasta;
    ArrayList<IObserver> oferentes;

    public void aceptarOferta(){

    }

    public void subastar(Subasta subasta){

    }

    public void cerrarSubasta(){

    }

    public void cancelarSubasta(){

    }

    public void enviarResultadoDeSubasta(){

    }

    @Override
    public void update() {

    }

    @Override
    public void addObserver() {

    }

    @Override
    public void notifyAllSubs() {

    }

    @Override
    public void notifySub(IObserver observer) {
        observer.update();
    }
}
