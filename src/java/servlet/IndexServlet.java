/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import funcionalidades.Consultas;
import funcionalidades.Permisos;
import static funcionalidades.Sesion.control;
import funcionalidades.Usuario;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matias
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //si no esta iniciada la session 
        if(control(request, response)){
            response.sendRedirect("login");//redirijo al formulario de login
        } else {
            Boolean modificado;
            Boolean insertado;
            Boolean eliminado;
            
            HttpSession session=request.getSession();//recupero session
            Usuario usr = (Usuario)session.getAttribute( "usuario" );//recupero usuario
            
            Permisos ps = (Permisos)session.getAttribute( "permisos" );//recupero permisos
            
            LinkedList clientes = Consultas.getClientes();//obtengo lista de clientes para vista
            
            request.setAttribute( "permisos", ps );//permisos
            request.setAttribute( "usuario", usr );//usuario
            request.setAttribute( "clientes", clientes );//lista de clientes
            
            modificado = (Boolean)session.getAttribute( "modificado" );
            if (modificado != null){
                modificado = true;
                request.setAttribute( "modificado", modificado );//permisos
            }
            insertado = (Boolean)session.getAttribute( "insertado" );
            if (insertado != null){
                insertado = true;
                request.setAttribute( "insertado", insertado );//permisos
            }
            eliminado = (Boolean)session.getAttribute( "eliminado" );
            if (eliminado != null){
                eliminado = true;
                request.setAttribute( "eliminado", eliminado );//permisos
            }
            request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
            
            
            if( insertado != null && insertado ) session.removeAttribute("insertado");
            if( modificado != null && modificado ) session.removeAttribute("modificado");
            if( eliminado != null && eliminado ) session.removeAttribute("eliminado");
        }
    
    
    
    
    
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
