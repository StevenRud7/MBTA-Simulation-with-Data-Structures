/**
* The MBTA Class which initiates everything and contains the main method to run other class
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/
package main;

import java.util.NoSuchElementException;
import java.io.File;
import java.util.Scanner;

import java.io.FileNotFoundException;
 


public class MBTA {

	public static final int SOUTHBOUND = 1; //Initiate all variables
	public static final int NORTHBOUND = 0;
	
	static final int TIMES = 6;
	static Railway r;
	
	
	/**
	 * The main method which calls the other methods creates the railway and runs the simulation
	 * @param args is the main argument 
	 * @throws FileNotFoundException throws file not found exception for the scenario when the file is not found
	 * Each method call has a linear run time so n*n*n*n making a running time of O(n^4)
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		r = new Railway();				//1
		initStations("redLine.txt");	//n
		initTrains("trains.txt");		//n
		initRiders("riders.txt");		//n
		
		runSimulation();				//n
	}
	
	public static void runSimulation() {
		for (int x = 1; x<TIMES+1; x++) {
			System.out.println(" ------ " + x +" ------ ");
			System.out.println(r.simulate());
			
		}
	}
	
	/**
	 * The initiate train method which scans through the train text file and adds them to the railway properly
	 * @param trainsFile the inputed train text file containing information on each train
	 * @throws FileNotFoundException throws file not found exception for the scenario when the file is not found
	 * Due to the trains text file lay out the scanning will have a running time of O(n^3)
	 */
	public static void initTrains(String trainsFile) throws FileNotFoundException {
		File trains = new File (trainsFile);
		Scanner console = new Scanner(trains);
		while (console.hasNext()) {
			String stationName = console.nextLine();
			int direction = Integer.parseInt(console.nextLine());
			Train train = new Train(stationName, direction);
			r.addTrain(train);
		}
	}
	
	/**
	 * The initiate rider method which scans through the riders text file and adds them to the railway properly by keeping the values organized
	 * @param ridersFile the inputed riders text file containing information on each rider including their ID, starting station (s), and end station (e)
	 * @throws FileNotFoundException throws file not found exception for the scenario when the file is not found
	 * Scanning through the riders text file makes a running time of O(n^2)
	 */
	public static void initRiders(String ridersFile) throws FileNotFoundException {
		File riders = new File (ridersFile);
		Scanner console = new Scanner(riders);
		while (console.hasNext()) {
			String riderID = console.nextLine();
			String s = console.nextLine();
			String e = console.nextLine();
			Rider r2 = new Rider(riderID, s, e);
			r.addRider(r2);
		}
	}
	/**
	 * The initiate station method which scans through the stations  text file and adds their names to the railway 
	 * @param stationsFile the inputed stations text file containing information on each station
	 * @throws FileNotFoundException throws file not found exception for the scenario when the file is not found
	 * Scanning through the stations text file makes a running time of O(n)
	 */
	public static void initStations(String stationsFile) throws FileNotFoundException {
		File stations = new File (stationsFile);
		Scanner console = new Scanner(stations);
		while (console.hasNext()) {
			String stationName = console.nextLine();
			Station s = new Station(stationName);
			r.addStation(s);
		}
	}
}
