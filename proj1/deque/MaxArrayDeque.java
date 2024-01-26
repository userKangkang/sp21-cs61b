package deque;

import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }

    public T max(){
        if(isEmpty()){
            return null;
        }
        T maxValue = items[head];
        for(T item : this){
            if(comparator.compare(item, maxValue) > 0){
                maxValue = item;
            }
        }
        return maxValue;
    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T maxValue = items[head];
        for(T item : items){
            if(c.compare(item, maxValue) > 0){
                maxValue = item;
            }
        }
        return maxValue;
    }
}
