/*3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n2n2n, 
squared in the worst case. You may assume that you can sort the nnn integers in time proportional to n2n2n, 
squared or better. */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ThreeSum {
  public static List<int[]> threeSum (int[] a, int target){
  Arrays.sort(a);
  int n = a.length;
  List<int[]> triplets = new ArrayList<>();
  for(int i = 0; i < n; i++){
    if(i > 0 && a[i] == a[i - 1]) continue;
    int left = i + 1;
    int right = n -1;
    while (left < right) {
      int sum = a[i] + a[left] + a[right];
      if (sum == target) {
        triplets.add(new int[]{a[i], a[left], a[right]});
        while (left < right && a[left] == a[left + 1]) {
            left++;
        }
        while (left < right && a[right] == a[right - 1]) {
            right--;
        }
        left++;
        right--;
      } else if (sum < target) {
          left++;
      } else {
          right--;
      }
    }
  }
  return triplets;
}
public static void main(String[] args) {
        int size = 100; // Size of the array
        int range = 1000; // Range of values (-range to range)
        int target = 4;    // The target sum

        Random random = new Random();
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = random.nextInt(2 * range + 1) - range; // Fill with random numbers
        }
        System.out.println("Array size: " + size);
        System.out.println("Target sum: " + target);
        long startTime = System.nanoTime();
        List<int[]> result = threeSum(largeArray, target);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double durationMillis = duration / 1_000_000.0;
        System.out.println("Number of triplets found: " + result.size());
        System.out.printf("Execution time: %.3f milliseconds\n", durationMillis);

        // Optional: Print a few of the found triplets if the count is not too large
        if (result.size() < 50) {
            System.out.println("Found triplets:");
            for (int[] triplet : result) {
                System.out.println(Arrays.toString(triplet));
            }
        }
    }
  
}
