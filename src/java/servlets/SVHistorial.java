/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Principal.OperacionesBD;
import Principal.modelos.historial;
import Principal.modelos.servicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "SVHistorial", urlPatterns = {"/SVHistorial"})
public class SVHistorial extends HttpServlet {

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
         int numMascota=-1;
        OperacionesBD operaciones=new OperacionesBD();
        try {
            numMascota=Integer.parseInt(request.getParameter("numeroMascota"));
        } catch (NumberFormatException e) {
            response.sendRedirect("cliente.jsp");
        }
        ArrayList<historial> historial=operaciones.buscarHistorialMascota(numMascota);
        sesion.setAttribute("historial", historial);
        response.sendRedirect("historial.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
