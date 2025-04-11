package models;

/**
 *
 * @author Petra Schröder
 */
public class ProductComponent {

    private int product_component_id;
    private String component_name;
    private String type;
    private String description;
    private String unit;
    private int amount;
    private String color;

    public ProductComponent(int product_component_id, String component_name, String type, String description, String unit, int amount, String color) {
        this.product_component_id = product_component_id;
        this.component_name = component_name;
        this.type = type;
        this.description = description;
        this.unit = unit;
        this.amount = amount;
        this.color = color;
    }

    public int getProduct_component_id() {
        return product_component_id;
    }

    public void setComponentName(String componentName) {
        this.component_name = componentName;
    }

    public String getComponentName() {
        return component_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
