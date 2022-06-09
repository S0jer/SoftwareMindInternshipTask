package org.example.businesscard;


public record BusinessCard(String name, String surname, String phone) {

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

}
