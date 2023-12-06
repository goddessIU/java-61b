package lab11.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        marked[v] = true;
        announce();

        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            int node = queue.remove();
            for (int w : maze.adj(node)) {
                if (!marked[w]) {
                    edgeTo[w] = node;
                    marked[w] = true;
                    announce();
                    distTo[w] = distTo[node] + 1;
                    queue.add(w);
                    if (w == t) {
                        targetFound = true;
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        // bfs();
        bfs(s);
    }
}

