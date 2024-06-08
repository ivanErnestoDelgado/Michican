<%-- 
    Document   : AdminAlta
    Created on : 4/06/2024, 09:39:41 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Dar de alta Cliente</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    <script>
        
       function validarCorreo(){
            var id=document.getElementById("correo").value;
            var expresion = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; 

            if (id === null || id.trim() === "") {
                alert("El campo de correo no debe estar vacio.");
                return false;
            } else if (id.match(expresion)) {
                return true;
            } else {
                alert("El formato de correo no esta correcto.");
                return false;
            }
        }
        function validarContrasena(){
            var contrasena=document.getElementById("contrasena").value;
            var expresion = /^[a-zA-Z0-9]+$/; 

            if (contrasena === null || contrasena.trim() === "") {
                alert("El campo de contraseña no puede estar vacío.");
                return false;
            } else if (contrasena.match(expresion)) {
                return true;
            } else {
                alert("El campo de contraseña debe contener numeros o letras sin caracteres especiales");
                return false;
            }
        }
        function validarNombre() {
        var nombre=document.getElementById("nombre").value;
        const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,25}$/;
        if (nombre === null || nombre.trim() === "") {
                alert("El campo de nombre no puede estar vacío.");
                return false;
            } else if (regex.test(nombre)) {
                return true;
            } else {
                alert("El campo de nombre debe contener letras y un nombre");
                return false;
            }
        }
        function validarApellido() {
            var apellido=document.getElementById("apellido").value;
            const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,25}$/;
            if (apellido === null || apellido.trim() === "") {
                alert("El campo de apellido no puede estar vacío.");
                return false;
            } else if (regex.test(apellido)) {
                return true;
            } else {
                alert("El campo de apellido debe contener letras y un apellido");
                return false;
            }    
        } 
        function regresar(){
             window.location.href="administrador.jsp";
        }
        function redireccionar(){
            var correo=document.getElementById("correo").value;
            var contrasena=document.getElementById("contrasena").value;
            var apellido=document.getElementById("apellido").value;
            var nombre=document.getElementById("nombre").value;
            
            if(validarContrasena()&&validarCorreo()&&validarNombre()&&validarApellido()){
                window.location.href="SVAdminAlta?correo="+correo+"&contrasena="+contrasena+"&&apellido="+apellido+"&nombre="+nombre;
            }
            
        }
        
    </script>
    <%
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
    %>
    <body class="registro-body">
        <nav class="barraNavegacion">
                <div class="titulo-pagina">
                    <a>Dar de alta Administrador</a>
                </div>
                <ul class="Menu-barra-navegacion">
                    <li class="elemento-barra"><a onclick="regresar()">Regresar</a></li>
                </ul>
            </nav>
        <section class="contenido">
        <table id="esqueleto-Inicio">
            <tr>
                <td>
                    <div class="padre">
                        <div class="Contenedor-Registro">
                            <h1 id="titulo-Index">Ingrese los datos</h1>
                            <form action="SVRegistro">
                                <p class="contenedor-Input">
                                    <b>Nombre:</b><input id="nombre" type="text" name="Nombre" class="entradaDatos" /> <br>
                                </p>
                                <p class="contenedor-Input">
                                    <b>Apellido: </b><input id="apellido" type="text" name="Apellido" class="entradaDatos" /> 
                                </p>
                                <p class="contenedor-Input">
                                    <b>Correo:</b><input id="correo" type="text" name="ID" class="entradaDatos"/> <br>
                                </p>
                                <p class="contenedor-Input">
                                    <b>Contraseña: </b><input id="contrasena" type="password" name="Contrasena" class="entradaDatos" /> 
                                </p>
                                
                                    <input class="boton" type="button" onclick="redireccionar();" value="Registrar"/>
                                    
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
            
            <tr>
                <td>
                    <div id="mensaje-error">
                        <h2>
                            <%
           String mensaje=request.getParameter("mensaje");
           if(!(mensaje==null))out.println("<br>"+mensaje.replace("_", " "));
           %>
                        </h2>
                    </div>
                </td>
            </tr>
        </table>
        
     </section>
    </body>
</html>
