package ruleengine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/*
 * User: mgalyutin
 * Date: 25.03.13
 * Time: 22:44
 */
public class ProxyIterable<T> implements Iterable<T> {

    private final IteratorProducer<T> iteratorProducer;


    public ProxyIterable(IteratorProducer<T> iteratorProducer) {
        this.iteratorProducer = iteratorProducer;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            final Iterator<T> currentIterator = iteratorProducer.createIterator();

            @Override
            public boolean hasNext() {
                return currentIterator.hasNext();
            }

            @Override
            public T next() {
                return currentIterator.next();
            }

            @Override
            public void remove() {
                throw new NotImplementedException();
            }
        };
    }
}
