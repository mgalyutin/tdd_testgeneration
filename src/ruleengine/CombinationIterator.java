package ruleengine;


public interface CombinationIterator {

	public Iterable<PropertyCombinations> getPropertyCombinations();

	public boolean hasCombinations(); 
	
}
