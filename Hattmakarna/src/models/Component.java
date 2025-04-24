
package models;

public class Component {
    
    private double amount;
    private int component_id;
    private String component_name;
    private String color;
    private String unit;
    private String type;
    private String description;
    
    
    public Component(double amount, int component_id, String component_name, String color, String unit, String type, String description) {
        this.amount = amount;
        this.component_id = component_id;
        this.component_name = component_name;
        this.color = color;
        this.unit = unit;
        this.type = type;
        this.description=description;
    }
    
    public int getComponentId() {
        return component_id;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getComponentName() {
        return component_name;
    }
    
    public String getColor() {
        return color;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
        public void setDescription(String description) {
        this.description = description;
    }
    
    public void setComponentId(int component_id ) {
        this.component_id = component_id;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void setComponentName(String component_name) {
        this.component_name = component_name;
    }
    
    public void setColor (String color) {
        this.color = color;
    }
    
    public void setUnit (String unit) {
        this.unit = unit;
    }
    
    public void setType (String type) {
        this.type = type;
    }
}
