/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Principal.OperacionesBD;
import Principal.modelos.administrador;
import Principal.modelos.cliente;
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
 * @author SADValenz
 */
@WebServlet(name = "SVAdminAlta", urlPatterns = {"/SVAdminAlta"})
public class SVAdminAlta extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
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
    HttpSession sesion=request.getSession();
        String procedencia=request.getHeader("referer");
            if((procedencia==null)||(sesion.isNew())){
                response.sendRedirect("index.jsp");
                return;
            }
        administrador administradorActual=(administrador) sesion.getAttribute("AdministradorActual");
        if(administradorActual==null){
                response.sendRedirect("index.jsp");
                return;
        }
        OperacionesBD operaciones=new OperacionesBD();
        String correo=request.getParameter("correo");
        String contrasena=request.getParameter("contrasena");
        String nombre=request.getParameter("nombre");
        String apellido=request.getParameter("apellido");

         
        if(!credencialesSonValidas(correo, contrasena, nombre, apellido)){
            response.sendRedirect("AdminAlta.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        administrador administradorAInsertar=new administrador(0,nombre, apellido, correo, contrasena);
        operaciones.insertarAdministrador(administradorAInsertar);
        response.sendRedirect("AdminAlta.jsp?mensaje=Administrador_insertado_exitosamente");
        return;
    }
    protected boolean credencialesSonValidas(String correo,String contrasena,String nombre,String apellido){
        return correoEsValido(correo)&&contrasenaEsValida(contrasena)&&nombreEsValido(nombre)&&apellidoEsValido(apellido);
    }
    protected boolean correoEsValido(String correo){
        String patronCorreo="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return correo.matches(patronCorreo);
    }
    protected boolean contrasenaEsValida(String contrasena){
        String patronContrasena="^[a-zA-Z0-9]+$";
        return contrasena.matches(patronContrasena);
    }
    protected boolean nombreEsValido(String nombre){
        String patronNombre="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$";
        return nombre.matches(patronNombre);
    }
    protected boolean apellidoEsValido(String apellido){
        String patronApellido="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$";
        return apellido.matches(patronApellido);
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
