/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarketsystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pc
 */
public class MyDatabase {

    public static Connection conn = null;
    private static final String url = "jdbc:mysql://localhost:3306/supermarket";
    private static final String username = "root";
    private static final String password = "";

    public static void getConnection() throws ClassNotFoundException, SQLException {
//        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Ok;");
//        } catch (ClassNotFoundException ex) {
//            System.err.println(ex);
//        } catch (SQLException ex) {
//            System.err.println(ex);
//        }
    }

    public static ObservableList<Employee> getAllEmployees() throws Exception {
        getConnection();
        ObservableList<Employee> employees
                = FXCollections.observableArrayList();
        PreparedStatement statement = conn.prepareStatement(
                "Select * from employees");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int id = resultSet.getInt("id");
            String address = resultSet.getString("address");
            String type = resultSet.getString("type");
            int salary = resultSet.getInt("salary");
            String gender = resultSet.getString("gender");

            Employee employee = new Employee(name, id, address, type, salary, gender);
            employees.add(employee);
        }
        return employees;
    }

    public static ObservableList<Customer> getAllCustomers() throws Exception {
        getConnection();
        ObservableList<Customer> customers
                = FXCollections.observableArrayList();
        PreparedStatement statement = conn.prepareStatement(
                "Select * from customers");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int id = resultSet.getInt("id");
            String address = resultSet.getString("address");
            int cardID = resultSet.getInt("cardID");
            int balance = resultSet.getInt("balance");

            Customer customer = new Customer(name, id, address, cardID, balance);
            customers.add(customer);
        }
        return customers;
    }

    public static ObservableList<Invoice> getAllInvoices() throws Exception {
        getConnection();
        ObservableList<Invoice> invoices
                = FXCollections.observableArrayList();
        PreparedStatement statement = conn.prepareStatement(
                "Select * from invoices");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String cusName = resultSet.getString("customerName");
            Date date = resultSet.getDate("date");
            int tot = resultSet.getInt("total");
            int net = resultSet.getInt("net");

            Invoice invoice = new Invoice(id, cusName, date, tot, net);
            invoices.add(invoice);
        }
        return invoices;
    }

    public static ObservableList<Product> getAllProducts() throws Exception {
        getConnection();
        ObservableList<Product> products
                = FXCollections.observableArrayList();
        PreparedStatement statement = conn.prepareStatement(
                "Select * from products");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int serNo = resultSet.getInt("serNo");
            String catName = resultSet.getString("catName");
            int quant = resultSet.getInt("quantity");
            int price = resultSet.getInt("price");

            Product product = new Product(name, serNo, catName, quant, price);
            products.add(product);
        }
        return products;
    }

}
