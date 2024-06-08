<%-- 
    Document   : administrador
    Created on : 28/05/2024, 10:02:12 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.veterinario"%>
<%@page import="Principal.modelos.servicio"%>
<%@page import="Principal.modelos.cliente"%>
<%@page import="Principal.modelos.mascota"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Principal.modelos.administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    <%
        HttpSession sesion=request.getSession();
        String procedencia=request.getHeader("referer");
            if(procedencia==null||sesion.isNew()){
                response.sendRedirect("index.jsp");
                return;
            }
        administrador administradorActual=(administrador) sesion.getAttribute("AdministradorActual");
        if(administradorActual==null){
                response.sendRedirect("index.jsp");
                return;
        }
        
        ArrayList<mascota> mascotas=(ArrayList) sesion.getAttribute("mascotasTotales");
        ArrayList<cliente> clientes=(ArrayList) sesion.getAttribute("clientesTotales");
        ArrayList<administrador> administradores=(ArrayList) sesion.getAttribute("administradoresTotales");
        ArrayList<servicio> servicios=(ArrayList) sesion.getAttribute("serviciosTotales");
        ArrayList<veterinario> veterinarios=(ArrayList) sesion.getAttribute("veterinariosTotales");
    
    %>
    <script>
        function mostrarTablas(tableId) {
            var tables = document.querySelectorAll("table");
            tables.forEach(function(table) {
                table.classList.add("hidden");
            });
            document.getElementById(tableId).classList.remove("hidden");
        }
        
         function mostrarForms(formId) {
            var forms = document.querySelectorAll(".Contenedor-Registro");
            forms.forEach(function(form) {
                form.classList.add("hidden");
            });
            document.getElementById(formId).classList.remove("hidden");
        }
        
        function irAAdmin(){
            window.location.href="AdminAlta.jsp";
        }
        function irACliente(){
            window.location.href="AdminCliente.jsp";
        }
        function irAMascota(){
            window.location.href="AdminMascota.jsp";
        }
        function irAServicio(){
            window.location.href="AdminServicio.jsp";
        }
        function irAVeterinario(){
            window.location.href="AdminVeterinario.jsp";
        }
        
    </script>

    <body class="administrador-body">
        <header>
            <nav class="barraNavegacion">
                <div class="titulo-pagina">
                    <a>Bienvenido <%=administradorActual.getNombre()%></a>
                </div>
                <ul class="Menu-barra-navegacion">
                    <li class="elemento-barra"><a href="index.jsp">Pagina Principal</a></li>
                    <li class="elemento-barra"><a onclick="mostrarTablas('clientes');mostrarForms('controlServicios')"">Cliente</a></li>
                    <li class="elemento-barra"><a onclick="mostrarTablas('administradores');mostrarForms('controlAdministradores')">Administrador</a></li>
                    <li class="elemento-barra"><a onclick="mostrarTablas('mascotas');mostrarForms('controlMascotas')">Mascota</a></li>
                    <li class="elemento-barra"><a onclick="mostrarTablas('servicios');mostrarForms('controlServicios')"">Servicio</a></li>
                    <li class="elemento-barra"><a onclick="mostrarTablas('veterinarios');mostrarForms('controlVeterinarios')">Veterinario</a></li>
                    <li class="elemento-barra"><a href="SVCierreSesion"><b>Cerrar Sesion</b></a></li>
                </ul>
            </nav>
        </header>
        <div class="padre">

            <div class="contenedor-tablas" >
                <table id="administradores" class="hidden">
                    <thead>
                        <tr>
                            <th>ID_Admin</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Correo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int indice = 0; indice < administradores.size(); indice++) {
                        %>
                        <tr>
                            <td>
                                <%=administradores.get(indice).getId_administrador()%>
                            </td>
                            <td>
                                <%=administradores.get(indice).getNombre()%>
                            </td>
                            <td>
                                <%=administradores.get(indice).getApellido()%>
                            </td>
                            <td>
                                <%=administradores.get(indice).getCorreo()%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>


                <table id="clientes" >
                    <thead>
                        <tr>
                            <th>ID_Cliente</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Telefono</th>
                            <th>Correo</th>
                            <th>Sexo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int indice = 0; indice < clientes.size(); indice++) {
                        %>
                        <tr>
                            <td>
                                <%=clientes.get(indice).getId_cliente()%>
                            </td>
                            <td>
                                <%=clientes.get(indice).getNombre()%>
                            </td>
                            <td>
                                <%=clientes.get(indice).getApellido()%>
                            </td>
                            <td>
                                <%=clientes.get(indice).getTelefono()%>
                            </td>
                            <td>
                                <%=clientes.get(indice).getCorreo()%>
                            </td>
                            <td>
                                <%=clientes.get(indice).getSexo()%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>

                <table id="veterinarios" class="hidden">
                    <thead>
                        <tr>
                            <th>ID_Veterinario</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Telefono</th>
                            <th>Especialidad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int indice = 0; indice < veterinarios.size(); indice++) {
                        %>
                        <tr>
                            <td>
                                <%=veterinarios.get(indice).getId_veterinario()%>
                            </td>
                            <td>
                                <%=veterinarios.get(indice).getNombre()%>
                            </td>
                            <td>
                                <%=veterinarios.get(indice).getApellido()%>
                            </td>
                            <td>
                                <%=veterinarios.get(indice).getTelefono()%>
                            </td>
                            <td>
                                <%=veterinarios.get(indice).getEspecialidad()%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>


                <table id="servicios" class="hidden">
                    <thead>
                        <tr>
                            <th>ID_Servicio</th>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Costo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int indice = 0; indice < servicios.size(); indice++) {
                        %>
                        <tr>
                            <td>
                                <%=servicios.get(indice).getId_servicio()%>
                            </td>
                            <td>
                                <%=servicios.get(indice).getNombre()%>
                            </td>
                            <td>
                                <%=servicios.get(indice).getDescripcion()%>
                            </td>
                            <td>
                                <%=servicios.get(indice).getCosto()%>
                            </td>
                        </tr>
                        <%
                            }
                        %>    
                    </tbody>
                </table>

                <table id="mascotas" class="hidden">
                    <thead>
                        <tr>
                            <th>ID_Mascota</th>
                            <th>Nombre_Mascota</th>
                            <th>Especie</th>
                            <th>Raza</th>
                            <th>Fecha_Nacimiento</th>
                            <th>ID_Cliente</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int indice = 0; indice < mascotas.size(); indice++) {
                        %>
                        <tr>
                            <td>
                                <%=mascotas.get(indice).getId_mascota()%>
                            </td>
                            <td>
                                <%=mascotas.get(indice).getNombre()%>
                            </td>
                            <td>
                                <%=mascotas.get(indice).getEspecie()%>
                            </td>
                            <td>
                                <%=mascotas.get(indice).getRaza()%>
                            </td>
                            <td>
                                <%=mascotas.get(indice).getFechaNacimiento()%>
                            </td>
                            <td>
                                <%=mascotas.get(indice).getId_cliente()%>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="controles-Tablas">
                <form id="controlClientes" class="Contenedor-Registro">
                    <h1>Controles Clientes</h1>
                    
                    <div class="contenedor-controles">
                        <input type="button" class="boton" value="Agregar Nuevo" onclick="irACliente();" />
                    </div>
                </form>

                <form id="controlMascotas" class="Contenedor-Registro hidden">
                    <h1>Controles Mascotas</h1>
                    
                    <div class="contenedor-controles">
                        <input type="button" class="boton" value="Agregar Nuevo" onclick="irAMascota();" />
                    </div>
                </form>
                <form id="controlAdministradores" class="Contenedor-Registro hidden">
                    <h1>Controles Administradores</h1>
                    
                    <div class="contenedor-controles">
                        <input type="button" class="boton" value="Agregar Nuevo" onclick="irAAdmin()" />
                    </div>
                </form>
                    <form id="controlVeterinarios" class="Contenedor-Registro hidden ">
                    <h1>Controles Veterinarios</h1>

                    <div class="contenedor-controles">
                        <<input type="button" class="boton" value="Agregar Nuevo" onclick="irAVeterinario()" />
                        
                    </div>
                </form>
                <form id="controlServicios" class="Contenedor-Registro hidden">
                    <h1>Controles Servicios</h1>
                    <div class="contenedor-controles">
                        <input type="button" class="boton" value="Agregar Nuevo" onclick="irAServicio()" />
                    </div>
                </form>
            </div>
        </div>
</body>
</html>
