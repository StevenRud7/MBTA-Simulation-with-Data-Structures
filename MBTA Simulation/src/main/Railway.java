/**
* The Railway Class which adds all previous classes, the rider, train, and station, to the railway and then simulates the railway running
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/
package main;

public class Railway {

	public DoubleLinkedList<Station> railway; //Initiate needed variables 
	public String[] stationNames;
	
	public int stationCount;
	public Node<Station> head;
	
	/**
	 * Railway constructor which defines each variable 
	 * Simple constructor to constant running time of O(1)
	 */
	public Railway() {
		stationNames= new String[18];
		railway = new DoubleLinkedList<Station>();
		
		stationCount=0;
	}
	
	/**
	 * Adds the station to the railway and updates every other variable 
	 * @param s the variable for the station to be added to the railway
	 * Calls insert and adds to variables so constant running time of O(1)
	 */
	public void addStation(Station s) {
		stationNames[stationCount] = s.stationName();
		railway.insert(s);
		stationCount++;
	}
	
	/**
	 * Adds the rider to the railway and as well properly sets their direction so they could be added to the right station
	 * @param r is the rider variable we are adding to the station
	 * Separate for and while loop so each have a linear running time and each if/else statement have a constant running time and so since
	 * the while and for loops are not nested the running time stays linear at O(n)
	 */
	public void addRider(Rider r) {
		setRiderDirection(r);												//n
		Node<Station> stationNode = railway.head;
		while (stationNode != null) {										//n
			if (r.getStarting().equals(stationNode.data.stationName())) {	//1
				stationNode.data.addRider(r); //add the rider to the appropriate station once direction has been sorted
			}
			stationNode = stationNode.nextNode;
		}
		
	}
	
	/**
	 * Add the train to the correct station in the railway by ensuring the station it is in matches the railways station
	 * @param t is the train variable we are adding to the station in the railway
	 * A while loop with an if statement which calls itself in the while loop so a running time of O(n^2)
	 */
	public void addTrain(Train t) {
		Node<Station> stationNode = railway.head;
		while (stationNode != null) {											//n
			if (t.currentStation.equals(stationNode.data.stationName())) { 		//1
				stationNode.data.addTrain(t);									//n^2
			}
			stationNode = stationNode.nextNode;
		}
	}
	
	/**
	 * Ensures that when the rider is being added to the station they are in the right direction by checking their start and end stations.
	 * @param r is the rider variable we are checking for and setting the right direction for
	 * For loop with if/else statements creating a total linear running time of O(n)
	 */
	public void setRiderDirection(Rider r) {
		int s=0;
		int e=0;
		for (int x = 0; x<stationCount; x++) {				//n
			if (stationNames[x].equals(r.getStarting())) {			//1
				s = x;
			}
			else if (stationNames[x].equals(r.getDestination())) {	//1
				e =x;
			}
		}
		if (s > e) {										//1
			r.swapDirection();								//1
		}
	}
	
	/**
	 * The main method which goes through most actions including swapping train direction if they are in Alewife or Braintree station 
	 * otherwise boarding all riders and moving the train to next stations and removing any necessary passengers
	 * @return the String containing everything that occurred during the simulation
	 * The while loop with all commands nested inside including calling the call of updateTrain() which has a linear run time and the 
	 * dequeue methods which also has a linear run time created a running time of O(n^3)
	 */
	public String simulate() {
		String simString = "\n";
		Node<Station> stationNode = railway.head;

		while (stationNode != null) {											//n
			if(stationNode.getData().stationName().equals("Alewife")){			//1
				simString +=stationNode.data.toString();
				//simString +="\n";
				Train sTrain = stationNode.data.southBoardTrain(); 
				if (sTrain != null) {											//1
					simString +=stationNode.nextNode.data.addTrain(sTrain); //first deal with all riders getting them off the train
					sTrain.updateTrain();										//n^2
					simString += sTrain.toString() +"\n";
				}
				if(stationNode.data.northBoundTrains.numEntries!=0) {			//1
					Train nTosTrain = stationNode.data.northBoundTrains.front();
					nTosTrain.swapDirection();									//1
					stationNode.data.southBoundTrains.enqueue(nTosTrain);	//swap the trains directions
					stationNode.data.northBoundTrains.dequeue();				//n^3
					
				}

			}
			else if (stationNode.getData().stationName().equals("Braintree")) {	//1
				simString +=stationNode.data.toString();
				Train nTrain = stationNode.data.northBoardTrain(); 
				if (nTrain != null) {											//1
					simString +=stationNode.prevNode.data.addTrain(nTrain);	//again first get off all passengers
					simString += nTrain.toString()+"\n";
				}
				if(stationNode.data.southBoundTrains.numEntries!=0) {			//1
					Train sTonTrain = stationNode.data.southBoundTrains.front();
					sTonTrain.swapDirection();									//1
					stationNode.data.northBoundTrains.enqueue(sTonTrain); //Now like in Alewife swap the train direction
					stationNode.data.southBoundTrains.dequeue();				//n^3
				}
			}
			else {																//1
				simString +=stationNode.data.toString();
				Train nTrain = stationNode.data.northBoardTrain(); 
				Train sTrain = stationNode.data.southBoardTrain(); 
				
				if (nTrain !=null) {											//1
					simString +=stationNode.prevNode.data.addTrain(nTrain);
					simString += nTrain.toString()+"\n";
				}
				if (sTrain !=null) {											//1
					simString +=stationNode.nextNode.data.addTrain(sTrain); //The condition for any other station where we remove it from one station and add it to the new one
					simString +=sTrain.toString()+"\n";
				}
				
				
			}
			stationNode=stationNode.nextNode;
		}
		return simString;
	}
	
	
	/**
	 * The toString() method which goes through each station and returns the string representation of it
	 * A for loop creating a linear running time of O(n) 
	 */
	@Override
	public String toString() {
		String railString ="";
		if (stationNames[0] !=null) {
			for (int x = 0; x <stationNames.length; x++) {
				if (stationNames[x]==null) {
					return railString;
				}
				railString +=stationNames[x]+ " ";
			}
			return railString;
		}
		else {
			return null;
		}
	}
}
