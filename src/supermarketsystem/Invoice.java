/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketsystem;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class Invoice {
    int id;
    String custName;
    Date date;
    int totBalance;
    int netBalance;
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    String date;

    public Invoice() {
    }

    public Invoice(int id, String custName, Date date, int totBalance, int netBalance) {
        this.id = id;
        this.custName = custName;
        this.date = date;
        this.totBalance = totBalance;
        this.netBalance = netBalance;
//        this.date = this.getDate();
    }

    public Invoice(String custName) {
        this.custName = custName;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getTotBalance() {
        return totBalance;
    }

    public void setTotBalance(int totBalance) {
        this.totBalance = totBalance;
    }

    public int getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(int netBalance) {
        this.netBalance = netBalance;
    }

//    public final String getDate() {
//        return date = sdf.format(date);
//    } 

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
    
    
}
