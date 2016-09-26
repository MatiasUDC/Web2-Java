/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import funcionalidades.Cliente;
import funcionalidades.Permisos;
import static funcionalidades.Sesion.control;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProcesarBajaServlet", urlPatterns = {"/ProcesarBaja"})
public class ProcesaBajaServlet extends HttpServlet {
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
        if (control(request, response)) {
            response.sendRedirect("LoginServlet");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            doPost(request, response,id);
        }

    }
        protected void doPost(HttpServletRequest request, HttpServletResponse response,int id)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Permisos permisos = (Permisos) session.getAttribute("permisos");

        if (permisos.tienePermiso("DELETE")) {
            Cliente.delete(id);
            Boolean eliminado = true;
            session.setAttribute("eliminado", eliminado);
            response.sendRedirect("index");

        } else {
            response.sendRedirect("PermisoDenegado");
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
