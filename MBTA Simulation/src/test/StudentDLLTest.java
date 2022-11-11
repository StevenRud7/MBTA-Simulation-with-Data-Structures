/**
* The Student Double Linked List tests which tests that each method works correctly and that the nodes work correctly as well
* Known Bugs: None
*
* @author Steven Rud
* stevenrud@brandeis.edu
* March 4th, 2022
* COSI 21A PA1
*/
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.DoubleLinkedList;
import main.Node;

class StudentDLLTest {

	@Test
	void LinkedListTest() {
		DoubleLinkedList l = new DoubleLinkedList();
		assertEquals(0, l.size());
		l.insert(7);
		l.insert(3);
		l.insert(25);
		l.insert(19);
		assertEquals(4, l.size());
		assertEquals(7,l.head.data);
		assertEquals(19,l.tail.data);
		assertEquals("732519",l.toString());
		l.delete(7);
		assertEquals(3, l.getFirst().data);
		assertEquals(3,l.size());
		Node n = l.head.nextNode;
		assertEquals(19,n.nextNode.data);
		assertEquals(3,n.prevNode.data);
		l.delete(3);
		l.delete(19);
		assertEquals("25",l.toString());
		l.delete(25);
		l.insert(6);
		assertEquals(6, l.head.data);
		assertEquals(6, l.tail.data);
		l.delete(6);
		assertEquals(null, l.head);
		assertEquals(null, l.tail);
		
	}

}
