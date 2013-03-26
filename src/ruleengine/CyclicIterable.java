package ruleengine;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/*
 * User: mgalyutin
 * Date: 25.03.13
 * Time: 22:44
 */
public class CyclicIterable<T> implements Iterable<T> {


    private IteratorProducer<T> iteratorProducer;

    public CyclicIterable(IteratorProducer<T> iteratorProducer) {
        this.iteratorProducer = iteratorProducer;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Iterator<T> currentIterator = iteratorProducer.createIterator();

            int count = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                if (!currentIterator.hasNext()) {
                    currentIterator = iteratorProducer.createIterator();
                }
                T r =  currentIterator.next();

                count++;

                return r;
            }

            @Override
            public void remove() {
                throw new NotImplementedException();
            }
        };
    }
}
