package App;

import App.Subastador;
import GUI.InfoSubasta;
import Observer.IObserver;
import Observer.ISubject;
import java.util.ArrayList;

public class Subasta implements ISubject{
    
    Subastador subastador;
    Articulo articulo;
    int tope;
    Oferente mejorPuja;
    ArrayList<Oferente> oferentes;
    int id;
    EstadoSubasta estado;

    public Subasta(Subastador subastador,Articulo articulo) {
        
        this.subastador = subastador;
        this.articulo = articulo;
        this.tope = articulo.precio;
        
    }
    
    public void enviarResultadoDeSubasta(){
        
    }

    @Override
    public void notifyAllObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyOne(IObserver oberver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addObserver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public InfoSubasta toInfo(){
        return new InfoSubasta(articulo, tope, estado.toString(), subastador.getNombre(), id);
    }
    
}
