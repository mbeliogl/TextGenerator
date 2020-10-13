package com.maxim;
import java.util.Iterator;
import java.util.ArrayList;


/* A concrete implementation of a ChainingHashMap */
/* See AbstractHashMap */

public class ChainingHashMap extends AbstractHashMap {

    protected ArrayList[] entries;

    public ChainingHashMap() {
		super(5);
		entries = new ArrayList[capacity];
    }

    @Override
    public void put(Object key, Object value) {
		Entry e  = new Entry(key, value);
		boolean contains = false; // check if key already in map
		int pos = hash(key);
	
		// check if resize is needed using load formula
		if((double)numKeys/capacity >= maxLoad) { resize(); }

		// if nothing in position, make a new list and add the Entry to it
		if(entries[pos] == null) {
			entries[pos] = new ArrayList();
			entries[pos].add(e);
			numKeys++;
	    }
		else {
			for(int i = 0; i < entries[pos].size(); i++) {
				Entry test = (Entry) entries[pos].get(i);
				if(test.key.equals(key)){
					test.value = value;
					contains = true;
			    }
		    }
			if(!contains) {
				entries[pos].add(e);
				numKeys++;
		    }
	    }
    }


    @Override
    public void resize() {
		ArrayList[] entries1 = entries;
	
		capacity = capacity * 2;
		entries = new ArrayList[capacity];
		numKeys = 0;

		for(ArrayList array: entries1) {
			if(array != null) {
				for(int i = 0; i <= array.size()-1; i++) {
					Entry test = (Entry) array.get(i); //rehashing everything into bigger array
					put(test.key,test.value);
			    }
		    }
	    }
    }
	
	
    @Override
    public Object get(Object key) {
		int pos = hash(key);
		if(entries[pos] == null) {
			return null;
	    }
		else {
			for(int i = 0; i < entries[pos].size(); i++) {
				Entry e = (Entry) entries[pos].get(i); //loop through entries and get their keys

				if(e.key.equals(key)) { return e.value; }
		    }
	    }
		return null;
    }
    
    
    public void printEntries() {
		for(ArrayList a: entries) {
			System.out.println(a);
	    }
    }
    
    @Override
    public Iterator<Entry> iterator() {
		return new ChainingHashMapIterator(entries);
    }
}
			   
