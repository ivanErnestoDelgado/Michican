<%-- 
    Document   : index
    Created on : 28/05/2024, 09:53:46 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.administrador"%>
<%@page import="Principal.modelos.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    <script>
        function redireccionarRegistro(){
            window.location.href="registro.jsp";
        }
    </script>
    <%
        HttpSession sesion = request.getSession();
        String procedencia=request.getHeader("referer");
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
    <body class="index-body">
        <header>
            <nav class="barraNavegacion">
                <div class="titulo-pagina">
                    <a>Michican</a>
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
                <div class="contenedor-Texto">
                    <h1 id="titulo-Index">Bienvenido a Michican</h1>
                    <p>En Michican, nuestra pasión es el bienestar y la salud de sus mascotas. Nos enorgullecemos de ofrecer una atención veterinaria de calidad y personalizada, con un equipo de profesionales dedicados y experimentados.</p>
                    <button onclick="redireccionarRegistro();">¡Inicie con nosotros!</button>
                </div>
            </div>
    

    </body>

</html>
