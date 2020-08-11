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

    public Subasta(Subastador subastador,Articulo articulo,int id) {

        this.id = id;
        this.subastador = subastador;
        this.articulo = articulo;
        this.tope = articulo.precio;
        this.estado = EstadoSubasta.ACTIVA;
        
    }
    
    public void enviarResultadoDeSubasta(){
        //Notify
    }

    public void actualizarSubasta(){
        //Notify
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    public void setMejorPuja(Oferente mejorPuja) {
        this.mejorPuja = mejorPuja;
    }

    public void aceptarOferta(int idCliente,int cantidad){
        for (Oferente ofer:oferentes){
            if(ofer.id == idCliente)
                setMejorPuja(ofer);
        }
        setTope(cantidad);
        //ActualizarSubasta Notifica
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
