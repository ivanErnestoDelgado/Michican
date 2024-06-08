<%-- 
    Document   : adminVeterinario
    Created on : 4/06/2024, 09:40:33 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Dar de alta Veterinario</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    <script>
       
        function validarNombre() {
        var nombre=document.getElementById("nombre").value;
        const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$/;
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
        function validarEspecialidad() {
            var especialidad=document.getElementById("especialidad").value;
            const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,50}$/;
            if (especialidad === null || especialidad.trim() === "") {
                alert("El campo de especialidad no puede estar vacío.");
                return false;
            } else if (regex.test(especialidad)) {
                return true;
            } else {
                alert("El campo de especialidad debe contener letras");
                return false;
            }    
        }
        function validarApellido() {
            var apellido=document.getElementById("apellido").value;
            const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$/;
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
        
        function validarTelefono() {
            var telefono=document.getElementById("telefono").value;
            const regex = /^\d{10}$/;
            if (telefono === null || telefono.trim() === "") {
                alert("El campo de telefono no puede estar vacío.");
                return false;
            } else if (regex.test(telefono)) {
                return true;
            } else {
                alert("El campo de telefono debe contener solo 10 numeros");
                return false;
            }
        }   
        function regresar(){
             window.location.href="administrador.jsp";
        }
        function redireccionar(){
            var telefono=document.getElementById("telefono").value;
            var apellido=document.getElementById("apellido").value;
            var nombre=document.getElementById("nombre").value;
            var especialidad=document.getElementById("especialidad").value;
            
            
            if(validarNombre()&&validarTelefono()&&validarApellido()&&validarEspecialidad()){
                window.location.href="SVAdminRegVeterinario?telefono="+telefono+"&apellido="+apellido+"&nombre="+nombre+"&especialidad="+especialidad;
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
                    <a>Dar de alta a veterinario</a>
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
                                    <b>Telefono: </b><input id="telefono" type="number" name="telefono" class="entradaDatos" /> 
                                </p>
                                <p class="contenedor-Input">
                                    <b>Especialidad: </b><input id="especialidad" type="text" name="especialidad" class="entradaDatos" /> 
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
