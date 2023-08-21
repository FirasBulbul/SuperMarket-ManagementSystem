/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketsystem;

/**
 *
 * @author pc
 */
public class Product {

    String name;
    int serNo;
    String catName;
    int quantity;
    int price;

    public Product() {
    }
    
    public Product(String name, int serNo, String catName, int quantity, int price) {
        this.name = name;
        this.serNo = serNo;
        this.catName = catName;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(int serNo, String name) {
        this.name = name;
        this.serNo = serNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerNo() {
        return serNo;
    }

    public void setSerNo(int serNo) {
        this.serNo = serNo;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
