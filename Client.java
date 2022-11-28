import java.io.*;
import java.net.*;

class Client {

	public static void main(String[] args) throws Exception {
		// Setting the Buffer
		int taille = 1024;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		byte buffer[] = new byte[taille];		

		// Enter server ip adress
		System.out.print("Server IP adress   : ");
		InetAddress adress = InetAddress.getByName(input.readLine());

		// Enter server port number
		System.out.print("Server Port number : ");
		int port = Integer.parseInt(input.readLine());

		// Connected
		System.out.println("\nConnetcted to server ("+adress.getHostAddress()+":"+port+")");
		
		// Initiating a socket
		DatagramSocket socket = new DatagramSocket();

		while (true) {
			//Clearing the Buffer
			buffer = new byte[taille];

			// Writing a message
			System.out.print("\nClient -> ");
			String line = input.readLine();
			int length = line.length();
			buffer = line.getBytes();

			// Sending the Packet
			DatagramPacket dataSent = new DatagramPacket(buffer, length, adress, port);
			socket.send(dataSent);

			//Clearing the Buffer
			buffer = new byte[taille];

			// Recieving a Packet
			DatagramPacket dataReceived = new DatagramPacket(buffer, buffer.length);
			socket.receive(dataReceived);

			// Message recieved
			System.out.println("Server -> "+ new String(dataReceived.getData()));


		}

	}
}