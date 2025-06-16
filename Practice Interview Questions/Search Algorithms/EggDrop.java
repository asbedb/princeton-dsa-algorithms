public class EggDrop {
/*Question 3
Egg drop. Suppose that you have an nnn-story building (with floors 1 through nnn) and plenty of eggs. 
An egg breaks if it is dropped from floor TTT or higher and does not break otherwise. Your goal is to 
devise a strategy to determine the value of  TTT given the following limitations on the number of eggs and tosses:
 */

  //v0: 1 egg <= T tosses (so max height but only one egg)
  public static int versionZero(int egg, int n, int t){
    for(int i = 0; i < n; i++){
      if((i + 1) == t) return (i + 1);
    }
    return -1;
  }

  //v1: ~lgn eggs and ~1lgn tosses
  public static int versionOne(int egg, int n, int t){
    int lo = 1;
    int hi = n;
    while(lo <= hi){
      if (egg == 0){
        System.out.println("Ran out of eggs");
        return -1;
      }
      int mid = lo + (hi - lo) / 2;
      if( t < mid) hi = mid -1;
      else if (t > mid) lo = mid + 1;
      else return mid;
      egg -= 1;
    }
    if (egg < 0) {
      System.out.println("Out of eggs!");
      return -1;
    }
    return -1;
  }

  //v2: ~lgT eggs and ~2lgT tosses
  public static int versionTwo(int egg, int n, int t){
    int lo = 1;
    int hi = n;
    int jump = 1;
    while (jump <= n){
      if (egg == 0 && jump <= n){
        System.out.println("Ran out of eggs during exponential jumps");
        return -1;
      }
      if(jump >= t){ 
        hi = jump;
        break;
      }
      lo = jump;
      jump *= 2;
      egg -= 1;
    }
    while (lo <= hi){
      int mid = lo + (hi - lo) /2;
      if (t < mid) hi = mid -1;
      else if (t > mid) lo = mid + 1;
      else return mid;
      egg -=1; 
    }
    if (egg < 0) {
      System.out.println("Out of eggs!");
      return -1;
    }
    return -1;
  }

  //v3: 2 eggs and ~2SQRTN tosses
  public static int versionThree(int egg, int n, int t){
    int lo = 1;
    int hi = n;
    int jumpModifier = 1;
    int jumpFloor = (int) (jumpModifier * Math.sqrt(n));
    while (jumpFloor <= n){
      if (egg == 0 && jumpFloor <= n){
        System.out.println("Ran out of eggs during sqrt jumps");
        return -1;
      }
      if(jumpFloor >= t){ 
        hi = jumpFloor;
        break;
      }
      jumpModifier += 1;
      lo = jumpFloor;
      jumpFloor = (int) (jumpModifier * Math.sqrt(n));
      egg -= 1;
    }
    for(int i = lo; i < hi; i++){
      if((i + 1) == t) {
        egg -= 1;
        return (i + 1);
      }
    }
    if (egg < 0) {
      System.out.println("Out of eggs!");
      return -1;
    }
    return -1;
  }

  //v4: 2 Eggs and cSQRTN where C is a fixed constant
  public static int versionFour(int egg, int n, int t){
    int lo = 1;
    int hi = n;
    int jumpModifier = 1;
    int jumpFloor = (int) (jumpModifier * Math.sqrt(n));
    while (jumpFloor <= n){
      if (egg == 0 && jumpFloor <= n){
        System.out.println("Ran out of eggs during sqrt jumps");
        return -1;
      }
      if(jumpFloor >= t){ 
        hi = jumpFloor;
        break;
      }
      jumpModifier += 1;
      lo = jumpFloor;
      jumpFloor = (int) (jumpModifier * Math.sqrt(n));
      egg -= 1;
    }
    for(int i = lo; i < hi; i++){
      if((i + 1) == t) {
        egg -= 1;
        return (i + 1);
      }
    }
    if (egg < 0) {
      System.out.println("Out of eggs!");
      return -1;
    }
    return -1;
  }
  public static void main(String[] args) {
    System.out.println("--- Testing versionZero (1 egg) ---");
    testVersionZero(1, 10, 5);  // T in the middle
    testVersionZero(1, 10, 1);  // T at the bottom
    testVersionZero(1, 10, 10); // T at the top
    testVersionZero(1, 5, 3);   // Another case

    System.out.println("\n--- Testing versionOne (~lgn eggs, ~lgn tosses) ---");
    testVersionOne(4, 100, 50); // Enough eggs
    testVersionOne(7, 100, 10);
    testVersionOne(7, 100, 95);
    testVersionOne(3, 100, 50); // Fewer eggs than ideal, might fail

    System.out.println("\n--- Testing versionTwo (~lgT eggs, ~2lgT tosses) ---");
    testVersionTwo(10, 100, 5);
    testVersionTwo(10, 100, 50);
    testVersionTwo(10, 100, 90);
    testVersionTwo(5, 100, 90); 

    System.out.println("\n--- Testing versionThree (~lgT eggs, ~2lgT tosses) ---");
    testVersionThree(2, 100, 5);
    testVersionThree(2, 100, 50);
    testVersionThree(2, 100, 90);
    testVersionThree(2, 100, 90); 
}

  public static void testVersionZero(int eggs, int n, int t) {
    int result = EggDrop.versionZero(eggs, n, t);
    System.out.println("versionZero(eggs=" + eggs + ", n=" + n + ", t=" + t + ") = " + result +
    (result == t ? " (Passed)" : " (Failed, expected " + t + ")"));
  }

  public static void testVersionOne(int eggs, int n, int t) {
    int result = EggDrop.versionOne(eggs, n, t);
    System.out.println("versionOne(eggs=" + eggs + ", n=" + n + ", t=" + t + ") = " + result +
    (result == t ? " (Passed)" : " (Failed, expected " + t + ")"));
  }

  public static void testVersionTwo(int eggs, int n, int t) {
    int result = EggDrop.versionTwo(eggs, n, t);
    System.out.println("versionTwo(eggs=" + eggs + ", n=" + n + ", t=" + t + ") = " + result +
    (result == t ? " (Passed)" : " (Failed, expected " + t + ")"));
  }

  public static void testVersionThree(int eggs, int n, int t) {
    int result = EggDrop.versionThree(eggs, n, t);
    System.out.println("versionThree(eggs=" + eggs + ", n=" + n + ", t=" + t + ") = " + result +
    (result == t ? " (Passed)" : " (Failed, expected " + t + ")"));
  }
}
