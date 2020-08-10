/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author Fernando Alvarez
 */
public class Articulo {
    
    int precio;
    String nombre;
    String imagePath;

    public Articulo(String name, String pathFile, int precio) {
        this.precio = precio;
        this.nombre = name;
        this.imagePath = pathFile;
    }
}
