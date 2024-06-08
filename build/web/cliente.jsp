<%-- 
    Document   : Cliente
    Created on : 31/05/2024, 10:13:55 PM
    Author     : SADValenz
--%>

<%@page import="Principal.OperacionesBD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Principal.modelos.mascota"%>
<%@page import="Principal.modelos.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido</title>
        <link rel="stylesheet" href="estilos/estilos.css">
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
                    
                
        OperacionesBD operaciones=new OperacionesBD();
        cliente clienteEncontrado = (cliente) sesion.getAttribute("ClienteActual");

        ArrayList<mascota> mascotasEncontradas = operaciones.buscarMascotas(clienteEncontrado.getId_cliente());
        if(clienteEncontrado==null||mascotasEncontradas==null){
        response.sendRedirect("index.jsp");
                return;
        }
        
        
        
    %>
    <body class="cliente-body">
        <header>
            <nav class="barraNavegacion">
                <div class="titulo-pagina">
                    <a>Bienvenido <%=clienteEncontrado.getNombre()%></a>
                </div>
                <ul class="Menu-barra-navegacion">
                    <li class="elemento-barra"><a href="citas.jsp">Agendar Cita</a></li>
                    <li class="elemento-barra"><a href="registroMascota.jsp">Registrar Mascota</a></li>
                    <li class="elemento-barra"><a href="michican.jsp">Nosotros</a></li>
                    <li class="elemento-barra"><a href="servicios.jsp">Servicios</a></li>
                    <li class="elemento-barra"><a href="contacto.jsp">Contacto</a></li>
                    <li class="elemento-barra"><a href="SVCierreSesion">Cerrar Sesion</a></li>
                </ul>
            </nav>
        </header>
        <div class="contenido">
            <table id="esqueleto">
                <tr>
                    <td>
                        <div class="encabezado">
                            <h1>Estas son sus mascotas registradas</h1>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="padre">
                            <div class="mascotas-contenedor">
                                <%
                                    for (int indice = 0; indice < mascotasEncontradas.size(); indice++) {
                                %>
                                <div class="mascotas-registradas-estilo">
                                    <h3><%=mascotasEncontradas.get(indice).getNombre()%></h3>
                                    <p>Especie: <%=mascotasEncontradas.get(indice).getEspecie()%></p>
                                    <button onclick="redireccionarMascota(<%=indice%>)">Ver mas</button>
                                </div> <br>
                                <%
                                    }
                                %>
                                
                                <%
                                    if(mascotasEncontradas.size()==0) {
                                %>
                                    <h3>No hay mascotas registradas</h3>
                                 
                                <%
                                    }
                                %>
                            </div>    
                        </div>

                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
