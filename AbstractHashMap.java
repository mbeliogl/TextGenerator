package com.maxim;
import java.util.Iterator;

/**
 * Abstract class detailing basic set of operations any concrete implementation of a HashMap will need to provide

 * Abstract methods:
    put
    get
    iterator
    resize

 * Concrete methods:
    size
    isEmpty
    hash
 */


public abstract class AbstractHashMap implements Iterable<Entry> {

    protected double maxLoad;
    protected int numKeys;
    protected int capacity;

    /**
     * Initialize the properties of a HashMap
     *
     * maxLoad: the maximum load value the HashMap can reach before possibly resizing (i.e. increasing the capacity)
     * numKeys: the number of key/value pairs in HashMap
     * capacity: is the upper limit on number of items the HashMap can hold
     */
    public AbstractHashMap(double maxLoad) {
        numKeys = 0;
        capacity = 16;
        this.maxLoad = maxLoad;
    }

    /**
     * Add key/value pair to the HashMap
     * @param key key to be added
     * @param value value for the key
     */
    public abstract void put(Object key, Object value);

    /**
     * Get the value of item
     * @param key
     * @return value associated with key or null if key not in the HashMap
     */
    public abstract Object get(Object key);

    /**
     * Iterate through all key/value pairs in the HashMap
     * Here the key/value pairs are represented as Entry types.
     * @return an Iterator of Entry types
     * @see Entry
     * @see AbstractHashMapIterator
     */
    public abstract Iterator<Entry> iterator();

    /**
     * Increase the capacity of the HashMap
     * This usually reduces the number of collisions that occur
     */
    protected abstract void resize();

    /**
     * @return the number of key/value pairs in the HashMap
     */
    public int size() {
        return numKeys;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @param key
     * @return the index position this key maps to in the data structure underlying the HashMap
     */
    protected int hash(Object key) {
        return (key.hashCode() % capacity + capacity) % capacity;
    }
}
    
    
