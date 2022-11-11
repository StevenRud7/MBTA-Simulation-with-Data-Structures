/**
* The Train Class which creates all necessary methods for the train including directions, add passengers, removing passengers, and updating the station the train is in.
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/
package main;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;		//Initiate all variables needed
	public Rider[] passengers;
	public int passengerIndex; //number of passengers in train at given moment
	public String currentStation;
	public int direction;
	
	/**
	 * Train constructor creating all variables 
	 * @param currentStation is the variable representing the current station the train is in
	 * @param direction is the variable for what direction the train is going (north or south)
	 * A simple constructor so running time of O(1)
	 */
	public Train(String currentStation, int direction) {
		this.currentStation=currentStation;
		this.direction= direction;
		passengerIndex = 0;
		passengers = new Rider[TOTAL_PASSENGERS];
		
	}
	
	/**
	 * Checks if train is going north
	 * @return true if train is going north and false if it is going south
	 * Only if/else statement so constant running time of O(1)
	 */
	public boolean goingNorth() {
		if (direction == 1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Swaps the direction value, if the direction is 1 then change to 0 and vice versa
	 * If else statement so running time O(1)
	 */
	public void swapDirection() {
		if (direction == 1) {
			direction = 0;
		}
		else {
			direction = 1;
		}
	}
	
	/**
	 * Creates a string and goes through the passengers variable and individually adds each one to the string to return a String with each passenger
	 * @return the new String with each passenger added to it
	 * A while loop with an if loop. The while loop going through passengers creates a linear running time so O(n)
	 */
	public String currentPassengers() {
		String passString = "";
		int count = 0; //try passengerIndex later maybes
		while (count<passengers.length) { 				//n
			if (passengers[count] != null) {			//1
				passString += passengers[count]+"/n";
			}
			count++;
		}
		return passString;
	}
	
	
	/**
	 * Makes sure that the train has space for the rider, the rider is at the correct station, and the rider and train are going in the same direction
	 * and if all this is true then we add the passenger to the train
	 * @param r is the rider we are attempted to add to the train
	 * @return true if passenger can be added and false if not.
	 * If statements are constant running time, but the for loop going through each passenger creates a linear running time of O(n)
	 */
	public boolean addPassenger(Rider r) {
		if (hasSpaceForPassengers() == false) {				//1
			return false;
		}
		if (!currentStation.equals(r.getStarting())) {		//1
			return false;
		}
		if(this.goingNorth() != r.goingNorth()) {			//1
			return false;
		}
		for(int x = 0; x < passengers.length; x++) {		//n
			if(passengers[x] == null) {
				passengers[x] = r;
				passengerIndex++;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return false if the train is full (amount of passengers equals total passengers capacity) and true if not.
	 * If/else statement so running time O(1)
	 */
	public boolean hasSpaceForPassengers() {
		if (passengerIndex==TOTAL_PASSENGERS) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * New Method created to update the passenger state of the train by manually removing every passenger from it
	 * A for loop with if statements creating an O(n) run time
	 */
	public void updateTrain() {
		for (int x = 0; x <passengers.length; x++) {
			if (passengers[x] != null) {
				if (getStation().equals(passengers[x].getDestination())) {
					passengerIndex--;
					
					passengers[x] = null;
					
				}
			}
		}
	}
	
	/**
	 * Goes through each passenger and if they are getting off then remove them from the train and add them to the String 
	 * @return the new String containing all passengers that got off the train
	 * constant if statements and linear for loop create a linear running time of O(n)
	 */
	public String disembarkPassengers() {
		String remPass = "";
		if(passengerIndex > 0) {														//1
			for (int x = 0; x<passengers.length; x++) {									//n
				if (passengers[x] != null) {												//1
					if (this.currentStation.equals(passengers[x].getDestination())) { 	//1		//adds to removedPass String
						remPass += passengers[x].getRiderID()+"\n";
					}
					if (passengers[x].getDestination().equals(getStation())) { 			//1	//removes them from the train	
						passengerIndex--;
						passengers[x] = null;
						
					}
				}
			}
		}
		return remPass;
	}
	
	/**
	 * @param s the station we are changing to
	 * Variable change with constant running time O(1)
	 */
	public void updateStation(String s) {
		currentStation = s;
	}
	
	/**
	 * @return the current Station variable
	 * A return so running time O(1)
	 */
	public String getStation() {
		return currentStation;
	}
	
	/**
	 * Create a string and add all necessary inputs so it (north or south, all passengers, and the current station)
	 * constant if else statements with a linear for loop making a linear running time of O(n)
	 */
	@Override
	public String toString() {
		String trainString = "Direction: ";
		if(goingNorth()==false) {
			trainString += "Southbound\n";
			
		}
		else {
			trainString += "Northbound\n";
		}
		trainString += "Passengers: \n";
		if (passengerIndex>0) {
			for (int x = 0; x < passengers.length; x++) {
				
				if (passengers[x]!=null) {
					trainString += passengers[x];
				}
			}
		}
		trainString += "Current station: " + currentStation + "\n";
		return trainString;
	}
}
