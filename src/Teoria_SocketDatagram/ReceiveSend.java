package Teoria_SocketDatagram;

import java.io.*;
import java.net.*;

public class ReceiveSend {
	public static void main(String[] args) {
		try {
			System.out.println("Creando el socket datagram");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			DatagramSocket datagramSocket = new DatagramSocket(addr);
			System.out.println("Recibiendo mensaje");
			byte[] mensaje = new byte[25]; //25 es el tamaño del paquete
			DatagramPacket datagrama1 = new DatagramPacket(mensaje, 25); //Para recibir
			datagramSocket.receive(datagrama1); //Se para el programa esperando el mensaje del emisor
			System.out.println("Mensaje recibido: " + new String(mensaje));
			
			//Paquete perdido
			System.out.println("Enviando mensaje");			
			InetAddress addr2 = InetAddress.getByName("localhost"); //Envío de nuevo mensaje
			DatagramPacket datagrama2 = new DatagramPacket(mensaje, mensaje.length, addr2, 5556);
			datagramSocket.send(datagrama2);
			System.out.println("Mensaje enviado");
			System.out.println("Cerrando el socket datagrama");			
			
			datagramSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}