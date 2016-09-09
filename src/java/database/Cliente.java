/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Date;
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
public class Cliente {
 
    
    public static boolean insert(String nombre, String apellido, Date fecha_nacimiento, int nacionalidad, int activo ) {
        
        try {
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            
            java.sql.Connection conn = ds.getConnection();
            
     
            String sql = "INSERT INTO clientes (nombre, apellido, fecha_naciemiento, nacionalidad, activo) VALUES ( ?, ?, ? , ? , ? )";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setDate(3, fecha_nacimiento);
            pstmt.setInt(4, nacionalidad);
            pstmt.setInt(5, activo);
            
            ResultSet rs = pstmt.executeQuery();

        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean update(int id_cliente, String nombre, String apellido, Date fecha_nacimiento, int nacionalidad, int activo ) {
        
        try {
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            
            java.sql.Connection conn = ds.getConnection();
            
     
            String sql = "UPDATE clientes "
                    + "SET apellido = UPPER( ? ), "
                    + "nombre = UPPER( ?), "
                    + "fecha_nacimiento = ?, "
                    + "activo = ?, "
                    + "nacionalidad_id = ? "
                    + "WHERE clientes.id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(2, nombre);
            pstmt.setString(1, apellido);
            pstmt.setDate(3, fecha_nacimiento);
            pstmt.setInt(5, nacionalidad);
            pstmt.setInt(4, activo);
            pstmt.setInt(6, id_cliente);
            
            pstmt.executeQuery();

        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public static boolean delete(int id) {
        try {
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            java.sql.Connection conn = ds.getConnection();
            
            String sql =    "DELETE FROM " +
                                "clientes " +
                            "WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeQuery();
            

            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }    
    
    public static HashMap getCliente(int id) {
        HashMap<String, Object> cliente = new HashMap();
        try {
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            java.sql.Connection conn = ds.getConnection();
            
            String sql = "SELECT "
                    + "clientes.id as id_cliente,"
                    + "LOWER( apellido ) as apellido," 
                    + "LOWER( nombre ) as nombre, "
                    + "DATE_FORMAT(fecha_nacimiento,'%d-%m-%Y') as fecha_nacimiento,"
                    + "activo,"
                    + "LOWER(nacionalidades.descripcion) as nacionalidad,"
                    + "nacionalidades.id as id_nacionalidad"
                + "FROM clientes "
                    + "join nacionalidades "
                    + "on clientes.nacionalidad_id = nacionalidades.id"
                + "WHERE clientes.id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            
            cliente.put( "id_cliente", rs.getInt("id_cliente") );
            cliente.put( "nombre", rs.getString("nombre") );
            cliente.put( "apellido", rs.getString("apellido") );
            cliente.put( "fecha_nacimiento", rs.getDate("fecha_nacimiento") );
            cliente.put( "activo", rs.getInt("activo"));
            cliente.put( "nacionalidad", rs.getString("nacionalidad") );
            cliente.put( "id_nacionalidad", rs.getString("id_nacionalidad") );
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return cliente;
    }
}
