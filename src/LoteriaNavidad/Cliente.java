package LoteriaNavidad;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

public class Cliente {
	public static void main(String[] args) {
		Socket clientSocket;
		
		try {
			System.out.println("Creando socket cliente");
			clientSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);

			Scanner sc;
			int numero = 1;

			while (numero != 0) {
				sc = new Scanner(System.in);
				System.out.print("Escribe el número de lotería: ");
				numero = sc.nextInt();
				
				OutputStream os = clientSocket.getOutputStream();
				String mensaje = Integer.toString(numero);
				os.write(mensaje.getBytes());
				System.out.println("Enviando mensaje...");

				InputStream is = clientSocket.getInputStream();
				byte[] resultado = new byte[100];
				is.read(resultado);
				System.out.println(new String(resultado));
			}
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}