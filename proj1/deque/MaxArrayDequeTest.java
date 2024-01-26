package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {


    @Test
    public void simpleTest(){
        MaxArrayDeque<Integer> integers = new MaxArrayDeque<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                }
        );
        for(int i = 0; i < 33; i++){
            integers.addFirst(33 - i);
        }
        integers.max();
        assertEquals(33, (int)integers.max());

        assertEquals(1, (int)integers.max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }));
    }
}
