package com.uaiguitar.site.pagSeguro;

public class Customer {
    
    private String name;
    private String email;
    private String tax_id;

    private Phone phone;

    public Customer (){}

    public Customer(String name, String email, String tax_id, Phone phone) {
        this.name = name;
        this.email = email;
        this.tax_id = tax_id;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", email=" + email + ", tax_id=" + tax_id + ", phone=" + phone + "]";
    }

    
}
