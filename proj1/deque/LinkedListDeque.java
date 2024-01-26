// declare it in the `deque` package.
package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private static class Node<T>{
        private T data;
        private Node next;
        private Node prev;

        Node(T data, Node next, Node prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        Node tmp = new Node<>(item, sentinel.next, sentinel);
        sentinel.next = tmp;
        tmp.next.prev = tmp;
        size += 1;
    }

    public void addLast(T item) {
        Node tmp = new Node<>(item, sentinel, sentinel.prev);
        sentinel.prev.next = tmp;
        sentinel.prev = tmp;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

    public void printDeque(){
        if(sentinel.next == sentinel){
            return;
        }
        Node p = sentinel.next;
        String printMessage = "";
        do{
            printMessage += p.data.toString() + ",";
            p = p.next;
        }while(p == sentinel);
        System.out.println(printMessage);
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T returnValue = (T)sentinel.next.data;
        Node p = sentinel.next;
        p.next.prev = sentinel;
        sentinel.next = p.next;
        size -= 1;
        return returnValue;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T returnValue = (T)sentinel.prev.data;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return returnValue;
    }

    public T get(int index) {
        if(isEmpty()){
            return null;
        }
        Node p = sentinel.next;
        for(int i = 0; i < index; i++){
            if(p == sentinel){
                return null;
            }
            p = p.next;
        }
        return (T)p.data;
    }

    public T getRecursive(int index){
        return recursiveHelper(index, sentinel.next);
    }

    private T recursiveHelper(int index, Node p){
        if(p == sentinel){
            return null;
        }
        if(index == 0){
            return (T)p.data;
        }
        return recursiveHelper(index - 1, p.next);
    }

    private class LinkIterator implements Iterator<T>{
        Node p;

        public LinkIterator(Node node){
            p = node;
        }

        @Override
        public boolean hasNext(){
            return p != sentinel;
        }

        @Override
        public T next(){
            if(!hasNext()){
                return null;
            }
            T data = (T)p.data;
            p = p.next;
            return data;
        }
    }

    @Override
    public Iterator<T> iterator(){
        LinkIterator iter = new LinkIterator(sentinel.next);
        return (Iterator<T>) iter;
    }

    private boolean contains(T item){
        Node p = sentinel;
        do{
            if(p.data == item){
                return true;
            }
            p = p.next;
        }while(p != sentinel);
        return false;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if(other.size() != size){
            return false;
        }
        for(T item : this){
            if(!other.contains(item)){
                return false;
            }
        }
        return true;
    }


}