/**
* The Queue class which implements the functionality of the known interface Queue and the needed commands behind it.   
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/
package main;

import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q; //The general variables that would be needed: The queue, its head, its tail, and amount of entries into the queue.
	public int head;
	public int tail;
	public int numEntries;
	
	/**
	 * Constructor for the queue, creating the queue and starting all values at 0
	 * @param capacity the max amount of values in the queue
	 * Simply initializes variables so a constant running time of O(1)
	 */
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
		head = 0;
		tail = 0;
		numEntries = 0;
				
	}
	
	/**
	 * Puts the given element to the end of the queue unless queue empty in which case just puts it at the head
	 * @param element is the variable that is inputed to be inserted in the queue
	 * A simple if/else statement so a constant running time of O(1) 
	 */
	public void enqueue(T element) {
		if (numEntries < q.length) {
			q[tail] = element;
			if (numEntries==0) {			//1
				q[head] = element;
			}
			numEntries++;
			tail++;
			
		}
	}
	
	/**
	 * Moves all the elements in the queue back and then removing the last element by making it null unless the queue is empty
	 * in which case returns an exception
	 * Due to the for loop creating a linear running time of O(n)
	 */
	public void dequeue() { 
		if (numEntries == 0) {						//1
			throw new NoSuchElementException();
		}
		for (int x = 0; x<=q.length-2; x++) {		//n
			q[x] = q[x+1];
		}
		q[tail] = null;
		tail--;
		numEntries--;
		
	}
	
	/**
	 * Simply returns the head of the queue unless queue is empty and it would return an exception.
	 * @return the head of the queue (its front)
	 * Only an if/else statement so a constant running time of O(1) 
	 */
	public T front() {
		if (numEntries == 0) {						//1
			throw new NoSuchElementException();
		}
		else {										//1
			return q[head];
		}
	}
	
	/**
	 * Similar as the front() method but instead returns the value at the end of the queue unless queue is empty and it would return an exception
	 * @return the value at end of the queue (tail)
	 * Only if/else statement so a constant running time of O(1)
	 */
	public T back() {
		if (numEntries == 0) {						//1
			throw new NoSuchElementException();
		}
		else {										//1
			return q[tail-1];
		}
	}
	
	/**
	 * @return the numEntries variable which represents the number of elements in the queue (size)
	 * Just a return so O(1)
	 */
	public int size() {
		return numEntries;
	}
	
	/**
	 * creates a new empty string and then adds each value from the queue into the string 
	 * A while loop creating a linear running time O(n)
	 */
	@Override
	public String toString() {
		String queueString = "";
		int x = 0;
		while(q[x] != null) {		//n
			queueString+=q[x];
			x++;
		}
		return queueString;
	}
}
