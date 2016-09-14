/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionalidades;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Matias
 */
public class Permisos implements Serializable{
    private LinkedList<String> permisos;

    public Permisos( LinkedList permisos ) {
        this.permisos = permisos;
    }
    
    public void setPermisos(LinkedList permisos) {
        this.permisos = permisos;
    } 

    public LinkedList getPermisos() {
        return permisos;
    }
    public static Permisos getPermisos(Integer idCliente) {
        Permisos permisos = null;
        Connection conn = null;
        try {
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            conn = ds.getConnection();
            
            StringBuilder query = new StringBuilder();
            query.append( "SELECT usuario.p.descripcion as permiso " );
            query.append( "FROM usuario.usuario u " );
            query.append( "JOIN usuario.rol_usuario ru " );
            query.append(  "ON u.id = ru.id_usuario " );
            query.append( "JOIN usuario.rol r " );
            query.append( "ON r.id = ru.id_rol " );
            query.append( "JOIN usuario.permiso_rol pr "); 
            query.append( "ON pr.id_rol = r.id ");
            query.append( "JOIN usuario.permiso p " );
            query.append( "ON p.id = pr.id_permiso " );
            query.append( "WHERE u.id = ?" );
            PreparedStatement pstmt = conn.prepareStatement( query.toString() );
            pstmt.setInt(1, idCliente);
        
            ResultSet rs = pstmt.executeQuery();
            LinkedList<String> permiso = new  LinkedList<>();
            
            while ( rs.next() ) {
                permiso.add( rs.getString( "permiso" ) );
            }
            
            permisos = new Permisos( permiso );
            /*
            query.setLength( 0 );
            
            query.append( "SELECT p.descripcion as permiso" );//permisos adicionales
            query.append( "FROM usuario.permiso p " );
            query.append( "JOIN usuario.permiso_usuario pu" );
            query.append( "ON p.id = pu.id_permiso " );
            query.append( "JOIN usuario.usuario u" );
            query.append( "ON u.id = pu.id_usuario " );
            query.append( "WHERE " );
            query.append( "true " );
            query.append( "AND u.id = ?" );
            
            
            pstmt = conn.prepareStatement( query.toString() );
            pstmt.setInt(1, idCliente);
            rs = pstmt.executeQuery();
            
            while ( rs.next() ) {
                if( !permiso.contains( rs.getString( "permiso" ) ) )
                    permiso.add( rs.getString( "permiso" ) );
            }
            */
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if( conn != null && !conn.isClosed() ) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permisos;
    }

    public boolean tienePermiso(String pms){
        for (String permiso : permisos) {
            if (permiso.equals(pms.toUpperCase())) {
                return true;
            }
        }
        return false;       
    }
}