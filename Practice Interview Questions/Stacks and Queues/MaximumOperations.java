public class MaximumOperations {

    /*Stack with max. Create a data structure
    that efficiently supports the stack operations
    (push and pop) and also a return-the-maximum
    operation. Assume the elements are real numbers
    so that you can compare them.
    */

    private Node first = null;

    //Inner Class
    private static class Node{
        Integer item;
        Node next;
        Integer currentMax;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void push(Integer item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst == null){
            first.currentMax = item;
        } else {
            first.currentMax = Math.max(item, oldFirst.currentMax);
        }
    }

    public Integer getMax(){
        if(isEmpty()){
            return null;
        } else {
          return first.currentMax;
        }
    }

    public Integer pop(){
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }
        Integer item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        //tests
    }
}
