package lowlatency;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> {
    private static class Node<T> {
        final T value;
        Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private final AtomicReference<Node<T>> top = new AtomicReference<>(null);

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        Node<T> oldTop;
        do {
            oldTop = top.get();
            newNode.next = oldTop;
        } while(!top.compareAndSet(oldTop,newNode));
    }

    public T pop() {
        Node<T> newTop;
        Node<T> oldTop;

        do{
            oldTop = top.get();
            if(oldTop == null) return null;
            newTop = oldTop.next;
        } while(!top.compareAndSet(oldTop,newTop));
            return oldTop.value;
    }

    public static void main(String[] args) {
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        stack.push(1);
        stack.push(2);
        System.out.println("Popped : " + stack.pop());
        System.out.println("Popped : " + stack.pop());
    }


}
