
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class connection {
    static Connection con = null;
     static Statement stm;
     static ResultSet re;
    public static Connection krleConnect(){
       String p = JOptionPane.showInputDialog("Enter MySQL Password");
       String q = JOptionPane.showInputDialog("Enter MySQL Database Name");
       try
       {
            Class.forName("com.mysql.jdbc.Driver"); 
                con = DriverManager.getConnection("jdbc:mysql://localhost/"+q,"mysql",""+p);
                stm = con.createStatement();
                System.out.println("connected!!!");
       } 
       catch (Exception e)
       {
           System.out.println("unknown database");
       }
       return con;
    }
    public static void main(String...args){
        
    }
}
