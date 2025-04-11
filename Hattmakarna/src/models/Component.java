/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author andre
 */
public class Component {
    
    private double amount;
    private int component_id;
    private String component_name;
    private String color;
    private String unit;
    
    
    public Component(double amount, int component_id, String component_name, String color, String unit) {
        this.amount = amount;
        this.component_id = component_id;
        this.component_name = component_name;
        this.color = color;
        this.unit = unit;
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
}
