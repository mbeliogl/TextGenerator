package com.maxim;
import java.util.Iterator;
import java.util.ArrayList;

/* A concrete implementation of a ChainingHashMapIterator */
/* See AbstractHashMapIterator */

public class ChainingHashMapIterator extends AbstractHashMapIterator {

    private ArrayList[] entries;
    private int index;

    public ChainingHashMapIterator(ArrayList[] entries) {
		this.entries = entries;
		index = -1;
    }


	@Override
    public boolean hasNext() {
		int index1 = index;
	
		if(index1 > 1) {
			if(entries[index1].size() > 1){
		    //is there more than 1 item and is it the only (last) item
				return true;
		    }
			else { index1++; }
	    }
		else
	    	index1++;

		//to start from 0
		if(index1 == -1) { index1++; }

		while(index1<entries.length) {
			if(entries[index1] != null) {
				return true;
		    }
			index1++;
	    }
		return false;
    }


    @Override
    public Entry next() {

		int insideIdx = 0;

		if(index > - 1) {
			if(entries[index].size() > 1) {
				insideIdx++;
				return (Entry) entries[index].get(insideIdx);
		    }
			else
		    	index++;
	    }
		else
	    	index++;
	
		if(index == - 1) { index++; }

		insideIdx = 0;
		while(index < entries.length) {
			if(entries[index] != null) {
				return (Entry) entries[index].get(insideIdx); // if position not empty, return the Entry
		    }
			index++;
	    }
		return null;
    }
    
}

			
				

		 

