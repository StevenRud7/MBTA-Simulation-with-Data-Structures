/**
* The Student Queue tests which tests that each method works correctly
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

import main.Queue;

class StudentQueueTest {

	@Test
	void queueTest() {
		Queue q = new Queue(5);
		assertEquals(0,q.size());
		q.enqueue(7);
		assertEquals(7,q.front());
		assertEquals(7,q.back());
		q.enqueue(10);
		q.enqueue(2);
		q.enqueue(15);
		assertEquals(4,q.size());
		assertEquals("710215",q.toString());
		q.dequeue();
		assertEquals(10,q.front());
		q.enqueue(7);
		assertEquals(7,q.back());
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		assertEquals(0,q.size());
		assertEquals(null, q.q[q.head]);
		assertEquals(null, q.q[q.tail]);
		q.enqueue(4);
		assertEquals("4",q.toString());
		
	}

}
