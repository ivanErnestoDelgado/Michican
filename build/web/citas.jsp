<%-- 
    Document   : citas
    Created on : 28/05/2024, 10:22:25 PM
    Author     : SADValenz
--%>

<%@page import="Principal.modelos.servicio"%>
<%@page import="Principal.OperacionesBD"%>
<%@page import="Principal.modelos.mascota"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Principal.modelos.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Citas</title>
        <link rel="stylesheet" href="estilos/estilos.css">
    </head>
    
    <%
        HttpSession sesion=request.getSession();
        String procedencia=request.getHeader("referer");
            if((procedencia==null)||(sesion.isNew())){
                response.sendRedirect("index.jsp");
                return;
            }
            
        cliente clienteEncontrado = (cliente) sesion.getAttribute("ClienteActual");
        OperacionesBD operaciones=new OperacionesBD();
         ArrayList<servicio> servicio=operaciones.devolverServicios();
        ArrayList<mascota> mascotas=(ArrayList) sesion.getAttribute("MascotasCliente");
        if(clienteEncontrado==null||mascotas==null){
        response.sendRedirect("index.jsp");
                return;
        }
    %>
    <script>
        
        function validarMotivo() {
            var motivo=document.getElementById("motivo").value;
            const regex = /^[a-zA-Z0-9áéíóúÁÉÍÓÚ ]+$/;
            if (motivo === null || motivo.trim() === "") {
                alert("El campo de motivo no puede estar vacío.");
                return false;
            } else if (regex.test(motivo)) {
                return true;
            } else {
                alert("El campo de motivo debe contener solo letras");
                return false;
            }    
        }   
        function redireccionar(){
            var motivo=document.getElementById("motivo").value;
            var fecha=document.getElementById("fechaCita").value;
            var hora=document.getElementById("horaCita").value;
            var servicio=document.getElementById("servicio");
            var mascota=document.getElementById("mascota");
            
            var id_servicio=servicio.options[servicio.selectedIndex].id;
            var id_mascota=mascota.options[mascota.selectedIndex].id;
            if(validarMotivo()&&validarFecha()&&validarHora()){
                window.location.href="SVFechaCita?motivo="+motivo+"&fecha="+fecha+"&hora="+hora+"&servicio="+id_servicio+"&mascota="+id_mascota;
            }
        }
        function validarFecha(){
            const fecha = document.getElementById('fechaCita').value;
             if (!fecha) {
                alert("Ingrese una fecha");
                return false;
            }
            
             const date = new Date(fecha);
            if (isNaN(date.getTime())) {
                alert("Fecha no valida");
                return false;
            }
            const minFecha = new Date('2024-06-04');
            const maxFecha = new Date('2025-03-03');

            if (date < minFecha || date > maxFecha) {
                alert("Fecha fuera del limite");
                return false;
            }
            return true;
        }
        function validarHora(){
            const hora = document.getElementById('horaCita').value;
            if (!hora) {
                alert("Ingrese una hora");
                return false;
            }
            const [horas, minutos] = hora.split(':').map(Number);
            if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59) {
                alert('La hora no es válida.');
                return false;
            }
                return true;
        }
        
    </script>
    <body class="citas-body">
            <nav class="barraNavegacion">
                <div class="titulo-pagina">
                    <a>Registrar Cita</a>
                </div>
                <ul class="Menu-barra-navegacion">
                    <li class="elemento-barra"><a href="index.jsp">Pagina principal</a></li>
                </ul>
            </nav>
        <section class="contenido">
        <table id="esqueleto-Inicio">
            <tr>
                <td>
                    <div class="padre">
                        <div class="Contenedor-Registro">
                            <h1 id="titulo-Index">Rellene los campos</h1>
                            <form action="SVRegistroMascota">
                                 <p class="contenedor-Input">
                                    <b>Fecha de cita:</b><input id="fechaCita" type="date" min="2024-06-04" max="2025-03-03" name="ID" class="entradaDatos"/> <br>
                                </p>
                                <p class="contenedor-Input">
                                    <b>Hora:</b><input id="horaCita" type="time" name="Nombre" class="entradaDatos" /> <br>
                                </p>
                                <p class="contenedor-Input">
                                    <b>Motivo: </b><input id="motivo" type="text" name="motivo" class="entradaDatos" /> 
                                </p>
                                <p class="contenedor-Input">
                                    <b>Mascota: </b>
                                <select name="mascota" id="mascota">
                                <%
                                    for (int indice = 0; indice < mascotas.size(); indice++) {
                                %>
                                <option id="<%=mascotas.get(indice).getId_mascota()%>"><%=mascotas.get(indice).getNombre()%></option>
                                <%
                                    }
                                %>
                                </select>
                                </p>
                                <p class="contenedor-Input">
                                    <b>Servicio: </b>
                                    <select name="servicio" id="servicio">
                                <%
                                    for (int indice = 0; indice < servicio.size(); indice++) {
                                %>
                                <option id="<%=servicio.get(indice).getId_servicio()%>"><%=servicio.get(indice).getNombre()%></option>
                                <%
                                    }
                                %>
                                </select>
                                </p>
                                <input class="boton" type="button" onclick="redireccionar();" value="Agendar Cita"/>
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
    </body>
</html>
