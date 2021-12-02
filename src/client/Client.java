package client;

import server.Tarea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

//Clase cliente donde indicamos todos los datos que necesitamos al contestar al servidor.
public class Client {
    private final String HOST = "localhost";
    private final int PUERTO = 9876;
    private Socket socket;
    DataOutputStream salidaServidor;
    DataInputStream entradaServidor;

    public Client() throws IOException {
        socket = new Socket(HOST, PUERTO);
    }

    public void iniciarCliente() throws IOException, ClassNotFoundException {

        //Iniciamos la entrada de datos
        entradaServidor = new DataInputStream(socket.getInputStream());
        salidaServidor = new DataOutputStream(socket.getOutputStream());
        System.out.println(entradaServidor.readUTF()); //Mostramos el mensaje por pantalla

        //Empezamos el envio de mensajes
        Scanner entrada = new Scanner(System.in);
        String cadena;
        cadena = entrada.nextLine();
        salidaServidor.writeUTF(cadena);
        System.out.println("Enviando mensaje al servidor:" + "{" + cadena + "}");
        System.out.println("Recibiendo mensaje del servidor");

        //Segundo mensaje indicando el numero de tareas
        System.out.println(entradaServidor.readUTF());
        int numeroTareas;
        numeroTareas = entrada.nextInt();
        salidaServidor.writeInt(numeroTareas);
        //Empezamos con la descripcion de las tareas
        System.out.println("Enviando mensaje al servidor: " + "{" + numeroTareas + "}");
        System.out.println("Leyendo mensaje del servidor");
        entrada.nextLine();
        //Comenzamos el bucle para recorer las tareas
        for (int i = 1; i < numeroTareas+1;i++) {

        //Introducimos la descripción
            System.out.println(entradaServidor.readUTF());
            String descripcion;
            descripcion = entrada.nextLine();
            salidaServidor.writeUTF(descripcion);
            System.out.println("Enviando mensaje al servidor: " + "{" + descripcion + "}");
            System.out.println("Leyendo mensaje del servidor");

        //Introducimos el estado
            System.out.println(entradaServidor.readUTF());
            String estado;
            estado = entrada.nextLine();
            salidaServidor.writeUTF(estado);
            System.out.println("Enviando mensaje al servidor " +"{" + estado + "}");

        }
        System.out.println("Leyendo mensaje del servidor");
        System.out.println(entradaServidor.readUTF());
        //Creamos una lista donde guardamos todas las tareas
        ObjectInputStream entradaCompleta = new ObjectInputStream(entradaServidor);
        List<Tarea> todasTareas = (List<Tarea>)  entradaCompleta.readObject();
        //Creamos un bucle for para recorrer todas las tareas
        for (Tarea tarea : todasTareas){
            System.out.println("Leyendo mensaje del servidor");
            System.out.println(tarea.toString());
        }

    }
    public void finalizarCliente() throws IOException {
        salidaServidor.close();
        entradaServidor.close();
        socket.close();
    }
}

