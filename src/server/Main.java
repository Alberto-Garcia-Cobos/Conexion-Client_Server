package server;

import java.io.IOException;

//Clase main del paquete server, donde iniciamos todas las funciones para la conexion.
/*
    PAC UF3 M09 PROGRAMACIÓN DE SERVICIOS Y PROCESOS
    ALBERTO GARCIA COBOS
    22/11/2021
 */

public class Main {
    public static void main(String[] args) throws IOException {

                //Definimos objeto
                Server serv = new Server();
                System.out.println("Iniciando servidor");

                //Iniciamos el servidor
                serv.iniciarServer();

                //finalizamos el servidor
                serv.finalizarServer();
            }
        }


