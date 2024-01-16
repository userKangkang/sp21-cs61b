package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> correct = new AListNoResizing<Integer>();
        BuggyAList<Integer> buggy = new BuggyAList<Integer>();

        correct.addLast(4);
        correct.addLast(5);
        correct.addLast(6);
        buggy.addLast(4);
        buggy.addLast(5);
        buggy.addLast(6);

        assertEquals(correct.size(), buggy.size());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<Integer>();
        BuggyAList<Integer> B = new BuggyAList<Integer>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.getLast(), B.getLast());
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 2 && L.size() > 0) {
                // getLast
                assertEquals(L.getLast(), B.getLast());
            } else if (operationNumber == 3 && L.size() > 0){
                // removeLast
                assertEquals(L.removeLast(), B.removeLast());
            }
        }
    }
}
