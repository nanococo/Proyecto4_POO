package App;

import GUI.InfoSubasta;
import Observer.IObserver;
import Observer.ISubject;
import java.util.ArrayList;

public class Subasta implements ISubject{
    
    private final Auctioneer auctioneer;
    private final Articulo articulo;
    private int tope;
    private Oferente mejorPuja;
    private final ArrayList<Oferente> oferentes = new ArrayList<>();
    private int id;
    private EstadoSubasta estado;

    public Subasta(Auctioneer auctioneer, Articulo articulo, int id) {
        this.id = id;
        this.auctioneer = auctioneer;
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

    public int getId() {
        return id;
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
        return new InfoSubasta(articulo, tope, estado.toString(), auctioneer.getName(), id);
    }
    
}
