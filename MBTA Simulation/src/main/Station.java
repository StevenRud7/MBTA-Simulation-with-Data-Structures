/**
* The Station Class which contains all needed methods for the station including adding riders/trains, adding riders to south bound and north bound trains, and as well swapping their directions
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/
package main;

public class Station {

	public Queue<Rider> northBoundRiders; //Initiate all variables
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	public String name;
	
	/**
	 * Constructor for station name and riders and trains. Create a max of 20 people
	 * @param name is the station variable name
	 * Just setting variables so running time O(1) 
	 */
	public Station(String name) {
		this.name=name;
		northBoundRiders = new Queue<Rider>(20);
		northBoundTrains = new Queue<Train>(20);
		
		southBoundRiders = new Queue<Rider>(20);
		southBoundTrains = new Queue<Train>(20);
	}
	
	/**
	 * Add riders to the north bound queue if going north and add riders to the south bound queue if going south
	 * @param r is the variable of the rider we are adding to the queue
	 * @return true if a rider was added otherwise return false
	 * Only if/else statements so constant running time O(1)
	 */
	public boolean addRider(Rider r) { 
			if (r.goingNorth()==true) {
				northBoundRiders.enqueue(r);
				return true;
			}
			if (r.goingNorth()==false) {
				southBoundRiders.enqueue(r);
				return true;
			}
			return false;
			
	}
	
	/**
	 * Works similarly to addRider() method but instead adding a train to either north or south queue
	 * @param t is the variable of the train we are adding to the queue
	 * @return the string of the passengers getting off the train
	 * Only if/else statements so constant running time O(1)
	 */
	public String addTrain(Train t) {
		t.updateStation(name);
		if (t.goingNorth() ==true) {
			northBoundTrains.enqueue(t);
			}
		if (t.goingNorth() == false) {
			southBoundTrains.enqueue(t);
		}
		return name + " Disembarking Passengers: \n" + t.disembarkPassengers();
	}
	
	/**
	 * Here we remove the south bound train from queue add all south bound riders, previously created, (until capacity reached) and then place back the train 
	 * @return the south bound train variable, but if no one wants to get on then return null
	 * The initial if statements are constant and the for loop is linear and as well the addPassenger() method in the for loop is linear creating
	 * a running time of O(n^2)
	 */
	public Train southBoardTrain() {
		if (southBoundTrains.numEntries !=0) {
			Train southTrain = southBoundTrains.front();
			southBoundTrains.dequeue();
			if (southTrain != null) {											//1
				if (southTrain.hasSpaceForPassengers()==true) {					//1
					for (int x = 0; x< southTrain.passengers.length; x++) {		//n
						if (southTrain.passengers[x]==null) {					//1
							if (southBoundRiders.numEntries != 0) {				//1
								Rider rider = southBoundRiders.front();
								southTrain.addPassenger(rider);					//n
								southBoundRiders.dequeue();
							}
						}
					}
				}
			}
			return southTrain;
		}
		else {
			return null;
		}
	}
	/**
	 * Works similar to southBoardTrain() method but 
	 * Here we remove the north bound trains from queue add all north bound riders, previously created, (until capacity reached) and then place back the train 
	 * @return the north bound train variable, but if no one wants to get on then return null
	 * The initial if statements are constant and the for loop is linear and as well the addPassenger() method in the for loop is linear creating
	 * a running time of O(n^2)
	 */
	public Train northBoardTrain() {
		if (northBoundTrains.numEntries!=0) {
			Train northTrain = northBoundTrains.front();
			northBoundTrains.dequeue();
			if (northTrain != null) {
				if (northTrain.hasSpaceForPassengers()==true) {
					for (int x = 0; x <northTrain.passengers.length; x++) {
						if (northTrain.passengers[x] == null) {
							if (northBoundRiders.numEntries!=0) {
								Rider rider = northBoundRiders.front();
								northTrain.addPassenger(rider);
								northBoundRiders.dequeue();
							}
						}
					}
				}
				
			}
			return northTrain;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Swap train direction using method from train class and then put train into south bound queue and remove from north bound queue
	 * Uses dequeue() method from queue class so a linear run time is created making the running time O(n)
	 */
	public void moveTrainNorthToSouth() {
		Train swapTrain = northBoundTrains.front();
		southBoundTrains.enqueue(swapTrain);
		northBoundTrains.dequeue();
		swapTrain.swapDirection();
	}
	
	/**
	 * Swap train direction using method from train class and then put train into north bound queue and remove from south bound queue
	 * Uses dequeue() method from queue class so a linear run time is created making the running time O(n)
	 */
	public void moveTrainSouthToNorth() {
		Train swapTrain = southBoundTrains.front();
		northBoundTrains.enqueue(swapTrain);
		southBoundTrains.dequeue();
		
		swapTrain.swapDirection();
	}
	
	/**
	 * The toString() method were we add the data for each variable (station, north bound riders and trains, and south bound riders and trains)
	 * then return the String created.
	 * Simply adds values to a string so a constant run time O(1)
	 */
	@Override
	public String toString() {
		String stationString = "Station: "+ stationName()+"\n";
		stationString += northBoundTrains.numEntries+" north-bound trains waiting\n";
		stationString += southBoundTrains.numEntries+ " south-bound trains waiting\n";
		stationString += northBoundRiders.numEntries+ " north-bound passengers waiting\n";
		stationString += southBoundRiders.numEntries+ " south-bound passengers waiting\n" + "\n";
		return stationString;
	}
	
	/**
	 * @return the station name
	 * A return so constant running time O(1)
	 */
	public String stationName() {
		return name;
	}
	
	/**
	 * If the inputed object station is not null and equal to the current station then return true otherwise return false
	 * If/else statements so constant running time O(1)
	 */
	@Override
	public boolean equals(Object o) {
		if (o != null) {
			Station station = (Station) o;
			if (station.stationName()==this.stationName()) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
}
