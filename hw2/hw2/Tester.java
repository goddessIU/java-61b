package hw2;

import org.junit.Test;

public class Tester {
    @Test
    public void test1() {
        Percolation pec = new Percolation(3);
        pec.open(0, 0);
        System.out.println(pec.isOpen(0, 0));
        System.out.println(pec.numberOfOpenSites());

        pec.open(0, 2);
        System.out.println(pec.isOpen(0, 2));
        System.out.println(pec.numberOfOpenSites());
        System.out.println(pec.isFull(0, 0));
        System.out.println(pec.isFull(0, 2));

        pec.open(1, 0);
        pec.open(2, 0);
        System.out.println(pec.numberOfOpenSites());
        System.out.println(pec.percolates());
    }
}
