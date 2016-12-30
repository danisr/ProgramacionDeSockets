package Teoria_SocketStream;

import java.io.*;
import java.net.*;

public class ServidorSocketStream {
	public static void main(String[] args) {
		try {
			System.out.println("Creando el socket servidor");
			ServerSocket serverSocket = new ServerSocket();
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555); //El 5555 tiene que coincidir con el del cliente
			serverSocket.bind(addr); //S�lo se hace aqu� en servidor
			System.out.println("Aceptando conexiones");
			Socket newSocket = serverSocket.accept(); //Establece conexi�n con el otro lado. Se ejecuta cuando el cliente haga connect
			System.out.println("Conexi�n recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream(); //En este caso no env�a mensaje
			byte[] mensaje = new byte[25]; //Array de bytes para leer el read
			is.read(mensaje); //En el cliente es write y aqu� read
			System.out.println("Mensaje recibido: " + new String(mensaje));
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
			System.out.println("Cerrando el socket servidor");
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}