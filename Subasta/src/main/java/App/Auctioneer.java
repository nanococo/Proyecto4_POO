package App;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Auctioneer {

    private final ArrayList<Subasta> auctions = new ArrayList<>(); //Enviados al subastador
    private final String name;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;


    public Auctioneer(ObjectInputStream inputStream, ObjectOutputStream outputStream, String name) {
        this.name = name;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    public ArrayList<Subasta> getAuctions() {
        return auctions;
    }

    public String getName() {
        return name;
    }

    public void aceptarOferta(int idSubasta,int tope,int clientId){
        for (Subasta subasta: auctions){
            if(subasta.getId() == idSubasta)
                subasta.aceptarOferta(clientId,tope);
        }
    }

    public void subastar(Articulo articulo){
        Subasta sub = new Subasta(this,articulo,CasaDeSubastas.getCurrentSubastaId());
        auctions.add(sub);
        CasaDeSubastas.addSubasta(sub);
    }

    public void cerrarSubasta(){

    }

    public void cancelarSubasta(){

    }

}
