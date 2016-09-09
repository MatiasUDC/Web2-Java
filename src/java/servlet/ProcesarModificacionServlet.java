/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cesar
 */
@WebServlet(name = "ProcesarModificacionServlet", urlPatterns = {"/ProcesarModificacion"})
public class ProcesarModificacionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap<String, Object> errores = new HashMap();
        
        int id_cliente = Integer.parseInt( request.getParameter(null) );
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int nacionalidad = Integer.valueOf(request.getParameter("nacionalidad"));
        Date fecha_nacimiento = Date.valueOf(request.getParameter("fecha_nacimiento"));

        if (nombre == null || nombre.equals("")) {
            errores.put("nombre", "El nombre es requerido");
        }

        if (apellido == null || apellido.equals("")) {
            errores.put("apellido", "El apellido es requerido");
        }

        if (nacionalidad == 0) {
            errores.put("nacionalidad", "El apellido es requerido");
        }

        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            request.setAttribute("id", errores);
            request.getRequestDispatcher("/FormularioModificacion").forward(request, response);
        } else {
            //Cliente.update(id, nombre, apellido, fecha_nacimiento, nacionalidad, 1);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
