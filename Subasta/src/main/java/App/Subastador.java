package App;


import java.util.ArrayList;

public class Subastador {

    ArrayList<Subasta> misSubastas;//Enviados al subastador
    String nombre;

    public ArrayList<Subasta> getMisSubastas() {
        return misSubastas;
    }

    public String getNombre() {
        return nombre;
    }

    public void aceptarOferta(int idSubasta,int tope,int clientId){
        for (Subasta subasta:misSubastas){
            if(subasta.id == idSubasta)
                subasta.aceptarOferta(clientId,tope);
        }
    }

    public void subastar(Articulo articulo){
        Subasta sub = new Subasta(this,articulo,CasaDeSubastas.getCurrentSubastaId());
        misSubastas.add(sub);
        CasaDeSubastas.addSubasta(sub);
    }

    public void cerrarSubasta(){

    }

    public void cancelarSubasta(){

    }

}
