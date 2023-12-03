import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('z', 'y'));
        assertFalse(offByOne.equalChars('d', 'n'));
    }
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.


    // Your tests go here.
    Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
}
