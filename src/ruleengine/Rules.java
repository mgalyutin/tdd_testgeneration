package ruleengine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Rules{
	private final Map<String, Rule> rules = new LinkedHashMap<String, Rule>();

	public void addRule(Rule rule) {
		rules.put(rule.getTargetedPropertyName(), rule);
	}

	public List<Rule> values() {
		ArrayList<Rule> arrayList = new ArrayList<Rule>();
		 arrayList.addAll(rules.values());
		return arrayList;
	}

	public boolean hasRuleForProperty(String propertyName) {
		return rules.containsKey(propertyName);
	}

}