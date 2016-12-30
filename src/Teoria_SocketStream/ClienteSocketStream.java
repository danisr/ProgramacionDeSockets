package Teoria_SocketStream;

import java.io.*;
import java.net.*;

public class ClienteSocketStream {
	public static void main(String[] args) {
		try {
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("10.4.110.11", 80); //Establecer conexión, en localhost poner dirección del otro
			clientSocket.connect(addr); //Antes de esto el servidor tiene que estar arrancado
			InputStream is = clientSocket.getInputStream(); //En este caso no recibe mensaje
			OutputStream os = clientSocket.getOutputStream();
			System.out.println("Enviando mensaje");
			String mensaje = "mensaje desde el cliente"; //Mensaje a cambiar-------------------
			os.write(mensaje.getBytes()); //Mensaje String se convierte a Bytes
			System.out.println("Mensaje enviado");
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}