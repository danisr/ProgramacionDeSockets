package Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Receptor {	
	public static void main(String args[]){
		DatagramSocket datagramSocket=null;
		
		try {
			//Conexión
			System.out.println("Creando el socket datagram receptor");
			System.out.println("Receptor");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			datagramSocket = new DatagramSocket(addr);
			
			boolean blFin=false;
			Scanner sc=new Scanner(System.in);
			System.out.print("Escriba su nick: ");
			String nick = sc.nextLine();
			System.out.println("Receptor escuchando mensaje");
			
			while(!blFin){
				//Recibe mensaje
				byte[] mensaje = new byte[25];
				DatagramPacket datagrama1 = new DatagramPacket(mensaje, 25);
				datagramSocket.receive(datagrama1);
				System.out.println("Mensaje recibido de emisor. " + new String(mensaje));
				
				//Envío de mensaje			
				System.out.print(nick + " escriba: ");				
				String mensaje2=sc.nextLine();
				if (mensaje2 == "adios") {
					blFin = true;
				}
				String mensajeReceptor = nick + ": " + mensaje2;
				
				InetAddress addr2 = InetAddress.getByName("localhost");
				DatagramPacket datagrama2 = new DatagramPacket(mensajeReceptor.getBytes(), mensajeReceptor.getBytes().length, addr2,5556);
				datagramSocket.send(datagrama2);
				System.out.println("Mensaje enviado de receptor"); 
			}
		} catch (IOException e) {
		e.printStackTrace();
		}finally {
			System.out.println("Cerrando el socket datagrama");
			datagramSocket.close();
			System.out.println("Terminado");
		}				
	}
}