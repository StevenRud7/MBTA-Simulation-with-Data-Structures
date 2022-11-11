/**
 * The Min Priority Queue class where values are inserted and repositioned in the heap
 * 
* @author Steven Rud
* stevenrud@brandeis.edu
* May 1st, 2022
* COSI 21A PA3
*
*/
public class MinPriorityQueue {
	public int cap; //needed variables 
	public int insCount = -1;
	public HashMap hash;
	public GraphNode[] node;
	public int checked;
	public GraphNode[] checkNode;
	
	/**
	 * Constructor which sets all GraphNodes and values
	 * @param cap is the size of the Queue
	 */
	public MinPriorityQueue(int cap) {
		this.cap = cap;
		this.hash = new HashMap(cap);
		this.node = new GraphNode[cap];
		this.checked = -1;
		this.checkNode = new GraphNode[cap];
	}
	
	
	/**
	 * If the hash doesn't have the key already then we add the key to the hash and heapify up to readjust
	 * @param g is the node we are adding
	 */
	public void insert(GraphNode g) {
		if (hash.hasKey(g) != true) {
			this.insCount+=1;
			node[insCount] = g;
			this.hash.set(g,this.insCount);
			heapUp(g);
		}
		cap+=1;
	}
	
	/**
	 * If not empty then get first element and set it in the hash to reposition the hash table so we can retrieve the highest priority element
	 * @return
	 */
	public GraphNode pullHighestPriorityElement() {
		int first = 0;
		cap -=1;
		if (isEmpty()==false) {
			GraphNode high = this.node[first];
			this.hash.set(high, -1);
			checked+=1;
			checkNode[checked] = high; //keep track of what was checked 
			node[first] = node[this.insCount];
			this.insCount-=1; //remove one inserted value as this is getting pulled
			if (this.insCount > -1) {
				hash.set(node[first], first); //reposition if at at least 1 value was inserted
				heapDown(first);
			}
			return high;
		}
		else {
			return null;
		}
		
	}
	
	/**
	 * Simply call the heapify up as it does the rebalancing
	 * @param g
	 */
	public void rebalance(GraphNode g) {
		heapUp(g);
	}
	/**
	 * If nothing was inserted then empty otherwise not empty
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		if (insCount > -1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 *  go through the values and see if the node was checked
	 * @param g the graph node we are checking
	 * @return true if the value was checked, false otherwise
	 */
	public boolean ifChecked(GraphNode g) {
		int count = 0;
		while (count<checked) {
			if (checkNode[count].getId().equals(g.getId())) {
				return true;
			}
			count+=1;
		}
		return false;
	}
	
	/**
	 * Go through the Graphnode and reposition all required values
	 * @param g the graph node we are heapifiying up
	 */
	public void heapUp(GraphNode g) {
		int val = this.hash.getValue(g);
		int Pval = 0;
		if (val >= 1) {
			Pval = (val - 1) /2; //find the parent value 
		}
		while (val>0 && this.node[Pval].priority > this.node[val].priority) {
			hash.set(node[val], Pval); //set the parent and regular value in the hash table
			hash.set(node[Pval], val);
			GraphNode tempNode = this.node[val]; //move around the values using a temp placeholder
			this.node[val] = this.node[Pval];
			this.node[Pval] = tempNode;
			val = Pval;
		}
	}
	
	/**
	 * The heapify down where we find the right, left, and main value and move them over using recursive calls
	 * @param value is the value were heapifiying down from
	 */
	public void heapDown(int value) {
		int Lvalue = 2*value + 1;
		int Rvalue = 2*value + 2;
		int DownVal = value;
        if (Rvalue <= this.insCount && this.node[Rvalue].priority < this.node[DownVal].priority) { //edge cases for the heap
        	DownVal = Rvalue;
        }
        if (Lvalue <= this.insCount && this.node[Lvalue].priority < this.node[value].priority) {
        	DownVal = Lvalue;
        }
        if (value != DownVal) { //the recusrion stops when none of the if statements are met 
            hash.set(this.node[value], DownVal); //set each value in the heap
            hash.set(this.node[DownVal], value);
            int tempVal = value; //using temp switching around the postions
            value = DownVal;
            DownVal = tempVal;
            heapDown(DownVal); //recall
            }
	}
}
