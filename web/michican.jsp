<%-- 
    Document   : michican
    Created on : 28/05/2024, 11:18:43 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.administrador"%>
<%@page import="Principal.modelos.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nosotros</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    <%
        HttpSession sesion=request.getSession();
        cliente clienteEncontrado;
        administrador administradorEncontrado;
        String procedencia=request.getHeader("referer");
            if((procedencia==null)||(sesion.isNew())){
                response.sendRedirect("index.jsp");
                return;
            }
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
    <body class="michican-body">
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
                    <h1 class="titulo-Info">¿Quiénes Somos?</h1>
                    <p>Somos una clínica veterinaria con años de experiencia en el cuidado de animales domésticos. Nuestro equipo está compuesto por veterinarios altamente capacitados y amantes de los animales, comprometidos en brindar el mejor servicio para sus queridos compañeros.</p>
                    <h1 class="titulo-Info">Nuestro Compromiso</h1>
                    <p>En Michican, entendemos que su mascota es parte de su familia. Por eso, nos dedicamos a proporcionar una atención cariñosa y profesional, utilizando tecnología de punta y prácticas veterinarias innovadoras.</p>
                    <h1 class="titulo-Info">Visítenos</h1>
                    <p>Estamos ubicados en calle Tlaxcala #234 en la colonia Los Virreyes en Los Mochis, Sin. y nuestro horario de atención son las 24 horas. Para cualquier consulta o para agendar una cita, no dude en contactarnos al 8852342321 u otro de nuestros numeros o mediante nuestro formulario de contacto en línea.</p>
                </div>
            </div>
    </body>
</html>
