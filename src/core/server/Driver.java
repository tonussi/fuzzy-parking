import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;


public class Driver extends Thread {

	Quadro quadro;

	final int nAngulos = 7;
	final int nX = 5;
	final int nSaidas = 7;
	int sockPort;

	private Truck t;

	private boolean running;

	private PrintWriter out;

	private BufferedReader in;

	private Socket clientSocket;

	private ServerSocket server;

	public Driver(Quadro quadro, int x, int y,double stepSize, double angulo, int sockPort) {
		this.quadro = quadro;
		t = new Truck(x, y,stepSize, angulo);
		this.sockPort = sockPort;
	}

	public void setTruckPosition(int x, int y , double angulo) {
		t.setRotation(angulo);
		t.setPos(x, y);
	}

	public void stopSock(){
		this.stop();
		try {
			if(out!=null)
				out.close();
			if(in!=null)
				in.close();
			if(clientSocket!=null && !clientSocket.isClosed())
				clientSocket.close();
			if(server!=null && !server.isClosed())
				server.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void run() {
		running = true;
		quadro.clear();
		quadro.addTruckImage(t);
		int stepsUsed = 10000;

		server = null;
		try{
			server = new ServerSocket(sockPort); 
		} catch (IOException e) {
			System.out.println("Could not listen on port " + sockPort);
			System.exit(-1);
		}

		System.out.println("Listening on port " + sockPort);
		clientSocket = null;
		try {
			clientSocket = server.accept();
		} catch (IOException e) {
			System.err.println("Accept failed on " + sockPort);
			System.exit(1);
		}

		System.out.println("Client connected on port " + sockPort);

		try {

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(
					new InputStreamReader(
							clientSocket.getInputStream()));
			String inputLine;
			stepsUsed = 0;

			while (running && clientSocket.isConnected() && (inputLine = in.readLine()) != null) {
				if (inputLine.contains("r")){
					out.println(((double)t.getPos().x)/(double)quadro.getWidth() + "\t" + ((double)t.getPos().y)/(double)quadro.getHeight() + "\t" + t.getRotation());
				}else{
					double steer = Double.valueOf(inputLine);
					if (steer>1)
						t.stepManeuver(1);
					else if (steer<-1)
						t.stepManeuver(-1);
					else
						t.stepManeuver(steer);

					quadro.addTruckImage(t);
					stepsUsed++;
				}
				
				if (t.getPos().x>quadro.getWidth()+200 || t.getPos().y>quadro.getHeight() || t.getPos().x<-200 || t.getPos().y<-200 || stepsUsed>2000)
					break;
			}
			out.close();
			in.close();
			clientSocket.close();
			server.close();

		} catch (Exception e) {
			System.err.println("Something else went wrong on " + sockPort);
			System.exit(1);
		}

		double score = 10000-stepsUsed*t.stepSize - Math.abs(t.getPos().x-quadro.getWidth()/2) - Math.abs(90-t.getRotation()) - (quadro.getHeight()-t.getPos().y);
		System.out.println("Client disconnected on " + sockPort);
		System.out.println(sockPort + " Final x: " + t.getPos().x + " final y: "+ t.getPos().y + " final angle: " + t.getRotation() + " steps used: " + stepsUsed);
		System.out.println("Score of " + sockPort + " is: " + score);
		
		quadro.setScore(score);

	}


}
