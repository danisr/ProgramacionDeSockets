package LoteriaNavidad;

import java.io.*;
import java.net.*;

public class Servidor {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket newSocket = null;
		
		try {
			//Conexión
			System.out.println("Creando el socket servidor - LOTERO");
			serverSocket = new ServerSocket();
			System.out.println("Realiza el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			serverSocket.bind(addr);
			System.out.println("Aceptando conexiones...");
			newSocket = serverSocket.accept();
			System.out.println("Conexión recibida");

			//Variables
			int numeroPremiado = (int) Math.ceil(Math.random() * 99999);
			int numeroRecibido = 1;
			
			//Mientras no se pase un 0
			while (numeroRecibido != 0) {
				System.out.println("Recibiendo números...");
				byte[] mensaje = new byte[100];
				InputStream is = newSocket.getInputStream();
				is.read(mensaje);
				String msjRecibido = new String(mensaje);
				numeroRecibido = Integer.parseInt(msjRecibido.trim());
				System.out.println("Numero recibido = " + numeroRecibido + "\n");

				//Comprobaciones
				if (numeroRecibido == numeroPremiado) {
					OutputStream os = newSocket.getOutputStream();
					String resultado = "¡Premio Gordo!\n";
					os.write(resultado.getBytes());

				} else if (numeroRecibido % 1000 == numeroPremiado % 1000) {
					OutputStream os = newSocket.getOutputStream();
					String resultado = "Centenas\n";
					os.write(resultado.getBytes());
					
				} else if (numeroRecibido % 100 == numeroPremiado % 100) {
					OutputStream os = newSocket.getOutputStream();
					String resultado = "Dos últimas cifras\n";
					os.write(resultado.getBytes());
					
				} else if (numeroRecibido % 10 == numeroPremiado % 10) {
					OutputStream os = newSocket.getOutputStream();
					String resultado = "Reintegro\n";
					os.write(resultado.getBytes());
					
				} else if (numeroRecibido + 1 == numeroPremiado) {
					OutputStream os = newSocket.getOutputStream();
					String resultado = "Número anterior\n";
					os.write(resultado.getBytes());
					
				} else if (numeroRecibido - 1 == numeroPremiado) {
					OutputStream os = newSocket.getOutputStream();
					String resultado = "Número posterior\n";
					os.write(resultado.getBytes());
					
				} else {
					OutputStream os = newSocket.getOutputStream();
					String resultado = "Sin premio.\n";
					os.write(resultado.getBytes());
				}
			}
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
			System.out.println("Cerrando el socket servidor");
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}