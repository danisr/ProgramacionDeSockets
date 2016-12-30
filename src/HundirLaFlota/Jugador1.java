package HundirLaFlota;

import java.io.*;
import java.net.*;
import java.util.Locale;
import java.util.Scanner;

public class Jugador1 {
	public static void main(String[] args) throws IOException {
		Socket clientSocket = null;

		try {
			//Conexión
			System.out.println("Creando socket cliente - Jugador1");
			clientSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			clientSocket.connect(addr);
			System.out.println("Conexión recibida");
			//Fin de conexión			

			//Declaracion de variables
			boolean found = false;
			int coordenadaX, coordenadaY, posicionX = -1, posicionY = -1, i = 1, continuar = 1, puntuacion = 3600;
			String nombre;
			char[] a = new char[36];
			for (int o = 0; o < a.length; o++) {
				a[o] = 'O';
			}
			//Fin declaracion de variables, array lleno de O.
			Scanner sc = new Scanner(System.in);
			sc.useLocale(Locale.ENGLISH);
			System.out.println("HUNDIR EL BARCO!");
			System.out.println("================");
			//Sitúa el barco en un sitio aleatorio en un tablero de 6x6
			coordenadaX = (int) (Math.random() * 6);
			coordenadaY = (int) (Math.random() * 6);
			System.out.printf("Introduce tu nombre: ");
			nombre = sc.next();
			System.out.printf("El barco ya esta situado\n\n");

			//Cuadrícula con el contenido de cada posicion del array.
			System.out.printf("  Y\n");
			System.out.printf(" (5) | " + a[30] + " | " + a[31] + " | " + a[32] + " | " + a[33] + " | " + a[34] + " | "
					+ a[35] + " |\n");
			System.out.printf(" (4) | " + a[24] + " | " + a[25] + " | " + a[26] + " | " + a[27] + " | " + a[28] + " | "
					+ a[29] + " |\n");
			System.out.printf(" (3) | " + a[18] + " | " + a[19] + " | " + a[20] + " | " + a[21] + " | " + a[22] + " | "
					+ a[23] + " |\n");
			System.out.printf(" (2) | " + a[12] + " | " + a[13] + " | " + a[14] + " | " + a[15] + " | " + a[16] + " | "
					+ a[17] + " |\n");
			System.out.printf(" (1) | " + a[6] + " | " + a[7] + " | " + a[8] + " | " + a[9] + " | " + a[10] + " | "
					+ a[11] + " |\n");
			System.out.printf(" (0) | " + a[0] + " | " + a[1] + " | " + a[2] + " | " + a[3] + " | " + a[4] + " | "
					+ a[5] + " |\n");
			System.out.printf("      (0) (1) (2) (3) (4) (5)  X\n\n");

			//found solo sera true al ganar o al rendirnos, entonces se acaba el bucle.
			while (!found) {				
				
				//Recibe mensaje de jugador 2
				InputStream is = clientSocket.getInputStream();
				byte[] mensaje = new byte[25];
				is.read(mensaje);
				System.out.println("JUGADOR 1: Mensaje recibido: " + new String(mensaje));
				//Fin de mensaje de jugador 2				

				//Si las posiciones entradas por el usuario coinciden con las coordenadas, gana la partida.
				if (posicionX == coordenadaX && posicionY == coordenadaY) {
					found = true; //Acierta
					
					//Para mandar información a jugador 2
					String sPerfecto = "Felicidades!\nHas hundido el barco en %d intentos" + nombre +  i;
					OutputStream os = clientSocket.getOutputStream();
					os.write(sPerfecto.getBytes());
					//Fin mandar información a jugador 2					
					
					//Comprueba los 8 puntos mas cercanos del barco.
				} else if (posicionX + 1 == coordenadaX
						&& (posicionY == coordenadaY || posicionY == coordenadaY - 1 || posicionY == coordenadaY + 1)
						|| posicionX - 1 == coordenadaX && (posicionY == coordenadaY || posicionY == coordenadaY - 1
								|| posicionY == coordenadaY + 1)
						|| posicionY + 1 == coordenadaY && posicionX == coordenadaX
						|| posicionY - 1 == coordenadaY && posicionX == coordenadaX) {
					
					//Para mandar información a jugador 2
					String sCasi = "Casi!";
					OutputStream os = clientSocket.getOutputStream();
					os.write(sCasi.getBytes());
					//Fin de mandar información a jugador 2
				} else {
										
					//Para mandar información a jugador 2
					String sMal = "Que mala punteria ...";
					OutputStream os = clientSocket.getOutputStream();
					os.write(sMal.getBytes());
					//Fin de mandar información a jugador 2
				}
				
				//Si decide no continuar, se muestra la posición del barco.
				if (continuar == 0) {
					System.out.printf("Que pena %s!\nNo has hundido el barco ...", nombre);
					System.out.printf("Estaba en la posicion (%d,%d)", coordenadaX, coordenadaY);
					System.out.printf("\nPuntuacion: 0\nHasta la proxima!!!\n");
					//Se activa true en found para no volver a entrar al bucle.
					found = true;
				} else {
					if (!found) {
						//Limpia la pantalla (de manera cutre :P)
						for (int k = 0; k < 1; k++) {
							System.out.println(' ');
						}
						//Muestra una X en las posiciones que el usuario dispare.
						switch (posicionY) {
						case 0:
							switch (posicionX) {
							case 0:
								a[0] = 'X';
								break;
							case 1:
								a[1] = 'X';
								break;
							case 2:
								a[2] = 'X';
								break;
							case 3:
								a[3] = 'X';
								break;
							case 4:
								a[4] = 'X';
								break;
							case 5:
								a[5] = 'X';
								break;
							}
							break;
						case 1:
							switch (posicionX) {
							case 0:
								a[6] = 'X';
								break;
							case 1:
								a[7] = 'X';
								break;
							case 2:
								a[8] = 'X';
								break;
							case 3:
								a[9] = 'X';
								break;
							case 4:
								a[10] = 'X';
								break;
							case 5:
								a[11] = 'X';
								break;
							}
							break;
						case 2:
							switch (posicionX) {
							case 0:
								a[12] = 'X';
								break;
							case 1:
								a[13] = 'X';
								break;
							case 2:
								a[14] = 'X';
								break;
							case 3:
								a[15] = 'X';
								break;
							case 4:
								a[16] = 'X';
								break;
							case 5:
								a[17] = 'X';
								break;
							}
							break;
						case 3:
							switch (posicionX) {
							case 0:
								a[18] = 'X';
								break;
							case 1:
								a[19] = 'X';
								break;
							case 2:
								a[20] = 'X';
								break;
							case 3:
								a[21] = 'X';
								break;
							case 4:
								a[22] = 'X';
								break;
							case 5:
								a[23] = 'X';
								break;
							}
							break;
						case 4:
							switch (posicionX) {
							case 0:
								a[24] = 'X';
								break;
							case 1:
								a[25] = 'X';
								break;
							case 2:
								a[26] = 'X';
								break;
							case 3:
								a[27] = 'X';
								break;
							case 4:
								a[28] = 'X';
								break;
							case 5:
								a[29] = 'X';
								break;
							}
							break;
						case 5:
							switch (posicionX) {
							case 0:
								a[30] = 'X';
								break;
							case 1:
								a[31] = 'X';
								break;
							case 2:
								a[32] = 'X';
								break;
							case 3:
								a[33] = 'X';
								break;
							case 4:
								a[34] = 'X';
								break;
							case 5:
								a[35] = 'X';
								break;
							}
							break;
						}
						//Vuelve a escribir el tablero con la X correspondiente.
						System.out.printf("  Y\n");
						System.out.printf(" (5) | " + a[30] + " | " + a[31] + " | " + a[32] + " | " + a[33] + " | "
								+ a[34] + " | " + a[35] + " |\n");
						System.out.printf(" (4) | " + a[24] + " | " + a[25] + " | " + a[26] + " | " + a[27] + " | "
								+ a[28] + " | " + a[29] + " |\n");
						System.out.printf(" (3) | " + a[18] + " | " + a[19] + " | " + a[20] + " | " + a[21] + " | "
								+ a[22] + " | " + a[23] + " |\n");
						System.out.printf(" (2) | " + a[12] + " | " + a[13] + " | " + a[14] + " | " + a[15] + " | "
								+ a[16] + " | " + a[17] + " |\n");
						System.out.printf(" (1) | " + a[6] + " | " + a[7] + " | " + a[8] + " | " + a[9] + " | " + a[10]
								+ " | " + a[11] + " |\n");
						System.out.printf(" (0) | " + a[0] + " | " + a[1] + " | " + a[2] + " | " + a[3] + " | " + a[4]
								+ " | " + a[5] + " |\n");
						System.out.printf("      (0) (1) (2) (3) (4) (5)  X\n\n");
					}
				}
				//Resta 100 puntos a puntuacion(3600) por cada fallo y suma 1 al contador para saber los intentos.
				puntuacion -= 100;
				i++;

				//Primero envía el mensaje y después recibe la información de si acierta o no
				//Envía mensaje a jugador 2
				System.out.println("JUGADOR 1: Enviando mensaje");
				Scanner scan = new Scanner(System.in);
				System.out.print("JUGADOR 1: Introduce coordenada X: ");
				String x = scan.nextLine();
				System.out.print("JUGADOR 1: Introduce coordenada Y: ");
				String y = scan.nextLine();
				String mensaje2 = x + "," + y;
				OutputStream os = clientSocket.getOutputStream();
				os.write(mensaje2.getBytes());
				System.out.println("JUGADOR 1: Mensaje enviado");
				//Fin de envía mensaje a jugador 2
				
				//Recibe mensaje de jugador 2 (Información)
				InputStream is2 = clientSocket.getInputStream();				
				byte[] mensaje3 = new byte[25];
				is2.read(mensaje3);
				System.out.println(new String(mensaje3));
				//Fin de reciba mensaje de jugador 2 (Información)	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Cerrando el socket CLIENTE");
			clientSocket.close();
		}
	}
}