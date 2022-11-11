/**
 * The Entry Class which simply enters a graphnode and value into the map
 * 
* @author Steven Rud
* stevenrud@brandeis.edu
* May 1st, 2022
* COSI 21A PA3
*
*/

public class Entry {
	
	public int val;
	public GraphNode key;
	
	/**
	 * Entry Constructor
	 * @param k is graphnode
	 * @param v is value
	 */
	public Entry(GraphNode k, int v) {
		this.key=k;
		this.val=v;
	}
}
