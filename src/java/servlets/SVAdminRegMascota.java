/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Principal.OperacionesBD;
import Principal.modelos.administrador;
import Principal.modelos.cliente;
import Principal.modelos.mascota;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
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
@WebServlet(name = "SVAdminRegMascota", urlPatterns = {"/SVAdminRegMascota"})
public class SVAdminRegMascota extends HttpServlet {

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
        String especie=request.getParameter("especie");
        String raza=request.getParameter("raza");
        String fechaCadena=request.getParameter("fechaNacimiento");
        String ID_Cliente=request.getParameter("cliente");
        int clienteID;
        Date fecha;
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fechaFinal;
        try {
              fecha= formatter.parse(fechaCadena);
              fechaFinal=new java.sql.Date(fecha.getTime());
        } catch (DateTimeParseException e) {
            response.sendRedirect("AdminMascota.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        } catch (ParseException ex) {
            response.sendRedirect("index.jsp");
                return;
        }
        
        try {
            clienteID=Integer.parseInt(ID_Cliente);
        } catch (Exception e) {
            response.sendRedirect("AdminMascota.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        
        if(!credencialesSonValidas(nombre, especie, raza)){
            response.sendRedirect("AdminMascota.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        mascota mascotaAInsertar=new mascota(0, nombre, especie, raza, fechaFinal, clienteID);
        operaciones.insertarMascota(mascotaAInsertar);
        response.sendRedirect("AdminMascota.jsp?mensaje=Mascota_registrada_exitosamente");
        return;
    }
    protected boolean credencialesSonValidas(String nombre,String especie, String raza){
        return nombreEsValido(nombre)&&especieEsValida(especie)&&razaEsValida(raza);
    }
    protected boolean nombreEsValido(String nombre){
        String patron="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$";
        return nombre.matches(patron);
    }
    protected boolean especieEsValida(String especie){
        String patron="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$";
        return especie.matches(patron);
    }
    protected boolean razaEsValida(String raza){
        String patron="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$";
        return raza.matches(patron);
    }
    

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
