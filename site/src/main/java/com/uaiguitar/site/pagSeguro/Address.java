package com.uaiguitar.site.pagSeguro;






public class Address {
    
    private String street;
    private String number;
    private String complement;
    private String locality;
    private String city;
    private String region;
    private String region_code;
    private String country;
    private String postal_code;

    public Address (){}

    public Address(String street, String number, String complement, String locality, String city, String region,
            String region_code, String country, String postal_code) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.locality = locality;
        this.city = city;
        this.region = region;
        this.region_code = region_code;
        this.country = country;
        this.postal_code = postal_code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", number=" + number + ", complement=" + complement + ", locality="
                + locality + ", city=" + city + ", region=" + region + ", region_code=" + region_code + ", country="
                + country + ", postal_code=" + postal_code + "]";
    }

    
}
