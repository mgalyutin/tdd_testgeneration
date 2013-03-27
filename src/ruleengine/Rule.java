package ruleengine;

import java.util.Iterator;

public interface Rule  {

	String getTargetedPropertyName();

    Iterator<Object> valuesIterator();

    public int getValuesCount();

}