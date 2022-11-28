import java.util.*;
import java.io.*;
import java.net.*;


public class Server  {
	static final int port = 8080;
	public static void main(String[] args) throws Exception{
		ServerSocket s = new ServerSocket(port);
		s.setReuseAddress(true);

		

		


		while(true){

			Socket soc = s.accept();

			System.out.println("New client connected : " + soc.getInetAddress().getHostAddress() +":"+ soc.getPort());

			ClientHandler clientSock = new ClientHandler(soc);

			new Thread(clientSock).start();
		}


		
		
	}
}

class ClientHandler implements Runnable {
	private final Socket clientSocket;

	public ClientHandler(Socket socket){
		this.clientSocket = socket;
	}

	public void run(){
			
		PrintWriter pred = null;
		BufferedReader plec = null;
		
		try {
					
				// get the outputstream of client
				pred = new PrintWriter(
					clientSocket.getOutputStream(), true);

				// get the inputstream of client
				plec = new BufferedReader(
					new InputStreamReader(
						clientSocket.getInputStream()));

				String str;
				while ((str = plec.readLine()) != null) {

					// writing the received message from
					// client
					System.out.printf(
						"Echo : %s\n",
						str);
					pred.println(str);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (pred != null) {
						pred.close();
					}
					if (plec != null) {
						plec.close();
						clientSocket.close();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}