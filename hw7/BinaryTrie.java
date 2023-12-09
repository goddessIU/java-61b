import edu.princeton.cs.algs4.MinPQ;

import java.io.CharConversionException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {

    private class Node implements Comparable<Node> ,Serializable {
        private Node left;
        private Node right;
        private char ch;
        private int frequency;

        Node(char ch, int frequency, Node left, Node right) {
            this.ch = ch;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }

        private void setLeft(Node left) {
            this.left = left;
        }

        private void setRight(Node right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.frequency - o.frequency;
        }

        int getFrequency() {
            return frequency;
        }
    }

//    private static final int R = 256;
    private Node root;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();
        for (Character c : frequencyTable.keySet()) {
                pq.insert(new Node(c, frequencyTable.get(c), null, null));

        }

        while (pq.size() > 1) {
            Node a = pq.delMin();
            Node b = pq.delMin();
            Node parent = new Node((char)0, a.getFrequency() + b.getFrequency(), a, b);
            pq.insert(parent);
        }

        root = pq.delMin();
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        Node temp = root;
        String str = querySequence.toString();
        int index = 0;
        while (temp != null && !temp.isLeaf() && index < str.length()) {
            if (str.charAt(index) == '0') {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
            index++;
        }

        return new Match(querySequence.firstNBits(index), temp.ch);

    }

    private void buildLookupTableHelper(Map<Character, BitSequence> mapper, Node node, String str) {
        if (node.isLeaf()) {
            mapper.put(node.ch, new BitSequence(str));
            return;
        }

        buildLookupTableHelper(mapper, node.left, str + "0");
        buildLookupTableHelper(mapper, node.right, str + "1");
    }
    public Map<Character, BitSequence> buildLookupTable() {
        Node temp = root;
        Map<Character, BitSequence> mapper = new HashMap<>();
        buildLookupTableHelper(mapper, temp, "");
        return mapper;
    }
}