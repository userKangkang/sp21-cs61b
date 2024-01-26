package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    // legal syntax of creating modeled array.
    protected T[] items;
    protected int size;
    protected int head;
    protected int rear;
    protected int capacity;

    public ArrayDeque(){
        items = (T[]) (new Object[10]);
        size = 0;
        head = 0;
        rear = 0;
        capacity = 10;
    }
    @Override
    public void addFirst(T item){
        if(isFull()){
            resize(capacity * 2);
        }
        head = (head + 1) % capacity;
        items[head] = item;
        size++;
    }
    @Override
    public void addLast(T item){
        if(isFull()){
            resize(capacity * 2);
        }
        items[rear] = item;
        rear = (rear - 1 + capacity) % capacity;
        size++;
    }

    private void resize(int newSize){
        T[] tmp = (T[]) new Object[newSize];
        int i;
        for(i = 0; i < size; i++){
            tmp[i] = items[(rear + 1 + i)%capacity];
        }
        capacity = newSize;
        head = i - 1;
        rear = newSize - 1;
        items = tmp;
    }


    private boolean isFull(){
        return (rear - head + capacity) % capacity == 1;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        StringBuilder returnSB = new StringBuilder("");
        int index = head;
        for(; index != rear; index = (index - 1 + capacity) % capacity){
            returnSB.append(items[index].toString());
            returnSB.append(",");
        }
        System.out.println(returnSB.toString());
    }
    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        if(size * 4 < capacity && capacity >= 16){
            resize(size * 2);
        }
        T returnValue = items[head];
        head = (head - 1 + capacity) % capacity;
        size--;
        return returnValue;
    }
    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        if(size * 4 < capacity && capacity >= 16){
            resize(size * 2);
        }
        T returnValue = items[(rear + 1) % capacity];
        rear = (rear + 1) % capacity;
        size--;
        return returnValue;
    }
    @Override
    public T get(int index){
        if(index >= capacity && index < 0){
            return null;
        }
        return items[(head - index + capacity) % capacity];
    }

    private class ArrayIterator implements Iterator<T>{
        int index;

        public ArrayIterator(){
            index = head;
        }

        @Override
        public boolean hasNext(){
            return index != rear;
        }

        @Override
        public T next(){
            if(!hasNext()){
                return null;
            }
            T data = get(head - index);
            index = (index - 1 + capacity) % capacity;
            return data;
        }
    }

    @Override
    public Iterator<T> iterator(){
        ArrayIterator iter = new ArrayIterator();
        return (Iterator<T>) iter;
    }

    public boolean contains(T data){
        for(int index = head; index != rear; index = (index - 1 + capacity) % capacity){
            if(data.equals(items[index])){
                return true;
            }
        }
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
        if(o.getClass() != this.getClass() && !o.getClass().equals(LinkedListDeque.class)){
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if(other.size() != size){
            return false;
        }
        for(T item : this){
            if(other.getClass().equals(LinkedListDeque.class)){
                if(!((LinkedListDeque<T>)(other)).contains(item)){
                    return false;
                }
            }
            if(other.getClass().equals(ArrayDeque.class)){
                if(!((ArrayDeque<T>)(other)).contains(item)){
                    return false;
                }
            }
        }
        return true;
    }
    public T getLast(){
        return get(size - 1);
    }
}
