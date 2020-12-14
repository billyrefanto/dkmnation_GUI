/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Moch Billy Refanto
 */
public class Config {
    
    private static Connection MySQLConfig;
    
    
    public static Connection configDB()throws SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/dkmnation?serverTimezone=Asia/Jakarta";
            String user = "billyrefanto";
            String password = "Billyrefanto123";
            
            DriverManager.registerDriver(new Driver());
            MySQLConfig = DriverManager.getConnection(url,user,password);
            System.out.println("Terhubung Dengan Database!");
//            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");

        }catch(SQLException e){
            System.out.println("Koneksi ke database gagal " + e.getMessage());
//            JOptionPane.showMessageDialog(null, "Koneksi Gagal! " + e.getMessage());
        }
        return MySQLConfig;
    }
    
}
