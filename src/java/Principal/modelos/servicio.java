/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.modelos;

/**
 *
 * @author SADValenz
 */
public class servicio {
    private int id_servicio;
    private String nombre;
    private String descripcion;
    private float costo;

    public servicio(int id_servicio, String nombre, String descripcion, float costo) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    
}
