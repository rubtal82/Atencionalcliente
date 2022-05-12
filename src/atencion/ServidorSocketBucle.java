package atencion;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * Servidor socket para comunicación constante UNIDIRECCIONAL
 * 
 * @author Eduardo Morales
 * 
 *         Servidor se queda esperando mensajes hasta recibir comando de
 *         finalización
 *
 */

public class ServidorSocketBucle {

	// constructor with port
	public static void main(String args[]) {
		Socket socket = null;
		ServerSocket serverSocket = null;
		DataInputStream in = null;

		
		try {
			serverSocket = new ServerSocket(5000); //INICIAMOS SERVIDOR
			System.out.println("Bienvenido a SocketCEL, favor ingrese número de cédula:");

			System.out.println("Leer numero de Cedula");

			// Esperamos clientes y cuando aceptemos uno
			// ese canal de comunicación será la instancia "socket"
			socket = serverSocket.accept();
			System.out.println("Cedula correcto");

			// recuperamos el canal de ENTRADA del socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			String line = "";

			//leeremos del canal de entrada hasta que se tenga el comando de finalizacion
			while (!line.equals("3")) {
				try {
					line = in.readUTF(); // esperamos leer mensaje del cliente
					System.out.println(line);

				} catch (IOException i) {
					System.out.println(i);
						
				}
				
				if (line.equals("1")) {
					
					System.out.println("Cual es su Consulta");
					line = in.readUTF(); // esperamos leer mensaje del cliente
					System.out.println(line);
					
				}
				
				if (line.equals("2")) {
					
					System.out.println("Cual es su Reclamo");
				}
			}
			System.out.println("Cerrando conexion");

			// close connection
			socket.close();
			in.close();
		} catch (IOException i) {
			System.out.println(i);
		}
	}

}
