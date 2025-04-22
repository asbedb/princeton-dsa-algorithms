import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trials;
    private final double[] thresholds;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        //initialise all sites to be blocked
        //open site
        //repeat until system percolates
        // fraction of sites that are open when system percolates provides estimate of perc threshold
        if(n<= 0 || trials <= 0) throw new IllegalArgumentException("N and Trials must be greater then 0");
        this.trials = trials;
        this.thresholds = new double[trials];
        for(int t = 0; t < trials; t++){
            Percolation testSite = new Percolation(n);
            int openSites = 0;
            while(!testSite.percolates()){
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if(!testSite.isOpen(row, col)){
                    testSite.open(row,col);
                    openSites++;
                }
            }
            thresholds[t] = (double) openSites / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - (1.96 * stddev()/Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + (1.96 * stddev()/Math.sqrt(trials));
    }

    //test client
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java PercolationStats <grid size> <number of trials>");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
