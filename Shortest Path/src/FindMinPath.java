/**
 * The FindMinPath class which is the main class where we traverse through the heap and determine the direction needed for the quickest path 
 * 
* @author Steven Rud
* stevenrud@brandeis.edu
* May 1st, 2022
* COSI 21A PA3
*
*/

import java.io.FileWriter;
import java.io.IOException;

public class FindMinPath {
	
	public MinPriorityQueue Q; //initialize all variables
	private static int cap = 10000;
	public static boolean GoalBool=false;
	public static GraphNode GoalNode;
	public GraphNode root;
	
	/**
	 * The constructor which puts a general cap value to start the path and sets the other base values
	 * @param startCap is a placeholder value for the path size
	 */
	public FindMinPath(int startCap) {
		this.Q = new MinPriorityQueue(cap);
		this.GoalNode = null;
		this.cap = startCap;
	}
	
	/**
	 * The main method which sets the values and then creates the file to put the answers into
	 * @param args 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		GraphWrapper GW = new GraphWrapper(true);
		FindMinPath path = new FindMinPath(cap);
		GraphNode root = GW.getHome();
		root.priority = 0;
		path.travese(root);
		if (GoalBool != true || root.getId().equals(GoalNode.getId())) { //if the heap has 1 or less values then we wouldn't move anywhere so we will have an empty text file and break out of the method
			FileWriter output = new FileWriter("answers.txt"); //create the text file but add nothing
			output.close();
			return; //break out of the method
		}
		FileWriter output = new FileWriter("answers.txt"); //create the text file
		GraphNode tempNode = GoalNode;
		String tempString = "";
		while (tempNode.previousDirection != null && tempNode != null) { //go through entire heap and add all required directions
			tempString = tempNode.previousDirection + "\n" + tempString;
			tempNode = tempNode.previousNode;
			}
		System.out.println(tempString); //print string in console (not sure if required)
		output.write(tempString); //add answer to the file
		output.close();
	}

	/**
	 * Main traversal method which goes through each direction and determines the best possible route
	 * @param root is the root or home of the heap.
	 */
	public void travese(GraphNode root) {
		this.root = root;
		Q.insert(this.root);
		while (Q.isEmpty()!=true) {
			GraphNode curr = Q.pullHighestPriorityElement();
			if (curr.isGoalNode() !=false) { //if the highest priority element is the goal node
				this.GoalNode = curr;
				this.GoalBool = true;
				if (curr.hasNorth() == true) { //check the north
					int Nval = curr.getNorthWeight() + curr.priority; 
					GraphNode N = curr.getNorth();
					if(Q.hash.hasKey(N) != true && Q.ifChecked(N) != true) { //if it doesn't already have the key and wasn't already checked then we create a new direction
						N.priority = Nval;
						N.previousNode = curr;
						N.previousDirection = "North";
						Q.insert(N);
						}
					else if(Q.hash.getValue(N) >= 0){
						if (Nval < N.priority) {
							N.priority = Nval;
							N.previousNode = curr;
							N.previousDirection = "North";
							Q.rebalance(N);
							}				
						}
					}
				if (curr.hasSouth() != false) {
					GraphNode S = curr.getSouth();
					int Sval = curr.getSouthWeight() + curr.priority;
					if (Q.hash.hasKey(S) != true && Q.ifChecked(S) != true) {//if it doesn't already have the key and wasn't already checked then we create a new direction
						S.priority = Sval;
						S.previousNode = curr;
						S.previousDirection = "South";
						Q.insert(S);
						
						}
					
					else if (Q.hash.getValue(S) > -1){
						if (S.priority > Sval){
							S.priority = Sval;
							S.previousNode = curr;
							S.previousDirection = "South";
							Q.rebalance(S);
							
							}				
						}
					}
				
				if (curr.hasEast() == true) {
					GraphNode E = curr.getEast();
					int Eval = curr.getEastWeight() + curr.priority;
					if(Q.hash.hasKey(E) != true && Q.ifChecked(E) != true) { //if it doesn't already have the key and wasn't already checked then we create a new direction
						E.priority = Eval;
						E.previousNode = curr;
						E.previousDirection = "East";
						Q.insert(E);
						
						}
					
					else if (Q.hash.getValue(E) > -1){
						if (E.priority > Eval) {
							E.priority = Eval;
							E.previousNode = curr;
							E.previousDirection = "East";
							Q.rebalance(E);
							}				
						}
					}
				
				if (curr.hasWest() != false) { //if it doesn't already have the key and wasn't already checked then we create a new direction
					int Wval = curr.priority + curr.getWestWeight();
					GraphNode W = curr.getWest();
					if(Q.hash.hasKey(W) != true && Q.ifChecked(W) != true) { 
						W.priority = Wval;
						W.previousNode = curr;
						W.previousDirection = "West";
						Q.insert(W);
						
						}
					
					else if (Q.hash.getValue(W) > -1){
						if (W.priority > Wval) {
							W.priority = Wval;
							W.previousNode = curr;
							W.previousDirection = "West";
							Q.rebalance(W);
							
							}				
						}
					}
			}
			else { //if the current node is not the goal node then do the same thing as before but without replacing the goalnode value
				if (curr.hasNorth() == true) {
					int Nval = curr.getNorthWeight() + curr.priority; 
					GraphNode N = curr.getNorth();
					if(Q.hash.hasKey(N) != true && Q.ifChecked(N) != true) {
						N.priority = Nval;
						N.previousNode = curr;
						N.previousDirection = "North";
						Q.insert(N);
						}
					else if(Q.hash.getValue(N) >= 0){
						if (Nval < N.priority) {
							N.priority = Nval;
							N.previousNode = curr;
							N.previousDirection = "North";
							Q.rebalance(N);
							}				
						}
					}
				if (curr.hasSouth() != false) {
					GraphNode S = curr.getSouth();
					int Sval = curr.getSouthWeight() + curr.priority;
					if (Q.hash.hasKey(S) != true && Q.ifChecked(S) != true) {
						S.priority = Sval;
						S.previousNode = curr;
						S.previousDirection = "South";
						Q.insert(S);
						
						}
					
					else if (Q.hash.getValue(S) > -1){
						if (S.priority > Sval){
							S.priority = Sval;
							S.previousNode = curr;
							S.previousDirection = "South";
							Q.rebalance(S);
							
							}				
						}
					}
				
				if (curr.hasEast() == true) {
					GraphNode E = curr.getEast();
					int Eval = curr.getEastWeight() + curr.priority;
					if(Q.hash.hasKey(E) != true && Q.ifChecked(E) != true) {
						E.priority = Eval;
						E.previousNode = curr;
						E.previousDirection = "East";
						Q.insert(E);
						
						}
					
					else if (Q.hash.getValue(E) > -1){
						if (E.priority > Eval) {
							E.priority = Eval;
							E.previousNode = curr;
							E.previousDirection = "East";
							Q.rebalance(E);
							}				
						}
					}
				
				if (curr.hasWest() != false) {
					int Wval = curr.priority + curr.getWestWeight();
					GraphNode W = curr.getWest();
					if(Q.hash.hasKey(W) != true && Q.ifChecked(W) != true) {
						W.priority = Wval;
						W.previousNode = curr;
						W.previousDirection = "West";
						Q.insert(W);
						
						}
					
					else if (Q.hash.getValue(W) > -1){
						if (W.priority > Wval) {
							W.priority = Wval;
							W.previousNode = curr;
							W.previousDirection = "West";
							Q.rebalance(W);
							
							}				
						}
					}
			}
		}
	}

}
