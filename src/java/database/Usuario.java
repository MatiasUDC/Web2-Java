/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



/**
 *
 * @author universidad
 */
public class Usuario {
    private String nombre;
    private String password;

    public Usuario() {}
    
    public Usuario(String usr, String pass){
        setNombre(usr);
        setPassword(pass);
    }

    private void setNombre(String usr) {
        nombre = usr;
    }

    private void setPassword(String pass) {
        password = pass;
    }
    
    public boolean validate(){
        boolean valido = false;
        
        try {
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            java.sql.Connection conn = ds.getConnection();
            
            String sql = "SELECT nombre, ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                rs.getInt("id");
                rs.getString("nombre");
  
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valido;
    }
    

}
