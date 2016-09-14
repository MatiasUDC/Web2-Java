/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import funcionalidades.Permisos;
import funcionalidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matias
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/sesion"})
public class SesionServlet extends HttpServlet {
     private static final long serialVersionUID = 1L;
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario  = request.getParameter( "usuario" );
        String pass      = request.getParameter( "contrasenia" );
        
        HashMap<String, Object> errores = new HashMap();
        
        //validacion
        if( usuario != null && usuario.equals("") )
            errores.put("usuario", "Campo usuario obligatorio");
        
        if ( pass != null && pass.equals("") )
            errores.put("contraseña", "Campo contraseña obligatorio");
        
        if(!errores.isEmpty()){
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        }

        Usuario usr = Usuario.getUsuario( usuario, pass );
        
        if ( usr != null ) {
            //obtengo permisos
            Permisos ps = Permisos.getPermisos(usr.getId());
            
            HttpSession session = request.getSession();
            session.setAttribute("esta_logueado", true);
            session.setAttribute("permisos", ps);
            session.setAttribute("usuario", usr);
            response.sendRedirect("index");
        } else {
            errores.put("error", "La combinacion de usuario y contraseña no es correcta");
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
