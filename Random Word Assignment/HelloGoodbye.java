public class HelloGoodbye {
  public static void main(String[] args) {
      if (args.length == 0 ) {
        System.out.print("Hello Anon");
      }
      else if (args.length == 1) {
        System.out.print(args[0]);
      } 
      else if (args.length > 1 && args.length <= 2) {
        System.out.print("Hello " + args[0] + " and " + args[1] + ".");
      } else {
        System.out.print("I can only greet two people :(");
      }
      if (args.length == 0 ) {
        System.out.print("\n Goodbye Anon");
      }
      else if (args.length == 1) {
        System.out.print(args[0]);
      } 
      else if (args.length > 1 && args.length <= 2) {
        System.out.print("\nGoodbye " + args[1] + " and " + args[0] + ".");
      } else {
        System.out.print("\nSorry!");
      }
    }
}
