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
public class Customer {
    String name;
    int id;
    String address;
    int cardID;
    int balance;

    public Customer() {
    }

    public Customer(String name, int id, String address, int cardID, int balance) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.cardID = cardID;
        this.balance = balance;
    }

    public Customer(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
    
}
