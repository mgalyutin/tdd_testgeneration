package ruleengine;

import java.util.Iterator;

/*
 * User: mgalyutin
 * Date: 25.03.13
 * Time: 23:21
 */
public  interface IteratorProducer<T> {

    Iterator<T> createIterator();

}