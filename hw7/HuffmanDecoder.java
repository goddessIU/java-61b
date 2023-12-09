import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie bt = (BinaryTrie) or.readObject();
        BitSequence bs = (BitSequence) or.readObject();
        List<Character> chList = new ArrayList<>();

        int i = 0;
        while (bs.length() > 0) {
            Match m = bt.longestPrefixMatch(bs);

            bs = bs.allButFirstNBits(m.getSequence().length());
            chList.add(m.getSymbol());
            System.out.print(m.getSymbol());
        }

        char[] chs = new char[chList.size()];
        for (char c : chList) {
            chs[i++] = c;
        }

        FileUtils.writeCharArray(args[1], chs);
    }
}
