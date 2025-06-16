
/*Search in a bitonic array. 
An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. 
Write a program that, given a bitonic array of nnn distinct integer values, determines whether a given integer is in the array.
Standard version: Use ∼3lg⁡n∼3lgn\sim, 3, \lg, n compares in the worst case.
Signing bonus: Use ∼2lg⁡n∼2lgn\sim, 2, \lg, n compares in the worst case (and prove that no algorithm can guarantee to perform fewer than ∼2lg⁡n∼2lgn\sim, 2, \lg, n compares in the worst case). */

import java.util.Arrays;

public class Bitonic {
  //example of bitonic array [1, 4, 6, 8, 3, 2]

  public static int binarySearchFunction(int[] a, int low, int high, int target, boolean isIncreasing){
    while(low <=high){
      int mid = low + (high -low) /2;
      if (target == a[mid]) return mid + 1;
      if(!isIncreasing){
        if (target > a[mid]) high = mid  - 1;
        else if (target < a[mid]) low = mid + 1;
      }else{
        if (target < a[mid]) high = mid - 1;
        else if (target > a[mid]) low = mid + 1;
      }
    }
    return 0;
  }

  // Helper to find peak in a bitonic
  public static int findPeak(int[] a) {
    int low = 0;
    int hi = a.length - 1;
    while (low <= hi) {
        int mid = low + (hi - low) / 2;
        // Check if mid is the peak
        if (
          (
            mid > 0 
            && mid < a.length - 1 
            && a[mid] > a[mid - 1] 
            && a[mid] > a[mid + 1]
          ) 
          || (a.length == 1 && mid == 0)
        ) 
        {
          return mid;
        }
        else if (
          mid < a.length - 1 
          && a[mid] < a[mid + 1]
        ) 
        {
            low = mid + 1;
        }
        else {
            hi = mid - 1;
        }
    }
    //ERROR
    return -1; 
}

  // new helper to find peak (refactor)
  public static int bitonicSearch(int[] a, int target){
    //determine increasing or decreasing BST and take the peak into account returning a helper of the relevant BST
    int peakIndex = findPeak(a);
    int increasingResult = binarySearchFunction(a, 0, peakIndex, target, true);
    if(increasingResult > 0 || (increasingResult == 0 && target == a[0])) return increasingResult;
    int decreasingResult = binarySearchFunction(a, peakIndex + 1, a.length-1, target, false);
    return decreasingResult;
  }

  public static void main(String[] args) {
      // Tests for typical bitonic arrays
      int[] bitonicArray1 = {1, 4, 6, 8, 3, 2};
      int target1 = 6;
      int index1 = bitonicSearch(bitonicArray1, target1);
      System.out.println("Target " + target1 + " found at index: " + index1 + " in " + Arrays.toString(bitonicArray1)); // Expected output: Target 6 found at index: 2 in [1, 4, 6, 8, 3, 2]

      int[] bitonicArray2 = {2, 3, 7, 9, 5, 1};
      int target2 = 1;
      int index2 = bitonicSearch(bitonicArray2, target2);
      System.out.println("Target " + target2 + " found at index: " + index2 + " in " + Arrays.toString(bitonicArray2)); // Expected output: Target 1 found at index: 5 in [2, 3, 7, 9, 5, 1]

      int[] bitonicArray3 = {7, 8, 9, 3, 2, 1};
      int target3 = 8;
      int index3 = bitonicSearch(bitonicArray3, target3);
      System.out.println("Target " + target3 + " found at index: " + index3 + " in " + Arrays.toString(bitonicArray3)); // Expected output: Target 8 found at index: 1 in [7, 8, 9, 3, 2, 1]

      int[] bitonicArray4 = {10, 20, 30, 40, 50}; // Strictly increasing (also bitonic)
      int target4 = 30;
      int index4 = bitonicSearch(bitonicArray4, target4);
      System.out.println("Target " + target4 + " found at index: " + index4 + " in " + Arrays.toString(bitonicArray4)); // Expected output: Target 30 found at index: 2 in [10, 20, 30, 40, 50]

      int[] bitonicArray5 = {50, 40, 30, 20, 10}; // Strictly decreasing (also bitonic)
      int target5 = 40;
      int index5 = bitonicSearch(bitonicArray5, target5);
      System.out.println("Target " + target5 + " found at index: " + index5 + " in " + Arrays.toString(bitonicArray5)); // Expected output: Target 40 found at index: 1 in [50, 40, 30, 20, 10]

      // Test case where target is the peak
      int[] bitonicArray6 = {1, 3, 5, 10, 8, 6};
      int target6 = 10;
      int index6 = bitonicSearch(bitonicArray6, target6);
      System.out.println("Target " + target6 + " found at index: " + index6 + " in " + Arrays.toString(bitonicArray6)); // Expected output: Target 10 found at index: 3 in [1, 3, 5, 10, 8, 6]

      // Test case where target is not present
      int[] bitonicArray7 = {1, 5, 10, 6, 2};
      int target7 = 7;
      int index7 = bitonicSearch(bitonicArray7, target7);
      System.out.println("Target " + target7 + " found at index: " + index7 + " in " + Arrays.toString(bitonicArray7)); // Expected output: Target 7 found at index: -1 in [1, 5, 10, 6, 2]

      // Test with a single element array
      int[] singleElementArray = {5};
      int target8 = 5;
      int index8 = bitonicSearch(singleElementArray, target8);
      System.out.println("Target " + target8 + " found at index: " + index8 + " in " + Arrays.toString(singleElementArray)); // Expected output: Target 5 found at index: 0 in [5]

      int target9 = 10;
      int index9 = bitonicSearch(singleElementArray, target9);
      System.out.println("Target " + target9 + " found at index: " + index9 + " in " + Arrays.toString(singleElementArray)); // Expected output: Target 10 found at index: -1 in [5]

      // Test with an empty array
      int[] emptyArray = {};
      int target10 = 5;
      int index10 = bitonicSearch(emptyArray, target10);
      System.out.println("Target " + target10 + " found at index: " + index10 + " in " + Arrays.toString(emptyArray)); // Expected output: Target 5 found at index: -1 in []
  }
}
