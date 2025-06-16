import java.util.Stack;

/*Implement a queue with two stacks so that each queue operations
takes a constant amortized number of stack operations.*/

public class QueueingUsingTwoStacks {
    Stack<Integer> enqueueStack;
    Stack<Integer> dequeueStack;

    public QueueingUsingTwoStacks(){
        enqueueStack = new Stack<>();
        dequeueStack = new Stack<>();
    }

    public void enqueue(Integer number){
        enqueueStack.push(number);
    }

    public Integer dequeue(){
        if(dequeueStack.isEmpty()){
            while(!enqueueStack.isEmpty()){
                dequeueStack.push(enqueueStack.pop());
            }
        }
        if (dequeueStack.isEmpty()){
            System.out.println("Queue is empty! Nothing to Dequeue");
            return null;
        } else {
            return dequeueStack.pop();
        }
    }

    public static void main(String[] args) {
        //Unit Tests
        // Example usage:
        QueueingUsingTwoStacks myQueue = new QueueingUsingTwoStacks();

        System.out.println("Enqueueing 1, 2, 3");
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);

        System.out.println("Dequeued: " + myQueue.dequeue()); // Should be 1
        System.out.println("Dequeued: " + myQueue.dequeue()); // Should be 2

        System.out.println("Enqueueing 4");
        myQueue.enqueue(4);

        System.out.println("Dequeued: " + myQueue.dequeue()); // Should be 3
        System.out.println("Dequeued: " + myQueue.dequeue()); // Should be 4
        System.out.println("Dequeued: " + myQueue.dequeue()); // Should be null/error as empty
    }
}
