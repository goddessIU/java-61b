import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> mapper = new HashMap<>();
        for (char c : inputSymbols) {
            if (mapper.containsKey(c)) {
                mapper.put(c, mapper.get(c) + 1);
            } else {
                mapper.put(c, 1);
            }
        }
        return mapper;
    }
    public static void main(String[] args) {
        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        char[] chs = FileUtils.readFile(args[0]);
        Map<Character, Integer> mapper = buildFrequencyTable(chs);
        BinaryTrie bt = new BinaryTrie(mapper);
        ow.writeObject((Serializable) bt);
        Map<Character, BitSequence> table = bt.buildLookupTable();
        BitSequence bs = new BitSequence();
        List<BitSequence> bss = new ArrayList<>();
        for (char c : chs) {
            bss.add(table.get(c));
        }
        BitSequence ret = BitSequence.assemble(bss);
        ow.writeObject((Integer)chs.length);
        ow.writeObject(ret);
    }
}
