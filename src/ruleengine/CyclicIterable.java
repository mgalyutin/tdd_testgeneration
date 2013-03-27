package ruleengine;

import java.util.Iterator;

/*
 * User: mgalyutin
 * Date: 25.03.13
 * Time: 22:44
 */
public class CyclicIterable<T> implements Iterable<T> {


    private final IteratorProducer<T> iteratorProducer;

    public CyclicIterable(IteratorProducer<T> iteratorProducer) {
        this.iteratorProducer = iteratorProducer;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorAdaptor<T>() {

            Iterator<T> currentIterator = iteratorProducer.createIterator();

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                if (!currentIterator.hasNext()) {
                    currentIterator = iteratorProducer.createIterator();
                }

                return currentIterator.next();
            }

        };
    }
}
