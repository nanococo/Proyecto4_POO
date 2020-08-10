package App;

import GUI.InfoSubasta;
import java.util.ArrayList;

public class CasaDeSubastas {

    ArrayList<Subasta> subastas;//Enviadas a todos los clientes
    ArrayList<Subastador> subastadores;

    public ArrayList<InfoSubasta> subastasAEnviar(){
        ArrayList<InfoSubasta> infoSubastas = new ArrayList<>();
        for (Subasta subasta : subastas) {
            infoSubastas.add(subasta.toInfo());
        }
        return infoSubastas;
    }
    
    public Subasta getSubasta(InfoSubasta info){
        for (Subasta subasta : subastas) {
            if(info.getId() == subasta.id){
                return subasta;
            }
        }
        return null;
    }
}
