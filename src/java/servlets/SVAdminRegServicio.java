/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Principal.OperacionesBD;
import Principal.modelos.administrador;
import Principal.modelos.servicio;
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
@WebServlet(name = "SVAdminRegServicio", urlPatterns = {"/SVAdminRegServicio"})
public class SVAdminRegServicio extends HttpServlet {

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
        String descripcion=request.getParameter("descripcion");
        String costo=request.getParameter("costo");

         
        if(!credencialesSonValidas(nombre, descripcion, costo)){
            response.sendRedirect("AdminServicio.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        servicio serivicioAInsertar=new servicio(0, nombre, descripcion, Float.parseFloat(costo));
        operaciones.insertarServicio(serivicioAInsertar);
        response.sendRedirect("AdminServicio.jsp?mensaje=Servicio_insertado_exitosamente");
        return;
    }
    protected boolean credencialesSonValidas(String nombre,String descripcion, String costo){
       return nombreEsValido(nombre)&&costoEsValido(costo)&&DescripcionEsValida(descripcion);
    }
   
    protected boolean costoEsValido(String costo){
        if (costo == null || costo.trim().isEmpty()) {
            return false;
        }
        double costValue;
        try {
            costValue = Double.parseDouble(costo);
        } catch (NumberFormatException e) {
            return false;
        }

        // Validar que el número sea positivo
        if (costValue <= 0) {
            return false;
        }
        
        return true;
    }
    protected boolean nombreEsValido(String nombre){
        String patronNombre="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,30}$";
        return nombre.matches(patronNombre);
    }
    protected boolean DescripcionEsValida(String descripcion){
        String patron="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,100}$";
        return descripcion.matches(patron);
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
