import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private final boolean[] grid;
    private final int n;
    private final int virtualTop;
    private final int virtualBottom;
    private int openCount;
    private final WeightedQuickUnionUF full;
    private final WeightedQuickUnionUF perc;

    public Percolation(int n){
        if(n <= 0) throw new IllegalArgumentException("Invalid Grid Size");
        this.n = n;
        // reserve 0 and n*n + 1 for virtual bottom and top nodes.
        this.virtualTop = 0;
        this.virtualBottom = (n*n) + 1;
        this.grid = new boolean[(n*n) + 1];
        //avoid the bottom virtual node for full (not required)
        this.full = new WeightedQuickUnionUF((n*n) + 1);
        this.perc = new WeightedQuickUnionUF((n*n) + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        validate(row, col);
        if(!isOpen(row, col)){
            openCount++;
            int currentIndex = ((row - 1) * n) + col;
            grid[currentIndex] = true;
            // Virtual Top Connector
            if (row == 1){
                full.union(currentIndex, virtualTop);
                perc.union(currentIndex, virtualTop);
            }
            //Virtual Bottom Connector
            if (row == n){
                perc.union(currentIndex, virtualBottom);
            }

            //Adjacent Logic (Update our Unions based on adjacent open)
            //Check Left
            if(col > 1 && isOpen(row, col -1)){
                int leftIndex = ((row - 1) * n) + (col - 1);
                full.union(currentIndex, leftIndex);
                perc.union(currentIndex, leftIndex);
            }
            //Check Right
            if(col < n  && isOpen(row, col + 1)){
                int rightIndex = ((row - 1) * n) + (col + 1);
                full.union(currentIndex, rightIndex);
                perc.union(currentIndex, rightIndex);
            }
            //Check Up
            if( row > 1 && isOpen(row - 1, col)){
                int topIndex = ((row - 2) * n) + col;
                full.union(currentIndex, topIndex);
                perc.union(currentIndex, topIndex);
            }
            //Check Down
            if(row < n && isOpen(row + 1, col)){
                int bottomIndex = ((row) * n) + col;
                full.union(currentIndex, bottomIndex);
                perc.union(currentIndex, bottomIndex);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validate(row, col);
        return grid[((row - 1) * n ) + col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validate(row, col);
        if (!isOpen(row, col)) return false;
        int currentRoot = full.find(((row - 1) * n) + col);
        int topRoot = full.find(virtualTop);
        return (currentRoot == topRoot);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openCount;
    }
    // does the system percolate?
    public boolean percolates(){
        int topRoot = perc.find(virtualTop);
        int bottomRoot = perc.find(virtualBottom);
        return topRoot == bottomRoot;
    }

    private void validate(int row, int col){
        if(row < 1 || row > n || col < 1 || col > n){
            throw new IllegalArgumentException("row or col out of bounds");
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perky = new Percolation(6);
        StdOut.println(perky.isFull(5,5));
        perky.open(1,6);
        perky.open(2,6);
        perky.open(3,6);
        perky.open(4,6);
        perky.open(5,6);
        perky.open(5,5);
        perky.open(4,4);
        perky.open(2,4);
        perky.open(2,3);
        perky.open(2,2);
        perky.open(2,1);
        perky.open(3,1);
        perky.open(4,1);
        perky.open(5,1);
        perky.open(5,2);
        perky.open(6,2);
        perky.open(5,4);
        StdOut.println(perky.isFull(5,5));
    }
}
