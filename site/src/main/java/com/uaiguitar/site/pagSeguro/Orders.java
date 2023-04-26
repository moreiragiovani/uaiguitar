package com.uaiguitar.site.pagSeguro;


public class Orders {
    
    private String reference_id;
    private Customer customer;
    private Items items;
    private Shipping shipping;

    public Orders (){}

    public Orders(String reference_id, Customer customer, Items items, Shipping shipping) {
        this.reference_id = reference_id;
        this.customer = customer;
        this.items = items;
        this.shipping = shipping;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    
    

}
