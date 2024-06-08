<%-- 
    Document   : AdminMascota
    Created on : 4/06/2024, 09:40:06 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.administrador"%>
<%@page import="Principal.OperacionesBD"%>
<%@page import="Principal.modelos.cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <title>Registra tu mascota</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    
    <%
        HttpSession sesion=request.getSession();
        String procedencia=request.getHeader("referer");
            if((procedencia==null)||(sesion.isNew())){
                response.sendRedirect("index.jsp");
                return;
            }
            OperacionesBD operaciones=new OperacionesBD();
        ArrayList<cliente> clientes=operaciones.devolverClientes();
        if(clientes==null){
            response.sendRedirect("index.jsp");
            return;
        }
        administrador administradorActual=(administrador) sesion.getAttribute("AdministradorActual");
        if(administradorActual==null){
                response.sendRedirect("index.jsp");
                return;
        }
        
    %>
    <script>
        function regresar(){
             window.location.href="administrador.jsp";
        }
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
        function validarEspecie() {
            var especie=document.getElementById("especie").value;
            const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$/;
            if (especie === null || especie.trim() === "") {
                alert("El campo de especie no puede estar vacío.");
                return false;
            } else if (regex.test(especie)) {
                return true;
            } else {
                alert("El campo de especie debe contener letras");
                return false;
            }    
        }
        function validarRaza() {
            var raza=document.getElementById("raza").value;
            const regex = /^[A-Za-záéíóúÁÉÍÓÚüÜñÑ]{1,50}$/;
            if (raza === null || raza.trim() === "") {
                alert("El campo de raza no puede estar vacío.");
                return false;
            } else if (regex.test(raza)) {
                return true;
            } else {
                alert("El campo de raza debe contener letras");
                return false;
            }    
        }   
        function redireccionar(){
            var nombre=document.getElementById("nombre").value;
            var especie=document.getElementById("especie").value;
            var raza=document.getElementById("raza").value;
            var fechaNacimiento=document.getElementById("fechaNacimiento").value;
            var cliente=document.getElementById("cliente");
            
            var id_Cliente=cliente.options[cliente.selectedIndex].id;
            
            if(validarRaza()&&validarEspecie()&&validarNombre()&&validarFecha()){
                window.location.href="SVAdminRegMascota?nombre="+nombre+"&especie="+especie+"&raza="+raza+"&fechaNacimiento="+fechaNacimiento+"&cliente="+id_Cliente;
            }  
        }
        function validarFecha(){
            const fecha = document.getElementById('fechaNacimiento').value;
             if (!fecha) {
                alert("Ingrese una fecha");
                return false;
            }
            
             const date = new Date(fecha);
            if (isNaN(date.getTime())) {
                alert("Fecha no valida");
                return false;
            }
            const minFecha = new Date('2010-01-01');
            const maxFecha = new Date('2024-06-04');

            if (date < minFecha || date > maxFecha) {
                alert("Fecha fuera del limite");
                return false;
            }
            return true;
        }
        
        
        
    </script>
    <body class="registro-mascotas-body">
        <nav class="barraNavegacion">
                <div class="titulo-pagina">
                    <a>Dar de alta mascota</a>
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
                            <form action="SVRegistroMascota">
                                <p class="contenedor-Input">
                                    <b>Nombre:</b><input id="nombre" type="text" name="Nombre" class="entradaDatos" /> <br>
                                </p>
                                <p class="contenedor-Input">
                                    <b>Especie: </b><input id="especie" type="text" name="especie" class="entradaDatos" /> 
                                </p>
                                <p class="contenedor-Input">
                                    <b>Raza: </b><input id="raza" type="text" name="raza" class="entradaDatos" /> 
                                </p>
                                <p class="contenedor-Input">
                                    <b>Fecha de Nacimiento:</b><input id="fechaNacimiento" type="date" min="2010-01-01" max="2024-06-04" name="ID" class="entradaDatos"/> <br>
                                </p>
                                <p class="contenedor-Input">
                                    <b>Cliente: </b>
                                    <select name="cliente" id="cliente">
                                <%
                                    for (int indice = 0; indice < clientes.size(); indice++) {
                                %>
                                <option id="<%=clientes.get(indice).getId_cliente()%>"><%=clientes.get(indice).getNombre()%> <%=clientes.get(indice).getApellido()%></option>
                                <%
                                    }
                                %>
                                </select>
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
