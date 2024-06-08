/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Principal.OperacionesBD;
import Principal.modelos.administrador;
import Principal.modelos.cliente;
import Principal.modelos.mascota;
import Principal.modelos.servicio;
import Principal.modelos.veterinario;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SADValenz
 */
@WebServlet(name = "SVInicio", urlPatterns = {"/SVInicio"})
public class SVInicio extends HttpServlet {
    
        
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
            }       
        String Correo=request.getParameter("Correo");
        String Contrasena=request.getParameter("Contrasena");
        int modo=0;
        OperacionesBD operaciones=new OperacionesBD();
        
        limpiarAtributos(sesion);
        try {
            modo=Integer.parseInt(request.getParameter("modo"));
        } catch (NumberFormatException e) {
            response.sendRedirect("login.jsp?mensaje=Modo_invalido");
        }
        if(!credencialesSonValidas(Correo, Contrasena)){
            response.sendRedirect("login.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        switch(modo){
            case 1:{
                iniciarCliente(sesion,operaciones,Correo,Contrasena, response);
                return;
            }
            case 2:{
                iniciarAdministrador(sesion,operaciones,Correo,Contrasena, response);
                return;
            }
        }
    }
    private void iniciarAdministrador(HttpSession sesion, OperacionesBD operaciones, String Correo, String Contrasena, HttpServletResponse response) throws IOException {
        administrador administradorEncontrado=operaciones.buscarAdministrador(Contrasena, Correo);
            if(administradorEncontrado!=null){
                sesion.setAttribute("AdministradorActual", administradorEncontrado);
                subirDatosTablas(sesion,operaciones);
                response.sendRedirect("administrador.jsp");
                return;
            }
            redireccionarLogin(response);
    }
    
    private void subirDatosTablas(HttpSession sesion, OperacionesBD operaciones) {
        ArrayList<mascota> mascotas=operaciones.devolverMascotas();
        ArrayList<cliente> clientes=operaciones.devolverClientes();
        ArrayList<servicio> servicios=operaciones.devolverServicios();
        ArrayList<administrador> administradores=operaciones.devolverAdministradores();
        ArrayList<veterinario> veterinarios=operaciones.devolverVeterinarios();
        
        sesion.setAttribute("mascotasTotales", mascotas);
        sesion.setAttribute("clientesTotales", clientes);
        sesion.setAttribute("administradoresTotales", administradores);
        sesion.setAttribute("serviciosTotales", servicios);
        sesion.setAttribute("veterinariosTotales", veterinarios);
    }
    private void limpiarAtributos(HttpSession sesion){
        sesion.removeAttribute("ClienteActual");
        sesion.removeAttribute("AdministradorActual");
    }
    private void iniciarCliente(HttpSession sesion, OperacionesBD operaciones, String Correo, String Contrasena, HttpServletResponse response) throws IOException {
        cliente clienteEncontrado=operaciones.buscarCliente(Contrasena, Correo);
            if(clienteEncontrado!=null){
                sesion.setAttribute("ClienteActual", clienteEncontrado);
                ArrayList<mascota> mascotas=operaciones.buscarMascotas(clienteEncontrado.getId_cliente());
                sesion.setAttribute("MascotasCliente", mascotas);
                response.sendRedirect("cliente.jsp");
                return;
            }
            redireccionarLogin(response);
    }
    private void redireccionarLogin(HttpServletResponse response) throws IOException{
    
    response.sendRedirect("login.jsp?mensaje=Credenciales_Incorrectas");
    }
    protected boolean credencialesSonValidas(String correo,String contrasena){
        return correoEsValido(correo)&&contrasenaEsValida(contrasena);
    }
    protected boolean correoEsValido(String correo){
        String patronCorreo="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return correo.matches(patronCorreo);
    }
    
    protected boolean contrasenaEsValida(String contrasena){
        String patronContrasena="^[a-zA-Z0-9]+$";
        return contrasena.matches(patronContrasena);
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
