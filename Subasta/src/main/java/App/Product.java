/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Messages.MessageKeys;
import Messaging.BaseMessage;

/**
 *
 * @author Fernando Alvarez
 */
public class Product extends BaseMessage {

    private final String ownerName;
    private int price;
    private String name;
    private String imagePath;


    public Product(String name, String pathFile, int price, String ownerName) {
        super(MessageKeys.PRODUCT);
        this.ownerName = ownerName;
        this.price = price;
        this.name = name;
        this.imagePath = pathFile;
    }

    public int getPrice() {
        return price;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
