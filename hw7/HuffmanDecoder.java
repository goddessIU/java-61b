import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie bt = (BinaryTrie) or.readObject();
        Integer len = (Integer) or.readObject();
        BitSequence bs = (BitSequence) or.readObject();
        char[] chs = new char[len];

        int i = 0;
        while (i < len) {
            Match m = bt.longestPrefixMatch(bs);

            bs = bs.allButFirstNBits(m.getSequence().length());
            chs[i++] = m.getSymbol();
        }


        FileUtils.writeCharArray(args[1], chs);
    }
}
