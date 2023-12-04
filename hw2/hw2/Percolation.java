package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] sz;
    private int[][] arr;
    private int startSz;
    private int endSz;
    private int[] startPos;
    private int[] endPos;
    private int N;
    private int openNum;
    private int startNum;
    private int startPar;
    private int endPar;
    private int endNum;
    private int[][] Directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N)     {
        this.N = N;
        sz = new int[N][N];
        arr = new int[N][N];

        startSz = 0;
        endSz = 0;
        startNum = -2;
        endNum = -3;
        startPar = startNum;
        endPar = endNum;
        openNum = 0;
        startPos = new int[]{-1, -1};
        endPos = new int[]{N, N};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sz[i][j] = 0;
                arr[i][j] = -1;
            }
        }
    }

    private int[] getPos(int num) {
        if (num == startNum) {
            return startPos;
        }

        if (num == endNum) {
            return endPos;
        }

        num -= 1;
        int col = num % N;
        int row = (num - col) / N;
        int[] ret = {row, col};
        return ret;
    }

    private int root(int num) {
        int[] pos = getPos(num);
        if (pos.equals(startPos)) {
            if (startPar == startNum) {
                return startPar;
            } else {
                return root(startPar);
            }
        } else if (pos.equals(endPos)) {
            if (endPar == endNum) {
                return endPar;
            } else {
                return root(endPar);
            }
        }

        int row = pos[0];
        int col = pos[1];
        int tRoot = arr[row][col];
        if (tRoot == num) {
            return num;
        }
        return root(tRoot);
    }

    private boolean isConnected(int row, int col, int nRow, int nCol) {
        int num1 = getNum(row, col);
        int num2 = getNum(nRow, nCol);
        int root1, root2;

        if (num1 == startNum) {
            root1 = root(startPar);
        } else {
            root1 = root(num1);
        }

        if (num2 == endNum) {
            root2 = root(endPar);
        } else {
            root2 = root(num2);
        }
        return root1 == root2;
    }

    //
    private void connect(int row, int col, int nRow, int nCol) {

        int num1 = getNum(row, col);
        int num2 = getNum(nRow, nCol);
        int root1 = root(num1);
        int root2 = root(num2);
        int[] pos1 = getPos(root1);
        int[] pos2 = getPos(root2);

        if (root1 == startNum) {
            System.out.println("startNum is " + startNum + " sz:  "  + startSz);
            if (startSz < sz[pos2[0]][pos2[1]]) {
                sz[pos2[0]][pos2[1]] += startSz;
                startPar = getNum(pos2[0], pos2[1]);
            } else {
                startSz += sz[pos2[0]][pos2[1]];
                arr[pos2[0]][pos2[1]] = startNum;
            }
        } else if (root2 == endNum) {
            if (endSz < sz[pos1[0]][pos1[1]]) {
                sz[pos1[0]][pos1[1]] += endSz;
                endPar = getNum(pos1[0], pos1[1]);
            } else {
                endSz += sz[pos1[0]][pos1[1]];
                arr[pos1[0]][pos1[1]] = endNum;
            }
        } else {
            if (sz[pos1[0]][pos1[1]] < sz[pos2[0]][pos2[1]]) {
                sz[pos2[0]][pos2[1]] += sz[pos1[0]][pos1[1]];
                arr[pos1[0]][pos1[1]] = arr[pos2[0]][pos2[1]];
            } else {
                sz[pos1[0]][pos1[1]] += sz[pos2[0]][pos2[1]];
                arr[pos2[0]][pos2[1]] = arr[pos1[0]][pos1[1]];
            }
        }
        boolean res = isConnected(row, col, nRow, nCol);
        if (res) {
            System.out.println("co");
        } else
        System.out.println("dd");
    }

    private int getNum(int row, int col) {
        if (row == startPos[0] && col == startPos[1]) {
            return startNum;
        }

        if (row == endPos[0] && col == endPos[1]) {
            return endNum;
        }

        return row * N + col + 1;
    }

    private boolean isValidPos(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return false;
        }
        return true;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col)   {
        if (!isValidPos(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        System.out.println("open: " + row + " " + col);

        openNum++;
        int pos = getNum(row, col);
        arr[row][col] = pos;
        sz[row][col] = 1;
        for (int i = 0; i < Directions.length; i++) {
            int nRow = row + Directions[i][0];
            int nCol = col + Directions[i][1];
            if (isValidPos(nRow, nCol) && isOpen(nRow, nCol)) {
                connect(row, col, nRow, nCol);
            }
        }

        if (row == 0) {
            connect(startPos[0], startPos[1], row, col);
        } else if (row == N - 1) {
            connect(row, col, endPos[0], endPos[1]);
        }

    }



    // is the site (row, col) open?
    public boolean isOpen(int row, int col)  {
        if (!isValidPos(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (arr[row][col] >= 0) {
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)  {
        if (!isValidPos(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        if (!isOpen(row, col)) {
            return false;
        }

        return isConnected(startPos[0], startPos[1], row, col);
    }

    // number of open sites
    public int numberOfOpenSites()   {
        return openNum;
    }

    // does the system percolate?
    public boolean percolates()   {
        return isConnected(startPos[0], startPos[1], endPos[0], endPos[1]);
    }

    // use for unit testing (not required)
    public static void main(String[] args)  {}
}
