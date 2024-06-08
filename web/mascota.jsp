<%-- 
    Document   : mascota
    Created on : 1/06/2024, 06:30:07 PM
    Author     : SADValenz
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Principal.modelos.mascota"%>
<%@page import="Principal.modelos.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de Mascota</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    <%
    HttpSession sesion = request.getSession();
        String procedencia=request.getHeader("referer");
            if((procedencia==null)||(sesion.isNew())){
                response.sendRedirect("index.jsp");
                return;
            }
        cliente clienteEncontrado = (cliente) sesion.getAttribute("ClienteActual");
        ArrayList<mascota> mascotasEncontradas = (ArrayList) sesion.getAttribute("MascotasCliente");
        int numeroMascota=0;
        try {
                numeroMascota=Integer.parseInt(request.getParameter("Mascota"));
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
                return;
            }
        if(clienteEncontrado==null||mascotasEncontradas==null){
        response.sendRedirect("index.jsp");
                return;
        }
        mascota mascotaEscogida=mascotasEncontradas.get(numeroMascota);
        
        sesion.setAttribute("numeroMascota", numeroMascota);
    %>
    <script>
        function regresar(){
            window.location.href="cliente.jsp";
        }
        function redireccionarHistorial(){
            window.location.href="SVHistorial?numeroMascota=<%=mascotaEscogida.getId_mascota()%>";
        }
    </script>
    <body class="mascotas-body">
        <nav class="barraNavegacion">
            <div class="titulo-pagina">
                <a>Mascota</a>
            </div>
            <ul class="Menu-barra-navegacion">
                <li class="elemento-barra"><a href="michican.jsp">Nosotros</a></li>
                <li class="elemento-barra"><a href="servicios.jsp">Servicios</a></li>
                <li class="elemento-barra"><a href="contacto.jsp">Contacto</a></li>
            </ul>
        </nav>
        <div class="padre">
            <div class="info-Mascota">
                <h1>Informacion de la mascota</h1>
                <hr>
                <p>Nombre: <%=mascotaEscogida.getNombre()%></p>
                <p>Especie: <%=mascotaEscogida.getEspecie()%></p>
                <p>Raza: <%=mascotaEscogida.getRaza()%></p>
                <p>Fecha de nacimiento: <%=mascotaEscogida.getFechaNacimiento()%></p>
                <hr>
                <div class="contenedor-controles">
                    <button onclick="regresar();">Regresar</button>
                    <button onclick="redireccionarHistorial();">Historial</button>
                </div>
            </div>
    </body>
</html>
