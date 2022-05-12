package atencion;


import java.io.*;
import java.net.*;
import java.util.*;

/***
 * Cliente socket para comunicación constante BIDIRECCIONAL
 * 
 * @author Eduardo Morales
 * 
 *         Nos conectamos al servidor y seguimos enviando mensajes hasta que se
 *         cumpla una condición de cierre de conexión. En este caso la
 *         comunicación con el servidor es turno a turno es decir, luego de
 *         enviar un mensaje, el cliente aguarda respuesta del servidor.
 * 
 */

class ClienteSocketBidireccional {

	
	public static void main(String[] args) {
		//establecemos conexion
		try (Socket socket = new Socket("localhost", 5000)) {

			//canal de salida hacia el servidor
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			//canal de entrada desde el servidor
			DataInputStream in = new DataInputStream(socket.getInputStream());

			// input del teclado
			Scanner entradaConsola = new Scanner(System.in);
			String line = null;

			while (!"fin".equalsIgnoreCase(line)) {

				//lectura de teclado
				line = entradaConsola.nextLine();

				// enviamos al servidor 
				out.writeUTF(line);
				out.flush();

				//imprimimos la respuesta
				System.out.println("Server retorno " + in.readUTF());
			}

			// cerramos conexion
			entradaConsola.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

