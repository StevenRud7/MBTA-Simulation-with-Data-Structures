/**
 * The HashMap class where add values and test whether they are already elements and as well retrieve key and value data
 * 
* @author Steven Rud
* stevenrud@brandeis.edu
* May 1st, 2022
* COSI 21A PA3
*
*/


public class HashMap {
	
	public Entry[] hash; //elements
	public int cap;
	
	/**
	 * constructor for Hashmap creating a new hashmap with given cap
	 * @param cap is the size of the entry
	 */
	public HashMap(int cap) {
		this.cap = cap; 
		this.hash = new Entry[cap];
	}
	
	/**
	 * Add a key to the Hashmap if it isn't already there. 
	 * @param key is the node we are adding
	 * @param value is the value of the key we want to add
	 */
	public void set(GraphNode key, int value) {
		int testkey = (hashFunc(key.getId())) % this.cap; //create new values to add the node
		Entry[] doubleHash = new Entry[this.cap*2];
		int count = 0;
		for (int i = 0; i<this.cap; i++) { //go through whole hash
			if (hash[testkey]==null) {
				hash[testkey] = new Entry(key, value);
				count++;
				if (count/this.cap >= 0.5) { //if half or more null values then ensure it is inputed in right place
					for (int j = 0; j<this.cap; j++ ) {
						if (hash[j] != null) {
							GraphNode k = hash[j].key;
							int tempV = hash[j].val;
							int x = 0;
							int testkey2 = (hashFunc(key.getId()) + x) % this.cap;
							while (doubleHash[testkey2] != null) {
								x++;
								testkey2 = (hashFunc(key.getId()) + i) % this.cap;
							}
							doubleHash[testkey2] = hash[j];		
					}
				}
				hash= doubleHash;
				break;
			}
		}
		else if (hash[testkey].val==-1) { //edge cases 
			hash[testkey] = new Entry(key, value);
			break;
			}
		else if (hash[testkey].key.getId().equals(key.getId())) { //if there already is an entry for the key we just swap the value
			hash[testkey].val = value;
			break;
		}
		else { //reset if none of the conidtions are met
			testkey = (hashFunc(key.getId()) + i) % this.cap;
		}
			
		}
		return;
		
	}
	
	/**
	 * Goes through hash until we reach given node and then returns its value
	 * @param g is the node we want to find the value of 
	 * @return the value of the given node
	 */
	public int getValue(GraphNode g) {
		int testkey = (hashFunc(g.getId())) % this.cap;
		for (int i = 0; i<this.cap && null != hash[testkey]; i++) { //for loop going through hash
			if (hash[testkey].key.getId().equals(g.getId())) {
				return hash[testkey].val;
			}
			else {
				testkey = (hashFunc(g.getId()) + i) % this.cap; //change to next position
			}
		}
		return -1000; //placeholder method
	}
	
	/**
	 * Similar to getValue but instead returns true if key is present otherwise false
	 * @param g is the inputed node for which we want to check if it has the key 
	 * @return true if it has the key and false if not
	 */
	public boolean hasKey(GraphNode g) {
		int testkey = (hashFunc(g.getId())) % this.cap;
		for (int i = 0; i<this.cap && null != hash[testkey]; i++) { //for loop through hash
			if (hash[testkey].key.getId().equals(g.getId())) {
				return true;
			} 
			else {
				testkey = (hashFunc(g.getId()) + i) % this.cap; //change to next position
			}

		}
		return false;
	}
	
	/**
	 * go through the hash and add to temp value and then find the hash function value
	 * @param k the string input we want the function for
	 * @return the hash function
	 */
	public int hashFunc(String k) {	    
	    int val = 0;
	    int count = 0;
	    int hFunc = 0;
	    while (count<k.length()) { 
	    	val += k.charAt(count);
	    	count+=1;
	    }
	    hFunc = val %this.cap;
		return hFunc;
	}
}
