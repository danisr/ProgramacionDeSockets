package Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Emisor {
	public static void main(String args[]) {
		DatagramSocket datagramSocket = null;
		
		try {
			//Conexión
			System.out.println("Creando el socket datagram");
			System.out.println("Emisor");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
			datagramSocket = new DatagramSocket(addr);
			
			//Variables
			boolean blFin = false;
			Scanner sc = new Scanner(System.in);
			System.out.print("Escriba su nick: ");
			String nick = sc.nextLine();
			
			while (!blFin) {				
				System.out.print(nick + " escriba: ");
				String mensaje2 = sc.nextLine();				
				if (mensaje2 == "adios") {
					blFin = true;
				}
				String mensajeEmisor = nick + ": " + mensaje2;
				
				//Envío de mensaje
				InetAddress addr2 = InetAddress.getByName("localhost");
				DatagramPacket datagrama2 = new DatagramPacket(mensajeEmisor.getBytes(), mensajeEmisor.getBytes().length, addr2,5555);
				datagramSocket.send(datagrama2);
				System.out.println("Mensaje enviado de emisor");
				
				//Recepción de mensaje
				byte[] mensaje = new byte[25];
				DatagramPacket datagrama1 = new DatagramPacket(mensaje, 25);
				datagramSocket.receive(datagrama1);
				System.out.println("Mensaje recibido de receptor. " + new String(mensaje));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Cerrando el socket");
			datagramSocket.close();
			System.out.println("Terminado");
		}
	}
}