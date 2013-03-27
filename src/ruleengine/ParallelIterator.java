package ruleengine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ParallelIterator implements CombinationIterator {

    private final List<Rule> parallelRules;

    public ParallelIterator(List<Rule> parallelRules) {
        this.parallelRules = parallelRules;
    }


    @Override
    public boolean hasCombinations() {
        // each rule with at least one value
        return getPropertyCombinationsSize() > 0;
    }

    private int getPropertyCombinationsSize() {
        int result = 0;

        if (this.parallelRules != null ) {
            for (Rule r : this.parallelRules){
                int i = r.getValuesCount();

                if (i == 0) {
                    return 0;
                } else {
                    if (result< i ) {
                        result = i;
                    }
                }

            }
        }
        return result;
    }

    @Override
    public Iterable<PropertyCombinations> getPropertyCombinations() {
        return new FixedLengthIterable<PropertyCombinations>(
                new IteratorProducer<PropertyCombinations>() {

                    HashMap<Rule, Iterator<Object>> cyclicIterableMap;

                    private Object getNextValueFor(Rule rule) {
                        Iterator<Object> valuesIterator = null;
                        if (cyclicIterableMap == null) {
                            cyclicIterableMap = new HashMap<Rule, Iterator<Object>>();
                        } else {
                            valuesIterator = cyclicIterableMap.get(rule);
                        }

                        if (valuesIterator == null) {
                            valuesIterator = createCyclicValuesIterable(rule);

                            cyclicIterableMap.put(rule, valuesIterator);
                        }

                        return valuesIterator.next();

                    }

                    private Iterator<Object> createCyclicValuesIterable(final Rule rule) {
                        return new CyclicIterable<Object>(new IteratorProducer<Object>() {

                            @Override
                            public Iterator<Object> createIterator() {
                                return new IteratorAdaptor<Object>() {

                                    final Iterator<Object> iterator = rule.valuesIterator();

                                    @Override
                                    public boolean hasNext() {
                                        return iterator.hasNext();
                                    }

                                    @Override
                                    public Object next() {
                                        return iterator.next();
                                    }

                                };
                            }
                        }).iterator();
                    }


                    @Override
                    public Iterator<PropertyCombinations> createIterator() {
                        return new IteratorAdaptor<PropertyCombinations>() {

                            @Override
                            public PropertyCombinations next() {
                                return new PropertyCombinations() {

                                    @Override
                                    public Iterable<PropertyAssignment> getPropertyAssignments() {
                                        return new ProxyIterable<PropertyAssignment>(new IteratorProducer<PropertyAssignment>() {
                                            @Override
                                            public Iterator<PropertyAssignment> createIterator() {
                                                return new IteratorAdaptor<PropertyAssignment>() {

                                                    final Iterator<Rule> ruleIterator = parallelRules.iterator();

                                                    @Override
                                                    public boolean hasNext() {
                                                        return ruleIterator.hasNext();
                                                    }

                                                    @Override
                                                    public PropertyAssignment next() {
                                                        Rule rule = ruleIterator.next();

                                                        return new PropertyAssignment(rule.getTargetedPropertyName(), getNextValueFor(rule));
                                                    }

                                                };
                                            }
                                        });
                                    }
                                };
                            }
                        };
                    }
                }, getPropertyCombinationsSize());
    }

}
