package Messages;

import App.Product;

import java.io.Serializable;

/**
 *
 * @author Fernando Alvarez
 */
public class AuctionsInfo implements Serializable {
    
    private final Product product;
    private final int pujaMasAlta;
    private final String estado;
    private final String nickSubastador;
    private final String id;
    public AuctionsInfo(Product product, int pujaMasAlta, String estado, String nickSubastador, String id) {
        this.product = product;
        this.pujaMasAlta = pujaMasAlta;
        this.estado = estado;
        this.nickSubastador = nickSubastador;
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public int getPujaMasAlta() {
        return pujaMasAlta;
    }

    public String getEstado() {
        return estado;
    }

    public String getNickSubastador() {
        return nickSubastador;
    }

    public String toString(){
        return nickSubastador+"'s Auction\n"+
                        "Status: "+estado+
                        "Product: \n"+
                        "Name: "+ product.getName()+"\n"+
                        "Price: "+ product.getPrice()+"\n"+
                        "MaxAmount: "+ pujaMasAlta;
    }
    
    
    
}