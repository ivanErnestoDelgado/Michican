/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Principal.OperacionesBD;
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
@WebServlet(name = "SVRegistro", urlPatterns = {"/SVRegistro"})
public class SVRegistro extends HttpServlet {

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
        String correo=request.getParameter("correo");
        String contrasena=request.getParameter("contrasena");
        String telefono=request.getParameter("telefono");
        String nombre=request.getParameter("nombre");
        String apellido=request.getParameter("apellido");
        String sexo=request.getParameter("sexo");
         
        if(!credencialesSonValidas(correo, contrasena, telefono, nombre, apellido,sexo)){
            response.sendRedirect("registro.jsp?mensaje=Datos_ingresados_invalidos");
            return;
        }
        cliente clienteAInsertar=new cliente(0,nombre, apellido, telefono, correo, sexo, contrasena);
        boolean seInsertoCorrectamente=operaciones.insertarCliente(clienteAInsertar);
        if(seInsertoCorrectamente){
        response.sendRedirect("registro.jsp?mensaje=Cliente_insertado_exitosamente");
        return;
        }else{
        response.sendRedirect("registro.jsp?mensaje=El_correo_ya_esta_utilizado");
        return;
        }
            
    }
    protected boolean credencialesSonValidas(String correo,String contrasena,String telefono,String nombre,String apellido,String sexo){
        return correoEsValido(correo)&&contrasenaEsValida(contrasena)&&telefonoEsValido(telefono)&&nombreEsValido(nombre)&&apellidoEsValido(apellido)&&sexoEsValido(sexo);
    }
    protected boolean correoEsValido(String correo){
        String patronCorreo="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return correo.matches(patronCorreo);
    }
    protected boolean contrasenaEsValida(String contrasena){
        String patronContrasena="^[a-zA-Z0-9]+$";
        return contrasena.matches(patronContrasena);
    }
    protected boolean telefonoEsValido(String telefono){
        String patronTelefono="^\\d{10}$";
        return telefono.matches(patronTelefono);
    }
    protected boolean nombreEsValido(String nombre){
        String patronNombre="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,25}$";
        return nombre.matches(patronNombre);
    }
    protected boolean apellidoEsValido(String apellido){
        String patronApellido="^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,25}$";
        return apellido.matches(patronApellido);
    }
    
    protected boolean sexoEsValido(String sexo){
        String patronSexo="^(?i)(Hombre|Mujer)$";
        return sexo.matches(patronSexo);
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
