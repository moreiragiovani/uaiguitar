package com.uaiguitar.site.pagSeguro;



public class Items {

    private String name;
    private Integer quantity;
    private Integer unit_amount;

    public Items(){}

    public Items(String name, Integer quantity, Integer unit_amount) {
        this.name = name;
        this.quantity = quantity;
        this.unit_amount = unit_amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnit_amount() {
        return unit_amount;
    }

    public void setUnit_amount(Integer unit_amount) {
        this.unit_amount = unit_amount;
    }

    @Override
    public String toString() {
        return "Items [name=" + name + ", quantity=" + quantity + ", unit_amount=" + unit_amount + "]";
    }

    

}
