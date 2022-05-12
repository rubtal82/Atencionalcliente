package atencion;

import java.io.*;
import java.net.*;

/***
 * Servidor socket multihilo
 * 
 * @author Eduardo Morales
 * 
 *         Cada vez que el servidor acepta una conexión y genere un socket este
 *         se pasará a un hilo nuevo, quedando el servidor libre para aceptar
 *         nuevas conexiones
 *
 */
class ServidorSocketMultiHilo {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {

			serverSocket = new ServerSocket(5000); // esperamos conexiones en puerto
			serverSocket.setReuseAddress(true);

			/*
			 * aqui la magia vamos a aceptar las conexiones en un bucle infinito
			 */
			while (true) {

				System.out.println("Esperamos clientes");
				
				Socket socketCliente = serverSocket.accept(); 
				//aceptamos conexión. 
				//se instancia el socketCliente, que es el objeto
				//que representa a la conexión única entre el cliente y el servidor
				
				//Mensaje 
				System.out.println("Nuevo cliente conectado" + socketCliente.getInetAddress().getHostAddress());

				// Vamos a pasar ese Socket (el canal de comunicación) a un hilo
				ClientHandler hiloSocket = new ClientHandler(socketCliente);

				//ejecutamos la lógica del hilo que tiene el socket dentro
				new Thread(hiloSocket).start();
				
				//luego de lanzar el hilo, el servidor vuelve al inicio del while
				//esperando nuevas conexiones
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//Clase que representa a un hilo independiente 
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;

		//Recibe el socket de conexion con el cliente por parte del servidor
		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		//Lo que se debe ejecutar el hilo
		public void run() {
			DataOutputStream out = null;
			DataInputStream in = null;
			try {

				// get the outputstream of client
				out = new DataOutputStream(clientSocket.getOutputStream());

				// get the inputstream of client
				in = new DataInputStream(clientSocket.getInputStream());

				String line = "";
				while (line != null) {//leemos del canal de entrada
					line = in.readUTF();
					System.out.printf("Recibimos correctamente este mensaje: %s\n", line);
					
					//escribimos por el canal de salida del socket
					out.writeUTF(line);
				}
			} catch (IOException e) {
				System.out.printf("Se interrumpio la comunicacion");
			} finally {
				//cierres de canales y socket
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {
						in.close();
						clientSocket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
