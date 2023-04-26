package com.uaiguitar.site.pagSeguro;

public class Shipping {
    
    private Address address;

    public Shipping(){}

    public Shipping(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shipping [address:" + address + "]";
    }

    
    
    
}
