package com.cedacri.internship.entities;

import java.util.Objects;

public class Bank {

    private final int id;

    private final String branch;

    private final String address;


    Bank(Builder builder) {
        this.id = builder.id;
        this.branch = builder.branch;
        this.address = builder.address;
    }

    public int getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }


    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id == bank.id && Objects.equals(branch, bank.branch) && Objects.equals(address, bank.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branch, address);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id='" + id + '\'' +
                ", bankBranch='" + branch + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class Builder {

        private int id;

        private String branch;

        private String address;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder branch(String branch) {
            this.branch = branch;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Bank build() {
            return new Bank(this);
        }
    }
}
