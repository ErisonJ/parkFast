/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yan_t
 */
public class mysql {
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user  = "root";
    private static final String password = "King5288";
    private static final String url = "jdbc:mysql://localhost:3306/db_scp";

    public mysql() {
        conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, password);
            if(conn != null){
                //System.out.println("Conexion exitosa!");
            }
        } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error al conectarse "+e);
        }
    }
    
    public Connection getConectar(){
        return conn;
    }
    
    public void desconectar(){
        conn = null;
        if(conn == null){
            //System.out.println("Conexion Terminada!");
        }
    }
    
    public ResultSet ejecutar(String query){       
        mysql cc = new mysql();
        Connection cn = cc.getConectar();
        ResultSet rs = null;
         try {    
            Statement st = cn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.desconectar();
        return rs;
    }
}
