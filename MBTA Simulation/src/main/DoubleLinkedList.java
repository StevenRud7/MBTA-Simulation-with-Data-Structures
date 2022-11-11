/**
* The Double Linked List Class which implements the known interface of double linked list and here I add all the necessary functionality of it
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/
package main;

public class DoubleLinkedList<T> {
	
	public Node<T> head;	//Add needed variables 
	public Node<T> tail;
	public int len;
	
	
	/**
	 * Constructor for double linked list which just sets the value for each variable
	 * Simply sets values so a constant running time of O(1) 
	 */
	public DoubleLinkedList() {
		len = 0;
		head = null;
		tail = null;
		
	}
	/**
	 * Gets the first value of the linked list by returning the head variable.
	 * @return the head of the linked list unless the head is empty then we return null
	 * An if/else statement so a constant running time of O(1)
	 */
	public Node<T> getFirst() {
		if (head == null) {		//1
			return null;
		}
		else {					//1
			return head;
		}
	}
	
	/**
	 * Inserts an inputed element into the end of the linked list by first checking if the list is empty and in that case it makes it the 
	 * first and only value. Otherwise makes the value after the tail the inserted value and rearranges the pointers
	 * @param element is the value being inserted to the end of the double linked list
	 * Only an if/else statement so a constant run time for each making O(1)
	 */
	public void insert(T element) {
		Node<T> currNode = new Node<T>(element);
		if(head == null) {							//1
			head = currNode;
			tail = currNode;
			currNode.nextNode = null; //null both after and before to make it the only value surrounded by nulls
			currNode.prevNode = null;
		}
		else {										//1
			tail.nextNode = currNode;
			currNode.nextNode = null; //next is null and previous is tail so element is in the end of list
			currNode.prevNode = tail;
			tail = currNode;
		}
		len++;
	}
	
	/**
	 * Goes through the entire list and checks if the node matches the key node. If it is that node gets deleted and the pointers shift 
	 * around it
	 * @param key is the inputed value that the user wants to remove
	 * @return null when the key isn't in the linked list and if it is then just return the key's data.
	 * Contains primarily a while loop with nested if/else if/else statements which have a constant run time but the while loop creates the
	 * linear run time making the O(n)
	 */
	public T delete(T key) {
		Node<T> tempNode = head;
		while(tempNode != null) {					//linear (n) run time
			if (tempNode.data == key) { //check if node matches the key (if statement with constant run time)
				if(tempNode == head) { //if it is the first value of the linked list then we first check if that value is the only value in the list then set everything to null otherwise just delete the head and shift the list back. (If statement so run time of 1) 
					if(tempNode.getNext() == null) {  //1 run time
						head=null;
						tail=null;
						len--;
						return tempNode.data;
					}
					else { 							//1 run time
						head=tempNode.nextNode;
						tempNode.nextNode.prevNode=null;
						len--;
						return tempNode.data;
					}
				} //else if the key is located at the end of the list in which case we remove the final value and shift the list to the right
				else if(tempNode==tail) { 			//1 run time
					tempNode.prevNode.nextNode=null;
					tail=tempNode.prevNode;
					len--;
					return tempNode.data;
				} //otherwise when in middle of the linked list we delete it and readjusts the list 
				else { 								//constant run time
					tempNode.prevNode.nextNode=tempNode.nextNode;
					tempNode.nextNode.prevNode=tempNode.prevNode;
					len--;
					return tempNode.data;
				}
			}
			tempNode=tempNode.nextNode;
		}
		return null;
	}
	
	/**
	 * Works similar to the delete method at the beginning where it goes through each node until the node data matches the key and then it returns the node
	 * @param key is the inputed value that is checked for presence in the list.
	 * @return the node where key is present or return null if it isn't present in the list.
	 * A while loop with an if statement so a linear run time O(n)
	 */
	public T get(T key) {
		Node<T> tempNode = head;
		while(tempNode!=null) {				//n
			if(tempNode.data == key) {		//1
				return (T) tempNode;
			}
			tempNode = tempNode.nextNode;
		}
		return null;
	}
	
	/**
	 * @return the len variable which represents the amount of nodes in the double linked list.
	 * Simple return so constant run time O(1)
	 */
	public int size() {
		return len;
	}
	
	/**
	 * Creates a temp string and converts each indiviual node to a string and then adds that to the temp string and the return that temp String
	 * A while loop so a linear run time of O(n)
	 */
	@Override
	public String toString() {
		Node<T> tempNode = head;
		String tempString = "";
		while(tempNode != null) {
			tempString += tempNode.toString();
			tempNode=tempNode.nextNode;
			
		}
		return tempString;
	}
}
