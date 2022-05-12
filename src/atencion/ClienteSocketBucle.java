package atencion;


import java.io.*;
import java.net.*;


/***
 * Cliente socket para comunicación constante UNIDIRECCIONAL
 * 
 * @author Eduardo Morales
 * 
 *         Nos conectamos al servidor y seguimos enviando mensajes hasta que se
 *         cumpla una condición de cierre de conexión
 *
 */

public class ClienteSocketBucle {

	public static void main(String args[]) throws Exception{

		Socket socket = null; // Sockete de COMUNICACION
		DataInputStream input = null;// canal de entra del socket
		DataOutputStream out = null;// canal de salida del socket

		try {
			socket = new Socket("localhost", 5000); // IP, puerto de conexión
			System.out.println("Bienvenido XXXX, en que podemos ayudarte (ingrese numero de opcion):");
			System.out.println("1. Consultar Factura");
            System.out.println("2. Realizar Reclamos");
            System.out.println("3. Salir");
            

			// RECUPERAMOS el canal del ENTRADA del socket
			input = new DataInputStream(System.in);

			// RECUPERAMOS el canal del SALIDA del socket
			out = new DataOutputStream(socket.getOutputStream());

			// En "line" guardaremos lo que ingresemos por teclado
			String line = "";
			
			while (!line.equals("3")) {
				
				try {
					
					line = input.readLine(); // leemos de la consola
					out.writeUTF(line); // enviamos el string leido por el canal de SALIDA
					
				} catch (IOException i) {
					System.out.println(i);
				}
				
			}

		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		} finally {
			input.close(); // cerramos canal
			out.close();// cerramos canal
			socket.close();// cerramos socket
		}
	}
}
