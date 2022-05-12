package atencion;

import java.io.*;
import java.net.*;

/***
 * Cliente socket simple
 * @author Eduardo Morales
 * 
 * nos conectamos a un socket y enviamos un mensaje en duro
 * unidireccionalmente. No se espera ninguna respuesta
 *
 */
public class ClienteSocketSimple {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 6666); //indicamos IP, puerto al cual conectarnos
			
			//recuperamos el canal de SALID el socket
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			//enviamos un mensaje por el canal de SALIDA
			out.writeUTF("Bienvenido XXXX, en que podemos ayudarte (ingrese numero de opcion):");
			out.flush();
			out.close(); //CERRAMOS CONEXION
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}