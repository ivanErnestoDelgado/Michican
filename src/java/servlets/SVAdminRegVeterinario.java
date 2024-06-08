/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Principal.OperacionesBD;
import Principal.modelos.administrador;
import Principal.modelos.veterinario;
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
@WebServlet(name = "SVAdminRegVeterinario", urlPatterns = {"/SVAdminRegVeterinario"})
public class SVAdminRegVeterinario extends HttpServlet {

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

    }

   
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
        String nombre=request.getParameter("nombre");
        String apellido=request.getParameter("apellido");
        String especialidad=request.getParameter("especialidad");
         String telefono=request.getParameter("telefono");

         
        if(!credencialesSonValidas(especialidad, telefono, nombre, apellido)){
            response.sendRedirect("AdminVeterinario.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        veterinario veterinarioAInsertar=new veterinario(0, nombre, apellido, telefono, especialidad);
        operaciones.insertarVeterinario(veterinarioAInsertar);
        response.sendRedirect("AdminVeterinario.jsp?mensaje=Veterinario_insertado_exitosamente");
        return;
    }
    protected boolean credencialesSonValidas(String Especialidad,String telefono,String nombre,String apellido){
        return especialidadEsValida(Especialidad)&&telefonoEsValido(telefono)&&nombreEsValido(nombre)&&apellidoEsValido(apellido);
    }
   
    protected boolean telefonoEsValido(String telefono){
        String patronTelefono="^\\d{10}$";
        return telefono.matches(patronTelefono);
    }
    protected boolean nombreEsValido(String nombre){
        String patronNombre="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$";
        return nombre.matches(patronNombre);
    }
    protected boolean apellidoEsValido(String apellido){
        String patronApellido="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$";
        return apellido.matches(patronApellido);
    }
    protected boolean especialidadEsValida(String especialidad){
        String patron="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$";
        return especialidad.matches(patron);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
