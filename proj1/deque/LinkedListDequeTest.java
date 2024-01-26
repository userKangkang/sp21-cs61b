package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    }

    @Test
    public void randomizedTest(){
        java.util.ArrayDeque<Integer> L = new java.util.ArrayDeque<Integer>();
        LinkedListDeque<Integer> B = new LinkedListDeque<Integer>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.getLast(), B.get(B.size() - 1));
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 2 && L.size() > 0) {
                // getLast
                assertEquals(L.getLast(), B.get(B.size() - 1));
            } else if (operationNumber == 3 && L.size() > 0){
                // removeLast
                assertEquals(L.removeLast(), B.removeLast());
            }
        }
    }

    @Test
    public void equalTest() {
        LinkedListDeque<Integer> lld1;
        ArrayDeque<Integer> lld2;
        lld1 = new LinkedListDeque<>();
        lld2 = new ArrayDeque<>();
        assertEquals(lld1, lld2);
        assertEquals(lld2, lld1);
        int rand14 = 0;
        for(int i = 0; i < 1000; i++){
            rand14 = StdRandom.uniform(0, 4);
            if (rand14 == 0 || rand14 == 3) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                lld1.addLast(randVal);
                lld2.addLast(randVal);
                assertEquals(lld1.get(lld1.size() - 1), lld2.get(lld2.size() - 1));
                assertEquals(lld1, lld2);
                assertEquals(lld2, lld1);
            } else if (rand14 == 1) {
                // size
                assertEquals(lld1.size(), lld2.size());
            } else if (rand14 == 2 && lld1.size() > 0){
                // removeLast
                assertEquals(lld1.removeFirst(), lld2.removeFirst());
                assertEquals(lld1, lld2);
                assertEquals(lld2, lld1);
            }
        }

        assertEquals(lld1, lld2);
        assertEquals(lld2, lld1);
    }

    @Test
    public void stringEquals(){
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        lld1.addFirst("Alpha");
        ad1.addLast("Alpha");

        lld1.addLast("Beta");
        ad1.addLast("Beta");

        lld1.removeFirst();
        ad1.removeFirst();

        lld1.addLast("Theta");
        ad1.addLast("Theta");

        assertEquals(ad1, lld1);
    }
}
