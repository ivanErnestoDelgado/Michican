<%-- 
    Document   : adminServicio
    Created on : 4/06/2024, 09:40:22 PM
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
        const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,30}$/;
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
        function regresar(){
             window.location.href="administrador.jsp";
        }
        
        function validarDescripcion() {
            var descripcion=document.getElementById("descripcion").value;
            const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ ]{1,100}$/;
            if (descripcion === null || descripcion.trim() === "") {
                alert("El campo de descripcion no puede estar vacío.");
                return false;
            } else if (regex.test(descripcion)) {
                return true;
            } else {
                alert("El campo de descripcion solo debe contener letras");
                return false;
            }    
        }
        
        function validarCosto() {
            let costo=document.getElementById("costo").value;
            
            if (!costo) {
                alert('El campo de costo no puede estar vacío.');
                return false;
            }

            let costValue = parseFloat(costo);
            if (isNaN(costValue)) {
                alert('Por favor, ingrese un número válido.');
                return false;
            }

            if (costValue <= 0) {
                alert('El costo debe ser un número positivo.');
                return false;
            }
            return true;
        }
         
        function redireccionar(){
            var descripcion=document.getElementById("descripcion").value;
            var nombre=document.getElementById("nombre").value;
            var costo=document.getElementById("costo").value;
            
            if(validarCosto()&&validarNombre()&&validarDescripcion()){
                window.location.href="SVAdminRegServicio?&nombre="+nombre+"&descripcion="+descripcion+"&costo="+costo;
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
                    <a>Dar de alta servicio</a>
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
                                    <b>Descripcion: </b><input id="descripcion" type="text" name="Apellido" class="entradaDatos" /> 
                                </p>
                                <p class="contenedor-Input">
                                    <b>Costo: </b><input id="costo" type="text" class="entradaDatos" /> 
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
