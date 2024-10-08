package utils;

import java.util.HashSet;
import java.util.Set;

public class Observable<T> {
    private final Set<Observer<T>> observers = new HashSet<>();

    private T value;

    public Observable() {
    }

    public Observable(T initialValue) {
        this.value = initialValue;
    }

    public void subscribe(Observer<T> observer){
        this.observers.add(observer);
    }

    public void unsubscribe(Observer<T> observable){
        this.observers.remove(observable);
    }

    public void set(T value){
        this.value = value;
        this.observers.forEach(observer -> observer.update(value));
    }

    public T get(){
        return this.value;
    }

}
