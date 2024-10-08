package rammanagementsystem.services;

import rammanagementsystem.exceptions.InvalidItemException;
import rammanagementsystem.exceptions.DuplicateItemException;

import rammanagementsystem.interfaces.Queriable;

import java.util.Collection;
import java.util.HashMap;

public abstract class DataService<T extends Queriable> extends HashMap<String, T> {

    DataService() {
    }

    DataService(Collection<T> data) throws InvalidItemException {
        for (T item : data) {
            addItem(item);
        }

    }

    public void addItem(T item) throws InvalidItemException {
        if (this.get(item.getId()) != null)
            throw new DuplicateItemException();
        this.put(item.getId(), item);
    }

    public void addAll(Collection<T> items) throws InvalidItemException {
        for (T item : items) {
            addItem(item);
        }
    }

    public HashMap<String, T> queryMap(String str) {
        HashMap<String, T> workingMap = new HashMap<>();
        this.forEach((key, value) -> {
            if (value.getQueryString().toLowerCase().contains(str.toLowerCase()) && value.isActive())
                workingMap.put(key, value);
        });
        return workingMap;
    }

    public boolean selfTest() {
        boolean result = true;
        for (Entry<String, T> entry : this.entrySet()) {
            if (entry.getKey() != entry.getValue().getId()) {
                result = false;
                break;
            }
        }
        return result;
    }

}
