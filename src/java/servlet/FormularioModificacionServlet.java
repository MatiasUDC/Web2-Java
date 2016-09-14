/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import funcionalidades.Cliente;
import funcionalidades.Consultas;
import funcionalidades.Permisos;
import static funcionalidades.Sesion.control;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
@WebServlet(name = "FormularioModificacionServlet", urlPatterns = {"/Modificacion"})
public class FormularioModificacionServlet extends HttpServlet {

    private int id_cliente;

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
        if (control(request, response)) {
            response.sendRedirect("login");
        } else {
            HttpSession session = request.getSession();
            Permisos permisos = (Permisos) session.getAttribute("permisos");
            if (permisos.tienePermiso("UPDATE")) {
                id_cliente = Integer.valueOf(request.getParameter("id"));
                HashMap cliente = Cliente.getCliente(id_cliente);
                request.setAttribute("cliente", cliente);
                LinkedList nacionalidades = Consultas.getNacionalidades();
                request.setAttribute("nacionalidades", nacionalidades);

                request.getRequestDispatcher("WEB-INF/jsp/modificacion_formulario.jsp").forward(request, response);
            } else {
                response.sendRedirect("denegado");
            }
        }
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
        if (control(request, response)) {
            response.sendRedirect("login");
        } else {
            HashMap<String, Object> errores = new HashMap();

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int nacionalidad = Integer.valueOf(request.getParameter("nacionalidad"));
            Integer activo = Integer.valueOf(request.getParameter("activo"));

            String fecha = request.getParameter("fecha_nacimiento");
            Date fecha_nacimiento = null;

            //valido nombre
            if (nombre == null || nombre.equals("")) {
                errores.put("nombre", "El nombre es requerido");
            }

            //valido apellido
            if (apellido == null || apellido.equals("")) {
                errores.put("apellido", "El apellido es requerido");
            }

            //valido nacionalidad
            if (nacionalidad == 0) {
                errores.put("nacionalidad", "La nacionalidad es requerido");
            }

            //valido fecha
            if (fecha == null || fecha.equals("")) {
                errores.put("fecha_nacimiento", "La fecha de nacimiento es requerido");
            } else {
                try {
                    fecha_nacimiento = Date.valueOf(fecha);
                } catch (IllegalArgumentException e) {
                    errores.put("fecha_nacimiento", "Ingrese una fecha de nacimiento valida");
                }
            }

            if (!errores.isEmpty()) {
                //seteo los errores
                request.setAttribute("errores", errores);

                HashMap cliente = Cliente.getCliente(id_cliente);
                request.setAttribute("cliente", cliente);
                LinkedList nacionalidades = Consultas.getNacionalidades();
                request.setAttribute("nacionalidades", nacionalidades);
                request.getRequestDispatcher("WEB-INF/jsp/modificacion_formulario.jsp").forward(request, response);

            } else {
                //inserto y redirecciono
                HttpSession session = request.getSession();
                Boolean modificado = true;
                session.setAttribute("modificado", modificado);
                Cliente.update(id_cliente, nombre, apellido, fecha_nacimiento, nacionalidad, activo);
                response.sendRedirect("index");
            }
        }
        }
    }
