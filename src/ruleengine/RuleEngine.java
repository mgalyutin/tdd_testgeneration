/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2013 Dimitry
 *
 *  This file author is Dimitry
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ruleengine;

/**
 * @author Dimitry Polivaev 18.02.2013
 */
public class RuleEngine {
	private Rules rules = new Rules();
	private State state = new State();

	public void addRule(Rule rule) {
		rules.addRule(rule);
	}

	public boolean hasRuleForProperty(String propertyName) {
		return rules.hasRuleForProperty(propertyName);
	}

	// while (combination.hasNext()) {
	// while(combilnation.nextRule().nextValue()) {
	// generate
	// }

	public void run(ScriptProducer scriptProducer) {
		// List<Rule> ruleValues = rules.values();

		// Set[] -- all iterations for the current combination root can be
		// determined
		CombinationIterator combinations = new ParallelIterator(rules.values());

		if (combinations.hasCombinations()) {

			for (PropertyCombinations propertySet : combinations.getPropertyCombinations()) {
				for (PropertyAssignment c : propertySet.getPropertyAssignments()) {
					state.addProperty(c.getTargetedPropertyName(), c.getValue());
				}
				scriptProducer.makeScriptFor(this);
			}

		} else {
			scriptProducer.makeScriptFor(this);
		}
	}

	private void addPropertyToState(Rule rule, ValueIterator valueIterator) {
			Object value = valueIterator.next();
			state.addProperty(rule.getTargetedPropertyName(), value);
	}


	public String getAssignedPropertiesAsString() {
		return state.getAssignedPropertiesAsString();
	}

}
