<%-- 
    Document   : contacto
    Created on : 28/05/2024, 11:19:59 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.administrador"%>
<%@page import="Principal.modelos.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacto</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    <%
    HttpSession sesion=request.getSession();
        String procedencia=request.getHeader("referer");
            if(procedencia==null){
                response.sendRedirect("index.jsp");
                return;
            }
        cliente clienteEncontrado;
        administrador administradorEncontrado;
        try {
                 clienteEncontrado= (cliente) sesion.getAttribute("ClienteActual");
            } catch (Exception e) {
                clienteEncontrado=null;
            }
        try {
                  administradorEncontrado = (administrador) sesion.getAttribute("AdministradorActual");
            } catch (Exception e) {
                administradorEncontrado=null;
            }
    %>
    <body class="contacto-body">
        <header>
            <nav class="barraNavegacion">
                <div class="titulo-pagina">
                    <a>Contactanos</a>
                </div>
                <ul class="Menu-barra-navegacion">
                    <%if((administradorEncontrado!=null))
                    {
                    %>
                    <li class="elemento-barra"><a href="administrador.jsp"><%=administradorEncontrado.getNombre()%></a></li>
                    <%
                    }
                    %>
                    <%if((clienteEncontrado!=null))
                    {
                    %>
                    <li class="elemento-barra"><a href="cliente.jsp"><%=clienteEncontrado.getNombre()%></a></li>
                    <%
                    }
                    %>
                    <li class="elemento-barra"><a href="index.jsp">Pagina Principal</a></li>
                    <li class="elemento-barra"><a href="michican.jsp">Nosotros</a></li>
                    <li class="elemento-barra"><a href="SVServicios">Servicios</a></li>
                    <li class="elemento-barra"><a href="contacto.jsp">Contacto</a></li>
                    <%if((administradorEncontrado==null)&&(clienteEncontrado==null))
                    {
                    %>
                        <li class="elemento-barra"><a href="login.jsp">Iniciar sesion</a></li>
                        <li class="elemento-barra"><a href="registro.jsp">Registrarse</a></li>
                    <%
                    }else{
                    %>
                    <li class="elemento-barra"><a href="SVCierreSesion">Cerrar Sesion</a></li>
                    <%}
                    %>
                </ul>
            </nav>
        </header>
            <div class="padre">
                <div class="contenedor-Info-Michican">
                    <div class="contenedor-Contacto">
                    <h1 class="titulo-Info">Telefonos</h1>
                    <hr>
                    <ul>
                        <p><b><img src="imagenes/icono-telefono.png" width="25" height="25"/> +52 8852342321</b></p>
                        <p><b><img src="imagenes/icono-telefono.png" width="25" height="25"/> +52 8123124521</b></p>
                        <p><b><img src="imagenes/icono-telefono.png" width="25" height="25"/> +52 8823132145</b></p>
                    </ul>
                    </div><br>
                    <div class="contenedor-Contacto">
                    <h1 class="titulo-Info">Redes Sociales</h1>
                    <hr>
                    <ul>
                        <li><p><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">Facebook</a></p></li>
                        <li><p><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">X(Twitter)</a></p></li>
                        <li><p><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">Instagram</a></p></li>
                    </ul>
                    </div>
                </div>
            </div>
    </body>
</html>
