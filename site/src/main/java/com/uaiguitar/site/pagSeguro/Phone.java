package com.uaiguitar.site.pagSeguro;


public class Phone {

    private Integer country;
    private Integer area;
    private Integer number;

    private String type; // Tipo Enum.

    public Phone(){}

    public Phone(Integer country, Integer area, Integer number, String type) {
        this.country = country;
        this.area = area;
        this.number = number;
        this.type = type;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Phone [country=" + country + ", area=" + area + ", number=" + number + ", type=" + type + "]";
    }

    
}
