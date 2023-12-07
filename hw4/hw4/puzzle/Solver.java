package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private int finalMoves;
    private Node finalNode;
    private class Node {
        private WorldState state;
        private int moves;
        private Node previous;
        Node(WorldState state, int moves, Node previous) {
            this.state = state;
            this.moves = moves;
            this.previous = previous;
        }
    }
    public Solver(WorldState initial) {
        MinPQ<Solver.Node> pq = new MinPQ<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.moves + o1.state.estimatedDistanceToGoal()
                        - (o2.moves + o2.state.estimatedDistanceToGoal());
            }
        });

        pq.insert(new Node(initial, 0, null));
        while (!pq.isEmpty()) {
            Node node = pq.delMin();
            if (node.state.isGoal()) {
                finalMoves = node.moves;
                finalNode = node;
                return;
            }

            for (WorldState temp : node.state.neighbors()) {
                if (isRedundent(node,temp)) {
                    continue;
                }
                pq.insert(new Node(temp, node.moves + 1, node));
            }
        }
    }

    public boolean isRedundent(Node node, WorldState ws) {
        Node t = node;
        while (t != null) {
            if (t.state.equals(ws)) {
                return true;
            }
            t = t.previous;
        }
        return false;
    }

    public int moves() {
        return finalMoves;
    }
    public Iterable<WorldState> solution() {
        ArrayList<WorldState> itr = new ArrayList<>();
        Node node = finalNode;
        while (node != null) {
            itr.add(node.state);
            node = node.previous;
        }
        ArrayList<WorldState> ret = new ArrayList<>();
        for (int i = itr.size() - 1; i >= 0; i--) {
            ret.add(itr.get(i));
        }
        return ret;
    }
}
