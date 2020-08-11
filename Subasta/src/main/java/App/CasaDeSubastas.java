package App;

import GUI.InfoSubasta;
import java.util.ArrayList;

public class CasaDeSubastas {

    static ArrayList<Subasta> subastas;//Enviadas a todos los clientes
    ArrayList<Subastador> subastadores;
    static int currentSubastaId;

    public static void addSubasta(Subasta sub) {
        subastas.add(sub);
    }

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

    public static int getCurrentSubastaId(){
        return currentSubastaId++;
    }
}
