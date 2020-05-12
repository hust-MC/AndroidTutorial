package com.emercy.myapplication;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<E> {

    private AtomicReference<Node<E>> reference = new AtomicReference<>();


    public void push(E v) {
        Node<E> oldHead;
        Node<E> newHead = new Node<>(v);

        do {
            oldHead = reference.get();
            newHead.next = oldHead;
        } while (!reference.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;

        do {
            oldHead = reference.get();
            newHead = oldHead.next;
        } while (!reference.compareAndSet(oldHead, newHead));
        return oldHead.value;
    }

    private class Node<E> {
        E value;
        Node<E> next;

        Node(E v) {
            value = v;
        }
    }
}
