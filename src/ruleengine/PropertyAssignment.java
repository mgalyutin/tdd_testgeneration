package ruleengine;

public class PropertyAssignment {

    private final String targetedPropertyName;
    private final Object value;

    public PropertyAssignment(String targetedPropertyName, Object value) {
        this.targetedPropertyName = targetedPropertyName;
        this.value = value;
    }

    public String getTargetedPropertyName() {
        return targetedPropertyName;
    }
	
	public Object getValue() {
        return value;
    }
	
}
