import java.io.*;
import java.net.*;

class Server {

	public static void main(String[] args) throws Exception{

		int taille = 1024;
		byte buffer[] = new byte[taille];
		
		// Set Port number
		System.out.print("Enter Port number : ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int port = Integer.parseInt(input.readLine());
		
		// Initiating a socket on that port
		DatagramSocket socket = new DatagramSocket(port);

		System.out.println("Waiting for connections...");



		while(true){
			// Clearing the buffer
			buffer = new byte[taille];

			// Receiving a Packet
			DatagramPacket data = new DatagramPacket(buffer, buffer.length);
			socket.receive(data);
			System.out.println("\nFrom ("+data.getAddress().getHostAddress()+":"+data.getPort()+")\n Client -> "+ new String(data.getData()));

			// Clearing the buffer
			buffer = new byte[taille];

			// Writing a message
			System.out.print(" Server -> ");
			String line = input.readLine();
			buffer = line.getBytes();

			// Sending the Packet
			DatagramPacket send = new DatagramPacket(buffer, buffer.length, data.getAddress(), data.getPort());
			socket.send(send);
		}

	}
}