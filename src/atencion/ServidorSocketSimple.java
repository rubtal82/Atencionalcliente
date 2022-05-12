package atencion;

import java.io.*;
import java.net.*;
/***
 * Servidor socket simple
 * @author Eduardo Morales
 * 
 * Abrimos un puerto como servidor y esperamos un mensaje
 * y luego cerramos conexión
 *
 */
public class ServidorSocketSimple {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(6666); //Abrimos puerto de escucha en puerto 6666
			System.out.println("Bienvenido a SocketCEL");
			
			// Esperamos clientes y cuando aceptemos uno
			// ese canal de comunicación será la instancia "socket"
			Socket socket = serverSocket.accept();
			
			//Una vez recibida la conexión, recuperamos su canal de ENTRADA
			DataInputStream in = new DataInputStream(socket.getInputStream());
			String str = (String) in.readUTF(); //leemos lo que nos llegó por el canal de ENTRADA
			
			System.out.println("Mensaje recibido= " + str); //IMPRIMIMOS EL MENSAJE
			serverSocket.close();//CERRAMOS CONEXIÓN
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
