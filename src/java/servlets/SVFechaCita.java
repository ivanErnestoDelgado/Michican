/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Principal.OperacionesBD;
import Principal.modelos.cita;
import Principal.modelos.cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
@WebServlet(name = "SVFechaCita", urlPatterns = {"/SVFechaCita"})
public class SVFechaCita extends HttpServlet {

    
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
        cliente clienteEncontrado = (cliente) sesion.getAttribute("ClienteActual");
        if(clienteEncontrado==null){
        response.sendRedirect("index.jsp");
                return;
        }
        OperacionesBD operaciones=new OperacionesBD();
        String motivo=request.getParameter("motivo");
        String hora=request.getParameter("hora");
        String mascota=request.getParameter("mascota");
        String fechaCadena=request.getParameter("fecha");
        String servicio=request.getParameter("servicio");
        int id_servicio,id_mascota;
        try {
            id_servicio=Integer.parseInt(servicio);
            id_mascota=Integer.parseInt(mascota);
        } catch (Exception e) {
            response.sendRedirect("citas.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        Date fecha;
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date fechaFinal;
        try {
              fecha= formatter.parse(fechaCadena);
              fechaFinal=new java.sql.Date(fecha.getTime());
        } catch (DateTimeParseException e) {
            response.sendRedirect("citas.jsp?mensaje=Fecha_invalida");
            return;
        } catch (ParseException ex) {
            response.sendRedirect("citas.jsp?mensaje=Fecha_invalida");
                return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Time horaFinal=new Time(0,0,0);
        try {
            // Parsea la cadena a un objeto Date
            Date parsedDate = dateFormat.parse(hora);
            horaFinal = new Time(parsedDate.getTime());
        } catch (ParseException e) {
            response.sendRedirect("citas.jsp?mensaje=hora_invalida");
            return;
        }
        cita citaAAgendar=new cita(0, fechaFinal, horaFinal, motivo, id_mascota, id_servicio);
        
        operaciones.insertarCita(citaAAgendar);

        response.sendRedirect("citas.jsp?mensaje=Cita_Agendada_Correctamente");
            return;
    
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
