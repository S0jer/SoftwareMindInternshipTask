package com.example.apptworestapi.businesscard;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class BusinessCard {
    private String name;
    private String surname;
    private String phone;

    @Override
    public String toString() {
        return name + " " +
                surname +
                " " +
                phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessCard that = (BusinessCard) o;
        return name.equals(that.name) && surname.equals(that.surname) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, phone);
    }
}
