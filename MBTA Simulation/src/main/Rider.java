/**
* The Rider Class which creates methods for all needed data about a rider including their ID, stations, and directions
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/

package main;

public class Rider {
	
	public String riderID;			//Initialize all variables 
	public String startingStation;
	public String destinationStation;
	public int direction;
	Train train;
	
	/**
	 * Constructor for rider which just sets each variable and starts the train south bound.
	 * @param riderID is the variable for the rider's ID value
	 * @param startingStation is the rider's starting station
	 * @param destinationStation is the rider's desired station
	 * A simple constructor so a constant running time of O(1)
	 */
	public Rider(String riderID, String startingStation, String destinationStation) {
		direction = 1;
		this.riderID=riderID;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
	}
	
	/**
	 * @return the starting station variable value
	 * A return so O(1)
	 */
	public String getStarting() {
		return startingStation;
	}
	
	/**
	 * @return the destination station variable value
	 * A return only so O(1)
	 */
	public String getDestination() {
		return destinationStation;
	}
	
	/**
	 * @return the riderID variable value
	 * Only return so O(1)
	 */
	public String getRiderID() {
		return riderID;
	}
	
	/**
	 * Check if the train is going north and if yes then return true otherwise false (uses direction variable)
	 * @return true if train north and false if south
	 * An if/else statement so a constant running time O(1)
	 */
	public boolean goingNorth() {	
		if (direction==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Uses the same swap direction method from train class which simply changes the direction value from 1 to 0 or 0 to 1
	 * The method is an if else statement so constant running time O(1)
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
	 * Simply return a string containing the riderID and their desired station
	 * So, constant running time O(1)
	 */
	@Override
	public String toString() {
		return riderID + ", " + destinationStation + "\n";
	}
	
	/**
	 * Checks if the new rider's id matches the current rider ID and if yes returns true otherwise false
	 * Only an if/else statement so running time of O(1)
	 */
	@Override
	public boolean equals(Object s) {
		Rider rider2 = (Rider) s;
		if(rider2.getRiderID()==this.riderID) {
			return true;
		}
		else {
			return false;
		}
	}
}
