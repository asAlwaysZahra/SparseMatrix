package org.example.model;

import org.example.exception.NodeDoesNotExistException;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // methods -----------------------------------------------------------------

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int first() {
        if (isEmpty()) throw new IllegalStateException("model.LinkedList is empty");
        return this.head.getValue();
    }

    public int last() {
        if (isEmpty()) throw new IllegalStateException("model.LinkedList is empty");
        return this.tail.getValue();
    }

    public void addNode(int index, int value) {
        Node newNode = new Node(value, index, null);
        if (isEmpty()) head = newNode;
        else tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    public boolean removeNode(int index) {

        if (head.index == index) {
            removeFirst();
            return true;
        }

        Node prev = head;
        Node node = head.next;
        while (node != null) {

            if (node.index == index) {
                prev.next = node.next;
                return true;
            }

            prev = node;
            node = node.next;
        }

        return false;
    }

    public int removeFirst() {
        if (isEmpty()) throw new IllegalStateException("model.LinkedList is empty");
        int first = head.getValue();
        head = head.getNext();
        size--;
        return first;
    }

    public boolean search(int value) {
        Node n = head;
        while (n != null) {
            if (n.value == value)
                return true;
            n = n.next;
        }

        return false;
    }

    public boolean searchByIndex(int index) {
        Node n = head;

        while (n != null) {
            if (n.index == index) return true;
            n = n.next;
        }

        return false;
    }

    public void updateNode(int index, int value) {
        if (getNodeByIndex(index) == null)
            throw new NodeDoesNotExistException();
        getNodeByIndex(index).setValue(value);
    }

    public void printList(int row) {
        Node n = head;
        while (n != null) {
            // print in format: row col val
            System.out.printf(row + " " + n.index + " " + n.value + "\n");
            n = n.next;
        }
    }

    public int getNodeValue(int index) {
        if (getNodeByIndex(index) == null)
            throw new NodeDoesNotExistException();
        return getNodeByIndex(index).value;
    }

    public Node getNodeByIndex(int index) {
        Node node = head;

        while (node != null) {
            if (node.index == index) return node;
            node = node.next;
        }

        return null;
    }

    static class Node {
        private int index;
        private int value;
        private Node next;

        public Node(int value, int index, Node next) {
            this.value = value;
            this.index = index;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }

        // getter / setter -----------------------------------------------------------------

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}