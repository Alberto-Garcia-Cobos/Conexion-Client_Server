package server;

import java.io.Serializable;

//Clase tarea donde indicamos los parametros de cada una de las tareas ha realizar.
public class Tarea implements Serializable {
    private String estado;
    private String descripcion;

    //Constructores
    public Tarea() {
    }

    public Tarea(String descripcion, String estado) {
        super();
        this.descripcion = descripcion;
        this.estado = estado;

    }

    //Metodo toString
    @Override
    public String toString() {
        return "Tarea: " + descripcion + "," +
                " con estado " + estado;
    }

    //Metodos getter y setter
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
