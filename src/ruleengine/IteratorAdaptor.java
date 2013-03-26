package ruleengine;

import java.util.Iterator;

/*
 * User: mgalyutin
 * Date: 26.03.13
 * Time: 21:20
 */
public abstract class IteratorAdaptor<T> implements Iterator<T> {

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
