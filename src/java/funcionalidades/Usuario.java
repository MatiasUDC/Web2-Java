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
public class Usuario implements Serializable{

    private Integer id;
    private String nombre;

    private Usuario( Integer id, String nombre ) {
        setId(id);
        setNombre(nombre.toLowerCase());
    }

    public Integer getId(){
        return id;
    }
    
    private void setId(Integer id) {
        this.id = id;
    }

    private void setNombre(String usr) {
        nombre = usr;
    }
    public String getNombre() {
        return nombre; 
    }
    
    public static Usuario getUsuario(String user, String password){
        Usuario usr = null;
        Connection conn = null;
        try {
            InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/clientes_db");
            conn = ds.getConnection();
            StringBuilder query = new StringBuilder();
            
            query.append( "SELECT usuario.id, usuario.nombre " );
            query.append( "FROM usuario.usuario " );
            query.append( "WHERE" );
            query.append( "     true ");
            query.append( "     AND usuario.nombre LIKE ? " );
            query.append( "     AND usuario.contrasenia = SHA1( ? ) " );
            query.append( "     AND usuario.estado = 1" );
            PreparedStatement pstmt = conn.prepareStatement( query.toString() );
            pstmt.setString(1, user);
            pstmt.setString(2, password);
        
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usr = new Usuario( rs.getInt("id"), rs.getString("nombre") );
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if( conn != null && !conn.isClosed() ) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usr;
    }

    @Override
    public String toString() {
        return nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}