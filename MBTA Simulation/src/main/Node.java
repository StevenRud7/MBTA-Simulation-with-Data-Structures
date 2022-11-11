/**
* The Node Class which implements the known interface of nodes and its functionality to represent the elements in the linked list 
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/
package main;

public class Node<T> {
	
	public T data; //Initialize the needed variables 
	public Node<T> prevNode;
	public Node<T> nextNode;
	
	/**
	 * The constructor for the node which sets the data value for the node
	 * @param data is the inputed value for the data in the node
	 * Simply initializes the value so constant running time of O(1)
	 */
	public Node(T data) {
		this.data = data;
	}
	
	/**
	 * Sets an inputed data value which could be used to set a specific node's value.
	 * @param data is the inputed value for the data in the node
	 * Simply initializes the value so constant running time of O(1)
	 */
	public void setData(T data) {
		this.data=data;
	}
	
	/**
	 * It sets the position for the next Node to be connected to in the linked list
	 * @param next is the inputed node that will be the next node
	 * Initializes the value so constant running time of O(1)
	 */
	public void setNext(Node<T> next) {
		this.nextNode = next;
	}
	
	/**
	 * It sets the position for the previous Node to be connected to in the linked list
	 * @param next is the inputed node that will be the previous node
	 * Initializes the value so constant running time of O(1)
	 */
	public void setPrev(Node<T> prev) {
		this.prevNode = prev;
	}
	
	/**
	 * Assuming the next node isn't null it returns the next node 
	 * @return the next node 
	 * An if/else statement so a constant running time of O(1)
	 */
	public Node<T> getNext() {
		if (nextNode != null) {
			return nextNode;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Similar to getNext assuming the previous node isn't null it returns the previous node 
	 * @return the previous node 
	 * An if/else statement so a constant running time of O(1)
	 */
	public Node<T> getPrev() {
		if (prevNode != null) {
			return prevNode;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Just return the current data value which represents a specific value
	 * @return the data variable 
	 * Just a return so O(1)
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Use the known toString() functionality which works on the node to convert the data value into a string and return it
	 * Just a return so O(1)
	 */
	@Override
	public String toString() {
		return data.toString();
	}
}
