import static org.junit.Assert.assertTrue;

public class Test {
    private static String[] someNegative = {"2", "100", "39", "46", "70000"};

    public static void assertIsSorted(String[] a) {
        for (int i = 1; i < a.length; i++) {
            assertTrue(a[i].compareTo(a[i - 1]) >= 0);
        }
    }

    @org.junit.Test
    public void testNaiveWithNonNegative() {
        String[] sortedNonNegative = RadixSort.sort(someNegative);
        for (String t : sortedNonNegative) {
            System.out.println(t);
        }
        assertIsSorted(sortedNonNegative);
    }
}
