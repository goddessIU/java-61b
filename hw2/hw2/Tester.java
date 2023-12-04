package hw2;

import org.junit.Test;

public class Tester {
    @Test
    public void test1() {
        Percolation pec = new Percolation(2);
        pec.open(0, 0);
        System.out.println(pec.isOpen(0, 0));
        System.out.println(pec.numberOfOpenSites());
        System.out.println(pec.isFull(0, 0));
        System.out.println(pec.percolates());

        pec.open(1, 0);
        System.out.println(pec.isOpen(1, 0));
        System.out.println(pec.numberOfOpenSites());
        System.out.println(pec.isFull(1, 0));
        System.out.println(pec.percolates());
    }
}
