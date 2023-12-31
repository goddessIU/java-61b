public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void check1() {
        System.out.println("check1");
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
//        boolean passed = checkEmpty(true, lld1.isEmpty());
//        lld1.addFirst(10);
//        passed = checkEmpty(false, lld1.isEmpty()) && passed;
//        lld1.removeFirst();
//        passed = checkEmpty(true, lld1.isEmpty()) && passed;
//        int num = 300;
//        for (int i = 0; i  < num; i++) {
//            lld1.addFirst(i);
//        }
//        for (int i = 0; i < num - 20; i++) {
//            System.out.println(lld1.removeLast());
//        }
////        for (int i = 0; i < num; i++) {
////            lld1.addFirst(i);
////        }
//        for (int i = 0; i < 20; i++) {
//            System.out.println(lld1.get(i));
//        }
//        lld1.addFirst(0);
//        System.out.println(lld1.removeLast());
//        lld1.addLast(2);
//        System.out.println(lld1.get(0));
        lld1.addFirst(0);
        lld1.addLast(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        System.out.println(lld1.removeLast());
        System.out.println(lld1.removeLast());
        lld1.addLast(6);
        lld1.get(0);
        lld1.addLast(8);
        lld1.removeLast()  ;
        lld1.removeLast()  ;
        lld1.removeLast()  ;
        lld1.removeFirst() ;
        lld1.addFirst(13);
        lld1.removeLast() ;
        lld1.addFirst(15);
        lld1.addLast(16);
        lld1.get(0)      ;
        lld1.get(1)      ;
        lld1.removeFirst()    ;
        lld1.removeFirst()   ;
        lld1.addFirst(21);
        lld1.addLast(22);
        System.out.println(lld1.removeLast());;
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
//        addIsEmptySizeTest();
//        addRemoveTest();
        check1();
    }
}
