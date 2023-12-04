package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;

public class PercolationStats {
    private double mean;
    private double stddev;
    private double confinLow;
    private double confinHigh;
//    private ArrayList<Integer> nums;
    private int[] nums;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw  new IllegalArgumentException();
        }
        nums = new int[T];

//        int num = 0;
        for (int i = 0; i < T; i++) {
            Percolation pc = pf.make(N);
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            while (pc.percolates()) {
                if (!pc.isOpen(row, col)) {
                    pc.open(row, col);
                }
            }
//            num += pc.numberOfOpenSites();
            nums[i] = pc.numberOfOpenSites();
        }
//        mean = num / T;
//
//        double total = 0;
//        for (int m : nums) {
//            total += Math.pow(m - mean, 2);
//        }
//        stddev = Math.sqrt(total / (T - 1));
//
//        double t = 1.96 * stddev / Math.sqrt(T);

        mean = StdStats.mean(nums);
        stddev = StdStats.stddev(nums);
        double t = stddev * 1.96 / Math.sqrt(T);
        confinLow = mean - t;
        confinHigh = mean + t;
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return confinLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confinHigh;
    }
}
