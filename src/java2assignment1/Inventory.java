/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java2assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author harsha
 */

public class Inventory {

    public static Connection getConnection(){
    
        Connection conn=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.err.println("JDBC Error");
        }
        try
        {
            String url="jdbc:mysql://ipro.lambton.on.ca/inventory";
            String userName="products";
            String password="products";
            conn=DriverManager.getConnection(url, userName, password);
        }
        catch (SQLException e) {
            System.err.println("Failed to connect database");
        }
        return conn;
    }
    
    
    public static int getQuantityForId(int id) 
    {
        int quantity=-1;
        try
        {
        
            Connection conn=getConnection();
            String query="SELECT quantity FROM inventory WHERE id=?";            
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet res=stmt.executeQuery();
            quantity=res.getInt("quantity");
            
            conn.close();
        } catch (Exception e) 
        {
            System.err.println("Error  in query");
        }
        return quantity;
    }
    
}
