package remoteDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class RemoteDriver {

	/*
	 * Connect to one of the ports
	 * 4321 is the left truck.
	 * 4322 is the right truck.
	 */
	static int port = 4321;

	/*
	 * On the competition day, maybe it will be another address here.
	 */
	static String host = "localhost";

	public static void main(String[] args) throws IOException {

		Socket kkSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			kkSocket = new Socket(host, port);
			out = new PrintWriter(kkSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host:" + host);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + host);
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;

		/*
		 * Our input parameters to the fuzzy system.
		 */
		double x, y;
		double angle;

		/*
		 * FIS is the Fuzzy Interface imported from JFuzzyLogic library
		 * its used to compute our fuzzy logic.
		 */
		String fileName = "prototype-fuzzy.fcl";
		FIS fis = FIS.load(fileName, true);

		/*
		 * Suggestion from the documentation.
		 */
		if (fis == null) { // Error while loading?
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}

		/*
		 * Request Truck's position.
		 */
		out.println("r");

		while ((fromServer = in.readLine()) != null) {
			/*
			 * This loop will be constantly seeking to make the
			 * Parking of the Truck. Until this loop runs
			 * it will send back and forth comunication with the
			 * Server.java (running somewhere), and then
			 * this loop needs logic to guide the Truck to the parking
			 * spot.
			 *
			 * We can park the Truck using FCL. More simpler way.
			 */
			StringTokenizer st = new StringTokenizer(fromServer);
			x = Double.valueOf(st.nextToken()).doubleValue();
			y = Double.valueOf(st.nextToken()).doubleValue();
			angle = Double.valueOf(st.nextToken()).doubleValue();

			System.out.println("x: " + x + " y: " + y + " angle: " + angle);

			fis.setVariable("x", x);
			fis.setVariable("y", y);
			fis.setVariable("angle", angle);

			fis.evaluate();
			/*
			 * Send driving action ("usando volante").
			 * See definition for Fuzzy Classes for 'volante'
			 * at prototype-fuzzy.fcl.
			 */
			double offset = fis.getVariable("volante").defuzzify();
			System.out.println("volante value: " + offset);

			/*
			 * Send driving action ("usando volante").
			 */
			out.println(offset);

			/*
			 * Receive here the Truck Position.
			 * Just print "r".
			 */
			out.println("r");
		}

		out.close();
		in.close();
		stdIn.close();
		kkSocket.close();
	}
}
