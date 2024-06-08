<%-- 
    Document   : historial
    Created on : 2/06/2024, 04:14:03 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.mascota"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Principal.modelos.historial"%>
<%@page import="Principal.modelos.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilos/estilos.css">
        <title>Historial</title>
    </head>
    <script>
        function redireccionarMascota(indice) {
            window.location.href = "mascota.jsp?Mascota=" + indice;
        }
    </script>
    <%
        HttpSession sesion = request.getSession();
        String procedencia=request.getHeader("referer");
            if((procedencia==null)||(sesion.isNew())){
                response.sendRedirect("index.jsp");
                return;
            }
                    
        int numeroMascota=-1;
        try {
                numeroMascota=(int)sesion.getAttribute("numeroMascota");
            } catch (Exception e) {
                response.sendRedirect("index.jsp");
                return;
            }
        ArrayList<historial> historialEncontrado = (ArrayList) sesion.getAttribute("historial");
     
        if(historialEncontrado==null){
        response.sendRedirect("index.jsp");
                return;
        }
    %>
    <body class="historial-body">
        <nav class="barraNavegacion">
            <div class="titulo-pagina">
                <a>Historial</a>
            </div>
            <ul class="Menu-barra-navegacion">
                <li class="elemento-barra"><a href="michican.jsp">Nosotros</a></li>
                <li class="elemento-barra"><a href="servicios.jsp">Servicios</a></li>
                <li class="elemento-barra"><a href="contacto.jsp">Contacto</a></li>
            </ul>
        </nav>
        
        <div class="contenido">
            <table id="esqueleto">
                <tr>
                    <td>
                        <div class="padre">
                            <div class="historial-contenedor">
                                <%
                                    if(historialEncontrado.size()>0)
                                    for (int indice = 0; indice < historialEncontrado.size(); indice++) {
                                %>
                                <div class="historial-registrado-estilo">
                                    <p>Fecha: <%=historialEncontrado.get(indice).getFecha()%></p>
                                    <p>Descripcion: <%=historialEncontrado.get(indice).getDescripcion()%></p>
                                </div> <br>
                                <%
                                    }
                                    else out.println("<p>No hay tratamientos en el historial de esta mascota</p>");
                                %>
                            </div>    
                        </div>

                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="padre">
                              <div class="contenedor-controles">
                                  <button onclick="redireccionarMascota(<%=numeroMascota%>)">Regresar</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
    </body>
</html>
