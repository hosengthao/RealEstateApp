package net.yorksolutions.realestate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    Long id;

    @JsonProperty
    String first;

    @JsonProperty
    char middle;

    @JsonProperty
    String last;

    @JsonProperty
    String street;

    @JsonProperty
    String city;

    @JsonProperty
    String state;

    @JsonProperty
    int zip;

    @JsonProperty
    String email;

    public Long getId() {
        return id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public char getMiddle() {
        return middle;
    }

    public void setMiddle(char middle) {
        this.middle = middle;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
