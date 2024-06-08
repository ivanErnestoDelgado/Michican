/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Principal.modelos.administrador;
import Principal.modelos.cita;
import Principal.modelos.cliente;
import Principal.modelos.historial;
import Principal.modelos.mascota;
import Principal.modelos.servicio;
import Principal.modelos.veterinario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author SADValenz
 */
public class OperacionesBD {

    public cliente buscarCliente(String contrasena, String correo) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from cliente where correo=? and contrasena=?;");

            consulta.setString(1, correo);
            consulta.setString(2, contrasena);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int idClienteEncontrado = resultado.getInt(1);
                String nombreEncontrado = resultado.getString(2);
                String apellidoEncontrado = resultado.getString(3);
                String telefonoEncontrado = resultado.getString(4);
                String correoEncontrado = resultado.getString(5);
                String sexoEncontrado = resultado.getString(6);
                String contrasenaEncontrada = resultado.getString(7);

                cliente clienteEncontrado;
                if (!correoEncontrado.equals("")) {
                    clienteEncontrado = new cliente(idClienteEncontrado, nombreEncontrado,
                            apellidoEncontrado, telefonoEncontrado, correoEncontrado,
                            sexoEncontrado, contrasenaEncontrada);
                    return clienteEncontrado;
                }
            }
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    }
    
    public ArrayList<mascota> buscarMascotas(int idCliente) {
        ArrayList<mascota> mascotas=new ArrayList();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from mascota where id_cliente=?;");

            consulta.setInt(1, idCliente);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {

                int id_mascota = resultado.getInt(1);
                String nombre = resultado.getString(2);
                String especie = resultado.getString(3);
                String raza = resultado.getString(4);
                Date fechaNacimiento = resultado.getDate(5);
                int id_cliente = resultado.getInt(6);

                mascota mascotaEncontrada;
                if (!nombre.equals("")) {
                    mascotaEncontrada = new mascota(id_mascota, nombre,
                            especie, raza, fechaNacimiento,
                            idCliente);
                    mascotas.add(mascotaEncontrada);
                }
            }
            return mascotas;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    }
    public administrador buscarAdministrador(String contrasena, String correo) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from administrador where correo=? and contrasena=?;");

            consulta.setString(1, correo);
            consulta.setString(2, contrasena);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int id_administrador=resultado.getInt(1);
                String nombreEncontrado=resultado.getString(2);
                String apellidoEncontrado=resultado.getString(3);
                String correoEncontrado=resultado.getString(4);
                String contrasenaEncontrada=resultado.getString(5);
                
                administrador administradorEncontrado;
                if (!nombreEncontrado.equals("")) {
                    administradorEncontrado=new administrador(id_administrador, nombreEncontrado, 
                                        apellidoEncontrado, correoEncontrado, contrasenaEncontrada);
                    return administradorEncontrado;
                }
            }
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    }
    public ArrayList<servicio> devolverServicios(){
        ArrayList<servicio> servicios=new ArrayList();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from servicio;");
            
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int id_servicio=resultado.getInt(1);
                String nombre=resultado.getString(2);
                String descripcion=resultado.getString(3);
                float costo=resultado.getFloat(4);

                servicio servicioEncontrado;
                if (!nombre.equals("")) {
                    servicioEncontrado = new servicio(id_servicio,nombre,descripcion,costo);
                    servicios.add(servicioEncontrado);
                }
            }
            return servicios;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    }
    
    public ArrayList<historial> buscarHistorialMascota(int idMascota) {
        ArrayList<historial> historial=new ArrayList();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from historial where ID_Mascota=?;");

            consulta.setInt(1, idMascota);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int ID_Historial=resultado.getInt(1); 
                Date Fecha=resultado.getDate(2); 
                String Descripcion=resultado.getString(3); 
                int ID_Servicio=resultado.getInt(4); 
                int ID_Mascota=resultado.getInt(5);
                

                historial historialEncontrado;
                if (!Descripcion.equals("")) {
                    historialEncontrado = new historial(ID_Historial, Fecha,
                            Descripcion, ID_Servicio, ID_Mascota);
                    historial.add(historialEncontrado);
                }
            }
            return historial;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    }
    
    public boolean insertarCliente(cliente ClienteAInsertar) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "INSERT INTO cliente (Nombre, Apellido, Telefono, Correo, Sexo, contrasena) VALUES(?, ?, ?, ?, ?, ?);");
            consulta.setString(1, ClienteAInsertar.getNombre());
            consulta.setString(2, ClienteAInsertar.getApellido());
            consulta.setString(3, ClienteAInsertar.getTelefono());
            consulta.setString(4, ClienteAInsertar.getCorreo());
            consulta.setString(5, ClienteAInsertar.getSexo());
            consulta.setString(6, ClienteAInsertar.getContrasena());
            consulta.execute();
            return true;
             } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
            return false;
        }
    }
    
    public void insertarHistorial(historial historialAInsertar) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "INSERT INTO Historial (Fecha, Descripcion, ID_Servicio, ID_Mascota) VALUES(?, ?, ?, ?),");
            consulta.setDate(1, historialAInsertar.getFecha());
            consulta.setString(2, historialAInsertar.getDescripcion());
            consulta.setInt(3, historialAInsertar.getID_Servicio());
            consulta.setInt(4, historialAInsertar.getID_Mascota());
            consulta.execute();
             } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
    }
    public void insertarMascota(mascota mascotaAInsertar) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "INSERT INTO Mascota (Nombre_Mascota, Especie, Raza, Fecha_Nacimiento, ID_Cliente) VALUES(?, ?, ?, ?, ?);");
            consulta.setString(1, mascotaAInsertar.getNombre());
            consulta.setString(2, mascotaAInsertar.getEspecie());
            consulta.setString(3, mascotaAInsertar.getRaza());
            consulta.setDate(4, mascotaAInsertar.getFechaNacimiento());
            consulta.setInt(5, mascotaAInsertar.getId_cliente());

            consulta.execute();
             } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
    }
    
    public void insertarAdministrador(administrador administradorAInsertar) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "INSERT INTO administrador (Nombre, Apellido, Correo, contrasena) VALUES(?, ?, ?, ?);");
            consulta.setString(1, administradorAInsertar.getNombre());
            consulta.setString(2, administradorAInsertar.getApellido());
            consulta.setString(3, administradorAInsertar.getCorreo());
            consulta.setString(4, administradorAInsertar.getContrasena());
            consulta.execute();
             } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
    }
    public void insertarCita(cita citaAInsertar) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "INSERT INTO Cita (Fecha, Hora, Motivo, ID_Mascota, ID_Servicio) VALUES(?, ?, ?, ?, ?);");
            consulta.setDate(1, citaAInsertar.getFecha());
            consulta.setTime(2, citaAInsertar.getHora());
            consulta.setString(3, citaAInsertar.getMotivo());
            consulta.setInt(4, citaAInsertar.getIdMascota());
            consulta.setInt(5, citaAInsertar.getIdServicio());
            consulta.execute();
             } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
    }
    public void insertarVeterinario(veterinario veterinarioAInsertar) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "INSERT INTO Veterinario (Nombre, Apellido, Telefono, Especialidad) VALUES(?, ?, ?, ?);");
            consulta.setString(1,veterinarioAInsertar.getNombre() );
            consulta.setString(2, veterinarioAInsertar.getApellido());
            consulta.setString(3, veterinarioAInsertar.getTelefono());
            consulta.setString(4, veterinarioAInsertar.getEspecialidad());
            consulta.execute();
             } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
    }
    
    public void insertarServicio(servicio servicioAInsertar) {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "INSERT INTO Servicio (Nombre, Descripcion, Costo) VALUES(?, ?, ?);");
            consulta.setString(1,servicioAInsertar.getNombre());
            consulta.setString(2, servicioAInsertar.getDescripcion());
            consulta.setFloat(3, servicioAInsertar.getCosto());
            consulta.execute();
             } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
    }
    public ArrayList<cita> buscarCitaDiaEspecifico(Date fechaEscogida ) {
        ArrayList<cita> citas=new ArrayList();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from cita where fecha=?;");

            consulta.setDate(1, fechaEscogida);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int idCita=resultado.getInt(1); 
                Date fecha=resultado.getDate(2); 
                Time hora=resultado.getTime(3); 
                String motivo=resultado.getString(4); 
                int idMascota=resultado.getInt(5); 
                int idServicio=resultado.getInt(6); 
                
               
                cita citaEncontrada;
                if (!motivo.equals("")) {
                    citaEncontrada = new cita(idCita, fecha, hora, motivo, idMascota, idServicio);
                    citas.add(citaEncontrada);
                }
            }
            return citas;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    }
    
    public ArrayList<mascota> devolverMascotas(){
        ArrayList<mascota> mascotas=new ArrayList();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from mascota;");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {

                int id_mascota = resultado.getInt(1);
                String nombre = resultado.getString(2);
                String especie = resultado.getString(3);
                String raza = resultado.getString(4);
                Date fechaNacimiento = resultado.getDate(5);
                int id_cliente = resultado.getInt(6);

                mascota mascotaEncontrada;
                if (!nombre.equals("")) {
                    mascotaEncontrada = new mascota(id_mascota, nombre,
                            especie, raza, fechaNacimiento,
                            id_cliente);
                    mascotas.add(mascotaEncontrada);
                }
            }
            return mascotas;} catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    
    }
    public ArrayList<cliente> devolverClientes(){
        ArrayList<cliente> clientes=new ArrayList();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from cliente;");

            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int idClienteEncontrado = resultado.getInt(1);
                String nombreEncontrado = resultado.getString(2);
                String apellidoEncontrado = resultado.getString(3);
                String telefonoEncontrado = resultado.getString(4);
                String correoEncontrado = resultado.getString(5);
                String sexoEncontrado = resultado.getString(6);
                String contrasenaEncontrada = resultado.getString(7);

                cliente clienteEncontrado;
                if (!correoEncontrado.equals("")) {
                    clienteEncontrado = new cliente(idClienteEncontrado, nombreEncontrado,
                            apellidoEncontrado, telefonoEncontrado, correoEncontrado,
                            sexoEncontrado, contrasenaEncontrada);
                    clientes.add(clienteEncontrado);
                }
            }
            return clientes;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    
    }
    
    public ArrayList<administrador> devolverAdministradores(){
        ArrayList<administrador> administradores=new ArrayList();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from administrador;");

            
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int id_administrador=resultado.getInt(1);
                String nombreEncontrado=resultado.getString(2);
                String apellidoEncontrado=resultado.getString(3);
                String correoEncontrado=resultado.getString(4);
                String contrasenaEncontrada=resultado.getString(5);
                
                administrador administradorEncontrado;
                if (!nombreEncontrado.equals("")) {
                    administradorEncontrado=new administrador(id_administrador, nombreEncontrado, 
                                        apellidoEncontrado, correoEncontrado, contrasenaEncontrada);
                    administradores.add(administradorEncontrado);
                }
            }
            return administradores;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    }
    public ArrayList<veterinario> devolverVeterinarios(){
        ArrayList<veterinario> veterinarios=new ArrayList();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/Michican",
                    "postgres", "27octiedv@5000");

            PreparedStatement consulta = connection.prepareStatement(
                    "select * from veterinario;");

            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                int id_veterinario= resultado.getInt(1);
                String nombre=resultado.getString(2);
                String apellido=resultado.getString(3);
                String telefono=resultado.getString(4);
                String especialidad=resultado.getString(5);

                veterinario veterinarioEncontrado;
                if (!nombre.equals("")) {
                    veterinarioEncontrado=new veterinario(id_veterinario, nombre, apellido, telefono, especialidad);
                    veterinarios.add(veterinarioEncontrado);
                }
            }
            return veterinarios;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error en PostgreSQL: " + sqle);
        }
        return null;
    }

}
