package App;

import App.Subastador;
import Observer.IObserver;

public class Subasta {
    
    Subastador subastador;
    Articulo articulo;
    int tope;
    IObserver mejorPuja;

    public Subasta(Subastador subastador,Articulo articulo) {
        
        this.subastador = subastador;
        this.articulo = articulo;
        this.tope = articulo.precio;
        
    }
    
}
