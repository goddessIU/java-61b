import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testCorrect() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque();
        int items = 0;
        for (int i = 0; i <10000; i++) {
            int choice;
            if (items <= 0) {
                choice = StdRandom.uniform(0, 2);

            } else {
                choice = StdRandom.uniform(0, 4);
            }
            int k = StdRandom.uniform(0, 100);
            int expected;
            int actual;
            switch (choice) {
                case 0:
                    ads.addFirst(k);
                    sad.addFirst(k);
                    items++;
                    break;
                case 1:
                    ads.addLast(k);
                    sad.addLast(k);
                    items++;
                    break;
                case 2:
                    expected = ads.removeFirst();
                    actual = sad.removeFirst();
                    assertEquals("removeFirst(), student was " + expected + ", correct was " + actual, expected, actual);
                    items--;
                    break;
                case 3:
                    expected = ads.removeLast();
                    actual = sad.removeLast();
                    assertEquals("removeFirst(), student was " + expected + ", correct was " + actual, expected, actual);
                    items--;
                    break;
            }
        }
    }
}
