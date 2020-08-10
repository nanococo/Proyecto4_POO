/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import App.Articulo;

/**
 *
 * @author Fernando Alvarez
 */
public class InfoSubasta {
    
    Articulo articulo;
    int pujaMasAlta;
    String estado;
    String nickSubastador;
    int idSubasta;

    public InfoSubasta(Articulo articulo, int pujaMasAlta, String estado, String nickSubastador, int idSubasta) {
        this.articulo = articulo;
        this.pujaMasAlta = pujaMasAlta;
        this.estado = estado;
        this.nickSubastador = nickSubastador;
        this.idSubasta = idSubasta;
    }
    
    public String toString(){
        String msg = "Subasta#"+String.valueOf(idSubasta)+" de "+nickSubastador+"\n"+
                        "Estado: "+estado+
                        "Articulo: \n"+
                        "Nombre: "+articulo.getNombre()+"\n"+
                        "Precio: "+articulo.getPrecio()+"\n"+
                        "Maximo ofrecido: "+ String.valueOf(pujaMasAlta);
        return msg;
    }

    public int getId() {
        return idSubasta;
    }
    
    
    
}
