package hw4.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.HashMap;
import java.util.Map;

public class Board implements WorldState {
    private int[][] tiles;
//    private int
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0; i <tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
    public int tileAt(int i, int j) {
        if (i < 0 || i >= tiles.length || j < 0 || j >= tiles.length) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    public int size() {
        return tiles.length;
    }
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int BLANK = 0;
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming() {
        int num = 1;
        int total = 0;
        for (int i = 0; i < tiles.length; i++) {
            int cols = i == tiles.length - 1 ? tiles.length - 1 : tiles.length;
            for (int j = 0; j < cols; j++) {
                if (tiles[i][j] == num++) {
                    continue;
                }
                total++;
            }
        }
        return total;
    }
    private int getDiff(int num, int row, int col) {
        int t = num - 1;
        int cCol = t % tiles.length;
        int cRow = t / tiles.length;
        int h = Math.abs(cRow - row) + Math.abs(cCol - col);
//        System.out.println(h);
        return h;
    }
    public int manhattan() {
        int total = 0;
        int t = 1;
        for (int i = 0; i < tiles.length; i++) {
            int cols = tiles.length;
            for (int j = 0; j < cols; j++) {
                if (tiles[i][j] == 0) {
                    t++;
                    continue;
                }
                if (tiles[i][j] == t) {
                    t++;
                    continue;
                }
                t++;
                total += getDiff(tiles[i][j], i, j);
            }
        }
//        System.out.println(total);
        return total;
    }
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board word1 = (Board) y;
        if (tiles == null && word1.tiles == null) {
            return true;
        } else if (tiles == null || word1.tiles == null) {
            return false;
        } else {
            if (word1.tiles.length != tiles.length) {
                return false;
            }

            for (int i = 0; i <tiles.length; i++) {
                for (int j = 0; j <tiles.length; j++) {
                    if (tiles[i][j] != word1.tiles[i][j]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
