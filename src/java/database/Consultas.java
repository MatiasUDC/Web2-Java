/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Cesar
 */
public class Consultas {
    
    public static LinkedList getNacionalidades(){
        Context initContext;
        LinkedList <HashMap<String, Object>> resultado = new LinkedList();
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            java.sql.Connection conn = ds.getConnection();
            
            String sql = "SELECT id, LOWER(descripcion) as descripcion FROM nacionalidades";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                HashMap row = new HashMap();
                row.put( "id", rs.getInt("id") );
                row.put( "descripcion", rs.getString("descripcion") );
                resultado.add(row);
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public static LinkedList getClientes(){
        Context initContext;
        LinkedList <HashMap<String, Object>> resultado = new LinkedList();
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            Connection conn = ds.getConnection();
            
            String sql = "SELECT " +
                            " clientes.id as id," +
                            " LOWER( apellido ) as apellido, " +
                            " LOWER( nombre ) as nombre, " +
                            " TIMESTAMPDIFF(YEAR, fecha_nacimiento, CURDATE()) as edad," +
                            " activo," +
                            " LOWER(nacionalidades.descripcion) as nacionalidad" +
                        " FROM clientes " +
                            " join nacionalidades " +
                            " on clientes.nacionalidad_id = nacionalidades.id";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                HashMap row = new HashMap();
                row.put( "id", rs.getInt("id") );
                row.put( "nombre", rs.getString("nombre") );
                row.put( "edad", rs.getInt("edad"));
                row.put( "activo", rs.getInt("activo"));
                row.put( "nacionalidad", rs.getString("nacionalidad"));
                resultado.add(row);
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
