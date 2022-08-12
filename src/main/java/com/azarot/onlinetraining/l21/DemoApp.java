package com.azarot.onlinetraining.l21;

public class DemoApp {
    public static void main(String[] args) {
        var head = createLinkedList(4, 3, 9, 1);
        printReversedRecursively(head); // should print "1 <- 9 <- 3 <- 4"
    }

    public static <T> Node<T> createLinkedList(T... elements) {
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException();
        }

        var head = new Node<>(elements[0]);

        var tmp = head;
        for (int i = 1; i < elements.length; i++) {
            var newNode = new Node<>(elements[i]);
            tmp.next = newNode;
            tmp = newNode;
        }

        return head;
    }

    public static <T> void printReversedRecursively(Node<T> head) {
        if (head != null) {
            printReversedRecursively(head.next);
            if (head.next != null) {
                System.out.print(" <- ");
            }
            System.out.print(head.element);
        }
    }

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }
}
