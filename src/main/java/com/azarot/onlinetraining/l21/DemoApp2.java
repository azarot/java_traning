package com.azarot.onlinetraining.l21;

public class DemoApp2 {
    public static void main(String[] args) {
        var tail = createLinkedList(4, 3, 9, 1);
        printReversed(tail); // should print "1 <-> 9 <-> 3 <-> 4"
    }

    public static <T> Node<T> createLinkedList(T... elements) {
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException();
        }
        var tmp = new Node<>(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            var newNode = new Node<>(elements[i]);
            newNode.prev = tmp;
            tmp.next = newNode;
            tmp = newNode;
        }
        return tmp;
    }

    public static <T> void printReversed(Node<T> tail) {
        var tmp = tail;
        while (tmp != null) {
            System.out.print(tmp.element);
            if (tmp.prev != null) {
                System.out.print(" <-> ");
            }
            tmp = tmp.prev;
        }
    }

    static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        public Node(T element) {
            this.element = element;
        }
    }
}