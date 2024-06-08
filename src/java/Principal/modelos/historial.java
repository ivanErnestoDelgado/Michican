/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.modelos;

import java.sql.Date;

/**
 *
 * @author SADValenz
 */
public class historial {
    private int ID_Historial; 
    private Date Fecha; 
    private String Descripcion; 
    private int ID_Servicio; 
    private int ID_Mascota;

    public historial(int ID_Historial, Date Fecha, String Descripcion, int ID_Servicio, int ID_Mascota) {
        this.ID_Historial = ID_Historial;
        this.Fecha = Fecha;
        this.Descripcion = Descripcion;
        this.ID_Servicio = ID_Servicio;
        this.ID_Mascota = ID_Mascota;
    }

    
    public int getID_Historial() {
        return ID_Historial;
    }

    public void setID_Historial(int ID_Historial) {
        this.ID_Historial = ID_Historial;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getID_Servicio() {
        return ID_Servicio;
    }

    public void setID_Servicio(int ID_Servicio) {
        this.ID_Servicio = ID_Servicio;
    }

    public int getID_Mascota() {
        return ID_Mascota;
    }

    public void setID_Mascota(int ID_Mascota) {
        this.ID_Mascota = ID_Mascota;
    }
    
}
