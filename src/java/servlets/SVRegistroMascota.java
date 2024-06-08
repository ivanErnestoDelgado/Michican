/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Principal.OperacionesBD;
import Principal.modelos.cliente;
import Principal.modelos.mascota;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SVRegistroMascota", urlPatterns = {"/SVRegistroMascota"})
public class SVRegistroMascota extends HttpServlet {

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
        OperacionesBD operaciones=new OperacionesBD();
        String nombre=request.getParameter("nombre");
        String especie=request.getParameter("especie");
        String raza=request.getParameter("raza");
        String fechaCadena=request.getParameter("fechaNacimiento");
        
        Date fecha;
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fechaFinal;
        try {
              fecha= formatter.parse(fechaCadena);
              fechaFinal=new java.sql.Date(fecha.getTime());
        } catch (DateTimeParseException e) {
            response.sendRedirect("registroMascota.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        } catch (ParseException ex) {
            response.sendRedirect("index.jsp");
                return;
        }
        cliente clienteEncontrado = (cliente) sesion.getAttribute("ClienteActual");
        if(clienteEncontrado==null){
        response.sendRedirect("index.jsp");
                return;
        }
        int clienteID=clienteEncontrado.getId_cliente();
        if(!credencialesSonValidas(nombre, especie, raza)){
            response.sendRedirect("registroMascota.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        mascota mascotaAInsertar=new mascota(0, nombre, especie, raza, fechaFinal, clienteID);
        operaciones.insertarMascota(mascotaAInsertar);
        response.sendRedirect("registroMascota.jsp?mensaje=Mascota_registrada_exitosamente");
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
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
