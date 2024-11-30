// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
import java.util.Iterator;


class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer nextNum;

	public PeekingIterator(Iterator<Integer> iterator) {
	    this.iterator = iterator;
        nextNum();
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return nextNum;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.

@Override
	public Integer next() {
        int res = nextNum;
	    nextNum();
        return res;
	}
	
	@Override
	public boolean hasNext() {
	    return nextNum != null;
	}

    private void nextNum() {
        if (iterator.hasNext()) 
            nextNum = iterator.next();
        else 
            nextNum = null;
    }
}