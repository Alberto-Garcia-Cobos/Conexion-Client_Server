package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//Clase server donde indicamos todos los campos que se solicitan al cliente
public class Server {
    //Indicamos el puerto y el socket
    private final int PUERTO = 9876;
    private ServerSocket serverSocket;
    private Socket socket;
    DataInputStream entradaCliente;
    DataOutputStream salidaCliente;

    //Definimos el constructor
    public Server() throws IOException {
        serverSocket = new ServerSocket(PUERTO); //Definimos la conexión
        socket = new Socket(); //Iniciamos el cliente
    }

    //Función para iniciar la conexión
    public void iniciarServer() throws IOException {

        //Vamos a aceptar los datos que llegarán del cliente
        while (true) {

            System.out.println("Esperando 1r cliente");
            socket = serverSocket.accept();
            salidaCliente = new DataOutputStream(socket.getOutputStream());
            entradaCliente = new DataInputStream(socket.getInputStream());
            // El servidor se queda a la espera de recibir peticiones

            //Al recibir la petición, iniciamos la conexión

            //Enviamos mensaje al cliente
            salidaCliente.writeUTF("Bienvenido, ¿Cómo te llamas?");

            String nombreCliente;
            nombreCliente = entradaCliente.readUTF();
                    //Mostramos el mensaje por pantalla
                    System.out.println("Encantado de verte, " + nombreCliente);
            //TAREAS A REALIZAR
            salidaCliente.writeUTF("¿Cuantas tareas has de realizar?");
            int numeroTareas = entradaCliente.readInt();
            System.out.println("Se han recibido " + numeroTareas + " tareas.");
            List<Tarea> tareas = new ArrayList<Tarea>();
            //Bucle for para recorrer todas las tareas
            for (int i = 1; i < numeroTareas+1; i ++) {

                System.out.println("Introducción de la tarea:" + i);
                //Solicito descripcion
                salidaCliente.writeUTF("Introduce la descripción " + i + ":" );
                //recibo descripcion
                String descripcion = entradaCliente.readUTF();
                System.out.println("Descripción recibida " + descripcion);
                //solicito estado
                salidaCliente.writeUTF("Introduce el estado: ");
                //recibo estado
                String estado = entradaCliente.readUTF();
                System.out.println("Estado recibido " + estado);
                //Añadimos cada objeto tareas con sus argumentos
                tareas.add(new Tarea(descripcion,estado));
            }
            //Mandamos al cliente el listado de todas las tareas
            salidaCliente.writeUTF("Listado de tareas");
            ObjectOutputStream salidaCompleta = new ObjectOutputStream(salidaCliente);
            salidaCompleta.writeObject(tareas);
            System.out.println("Listado de tareas");

        }
    }

    public void finalizarServer() throws IOException {
        serverSocket.close();
        socket.close();
    }

}
