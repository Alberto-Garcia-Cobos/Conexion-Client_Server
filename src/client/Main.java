package client;

import java.io.IOException;

/*
    PAC UF3 M09 PROGRAMACIÓN DE SERVICIOS Y PROCESOS
    ALBERTO GARCIA COBOS
    22/11/2021
 */
//Clase main del paquete cliente donde inicializamos todas las funciones de conexion y envio de datos.
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Creamos objeto de Cliente
        Client cli = new Client();
        System.out.println("Recibiendo mensaje del servidor");


        //Iniciamos la conexión
        cli.iniciarCliente();

        //Cerramos conexion
        cli.finalizarCliente();
    }
}
